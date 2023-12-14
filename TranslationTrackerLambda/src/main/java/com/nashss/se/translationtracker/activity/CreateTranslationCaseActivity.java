package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationCaseResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.types.ProjectType;
import com.nashss.se.translationtracker.utils.IdGenerator;

import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Implementation of the CreateTranslationCaseActivity for the TranslationTracker's CreateTranslationCase API.
 * <p>
 * This API allows the customer to create a new translation case.
 */

public class CreateTranslationCaseActivity {
    private final TranslationCaseDao caseDao;
    private final PaymentRecordDao paymentRecordDao;

    /**
     * Instantiates a new CreateTranslationCase Activity object.
     *
     * @param caseDao to access the translation cases table.
     * @param paymentRecordDao to access the payment record table.
     */
    @Inject
    public CreateTranslationCaseActivity(TranslationCaseDao caseDao, PaymentRecordDao paymentRecordDao) {
        this.caseDao = caseDao;
        this.paymentRecordDao = paymentRecordDao;
    }
    /**
     * This method handles the incoming request by persisting a new translation case
     * with the provided customer ID, case nickname and project type from the request.
     * <p>
     * Then it returns the newly created translation case.
     * <p>
     * @param createTranslationCaseRequest request object containing the translation case nickname and project type.
     * @return createTranslationCaseResult result object containing the API defined {@link TranslationCaseModel}
     */
    public CreateTranslationCaseResult handleRequest(final CreateTranslationCaseRequest
            createTranslationCaseRequest) {
        if (createTranslationCaseRequest.getCaseNickname().isBlank()) {
            throw new IllegalArgumentException("The translation case nickname cannot be blank.");
        }

        if (createTranslationCaseRequest.getProjectType().isBlank()) {
            throw new IllegalArgumentException("The project type cannot be blank.");
        }


        String customerId = createTranslationCaseRequest.getCustomerId();
        String translationCaseId = IdGenerator.newTranslationCaseId(createTranslationCaseRequest.getProjectType(),
                createTranslationCaseRequest.getCaseNickname());

        TranslationCase newTranslationCase = new TranslationCase();
        newTranslationCase.setCustomerId(customerId);
        newTranslationCase.setTranslationCaseId(translationCaseId);
        newTranslationCase.setCaseNickname(createTranslationCaseRequest.getCaseNickname());
        newTranslationCase.setProjectType(ProjectType.valueOf(createTranslationCaseRequest.getProjectType()));
        newTranslationCase.setTranslationClientId(createTranslationCaseRequest.getTranslationClientId());
        newTranslationCase.setSourceTextTitle(createTranslationCaseRequest.getSourceTextTitle());
        newTranslationCase.setSourceTextAuthor(createTranslationCaseRequest.getSourceTextAuthor());
        newTranslationCase.setTranslatedTitle(createTranslationCaseRequest.getTranslatedTitle());
        newTranslationCase.setDueDate(createTranslationCaseRequest.getDueDate());
        newTranslationCase.setStartDate(createTranslationCaseRequest.getStartDate());
        newTranslationCase.setEndDate(createTranslationCaseRequest.getEndDate());
        newTranslationCase.setOpenCase(createTranslationCaseRequest.getOpenCase());
        newTranslationCase.setRushJob(createTranslationCaseRequest.getRushJob());
        newTranslationCase.setProgressLog(new ArrayList<>());

        caseDao.createTranslationCase(newTranslationCase);
        paymentRecordDao.createPaymentRecord(customerId, translationCaseId);

        TranslationCaseModel translationCaseModel = new ModelConverter().toTranslationCaseModel(newTranslationCase);
        return CreateTranslationCaseResult.builder()
                .withTranslationCase(translationCaseModel)
                .build();
    }
}
