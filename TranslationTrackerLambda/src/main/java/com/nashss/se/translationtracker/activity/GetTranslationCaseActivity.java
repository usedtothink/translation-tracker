package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationCaseResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import javax.inject.Inject;

/**
 * Implementation of the GetTranslationCase Activity for Translation Tracker's GetTranslationCase API.
 *<p>
 * This API allows the customer to get one of their saved translation cases.
 */
public class GetTranslationCaseActivity {
    private final TranslationCaseDao caseDao;

    /**
     * Instantiates a new GetTranslationCaseActivity object.
     * 
     * @param caseDao TranslationCaseDao to access the translation case table. 
     */
    @Inject
    public GetTranslationCaseActivity(TranslationCaseDao caseDao) {
        this.caseDao = caseDao;
    }

    /**
     * This method handles the incoming request by retrieving the translation case from the database.
     * <p>
     * It then returns the translation case;
     * <p>
     * If the translation case does not exist, this should throw a TranslationCaseNotFoundException.
     *
     * @param getTranslationCaseRequest request object containing the translation case ID and customer ID.
     * @return GetTranslationCaseResult result object containing the API defined {@link TranslationCaseModel}
     */
    public GetTranslationCaseResult handleRequest(final GetTranslationCaseRequest getTranslationCaseRequest) {
        String requestedId = getTranslationCaseRequest.getTranslationCaseId();
        String customerId = getTranslationCaseRequest.getCustomerId();
        TranslationCase translationCase = caseDao.getTranslationCase(customerId, requestedId);
        TranslationCaseModel translationCaseModel = new ModelConverter().toTranslationCaseModel(translationCase);

        return GetTranslationCaseResult.builder()
                .withTranslationCase(translationCaseModel)
                .build();
    }

}
