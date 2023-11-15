package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationCaseResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.types.ProjectType;

import javax.inject.Inject;

/**
 * Implementation of the CreateTranslationCaseActivity for the TranslationTracker's CreateTranslationCase API.
 * <p>
 * This API allows the customer to create a new translation case that is linked to a translation client.
 */

public class CreateTranslationCaseActivity {
    private final TranslationCaseDao caseDao;

    /**
     * Instantiates a new CreateTranslationCaseActivity object.
     *
     * @param caseDao to access the translation cases table.
     */
    @Inject
    public CreateTranslationCaseActivity(TranslationCaseDao caseDao) {
        this.caseDao = caseDao;
    }
    /**
     * This method handles the incoming request by persisting a new translation case
     * with the provided case nickname and translation client ID from the request.
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
        TranslationCase newTranslationCase = new TranslationCase();
        newTranslationCase.setTranslationCaseId(createTranslationCaseRequest.getTranslationCaseId());
        newTranslationCase.setProjectType(ProjectType.valueOf(createTranslationCaseRequest.getProjectType()));
        newTranslationCase.setCaseNickname(createTranslationCaseRequest.getCaseNickname());

        caseDao.createTranslationCase(newTranslationCase);

        TranslationCaseModel translationCaseModel = new ModelConverter().toTranslationCaseModel(newTranslationCase);
        return CreateTranslationCaseResult.builder()
                .withTranslationCase(translationCaseModel)
                .build();
    }
}
