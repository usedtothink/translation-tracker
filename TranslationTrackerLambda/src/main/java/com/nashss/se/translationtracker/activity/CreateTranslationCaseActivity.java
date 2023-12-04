package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationCaseResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.types.ProjectType;

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
     * Instantiates a new CreateTranslationCaseActivity object.
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
     * If the provided case nickname already exists, throw a
     * DuplicateCaseNicknameException.
     * @param createTranslationCaseRequest request object containing the translation case nickname and project type.
     * @return createTranslationCaseResult result object containing the API defined {@link TranslationCaseModel}
     */
    public CreateTranslationCaseResult handleRequest(final CreateTranslationCaseRequest
            createTranslationCaseRequest) {
        String customerId = createTranslationCaseRequest.getCustomerId();
        String translationCaseId = createTranslationCaseRequest.getTranslationCaseId();

        TranslationCase newTranslationCase = new TranslationCase();
        newTranslationCase.setCustomerId(customerId);
        newTranslationCase.setTranslationCaseId(translationCaseId);
        newTranslationCase.setProjectType(ProjectType.valueOf(createTranslationCaseRequest.getProjectType()));
        newTranslationCase.setCaseNickname(createTranslationCaseRequest.getCaseNickname());

        caseDao.createTranslationCase(newTranslationCase);
        paymentRecordDao.createPaymentRecord(customerId, translationCaseId);

        TranslationCaseModel translationCaseModel = new ModelConverter().toTranslationCaseModel(newTranslationCase);
        return CreateTranslationCaseResult.builder()
                .withTranslationCase(translationCaseModel)
                .build();
    }
}
