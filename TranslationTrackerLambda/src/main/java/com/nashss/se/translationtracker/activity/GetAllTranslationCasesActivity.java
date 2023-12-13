package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationCasesRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationCasesResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import java.util.List;
import javax.inject.Inject;


/**
 * Implementation of the GetAllTranslationCasesActivity for Translation Tracker's GetAllTranslationCases API.
 * <p>
 * This API allows the customer to get a list of all of their saved translation cases.
 */
public class GetAllTranslationCasesActivity {
    private final TranslationCaseDao caseDao;

    /**
     * Instantiates a new GetAllTranslationCasesActivity object.
     *
     * @param caseDao TranslationCaseDao to access the translation case table.
     */
    @Inject
    public GetAllTranslationCasesActivity(TranslationCaseDao caseDao) {
        this.caseDao = caseDao;
    }

    /**
     * This method handles the incoming request by retrieving all the translation cases from the database that are
     * associated with the customerId.
     * <p>
     * It then returns a list of all translation cases associated with the customerId.
     * <p>
     * If there are no translation cases with that customerId, this should throw a TranslationCaseNotFoundException.
     *
     * @param getAllTranslationCasesRequest request object containing the customerId.
     * @return getAllTranslationCasesResult result object containing a list of
     * the API defined {@link TranslationCaseModel}
     */
    public GetAllTranslationCasesResult handleRequest(final GetAllTranslationCasesRequest
                                                              getAllTranslationCasesRequest) {
        String requestedCustomerId = getAllTranslationCasesRequest.getCustomerId();
        List<TranslationCase> translationCaseList = caseDao.getAllTranslationCases(requestedCustomerId);
        List<TranslationCaseModel> translationCaseModelList = new ModelConverter()
                .toTranslationCaseModelList(translationCaseList);

        return GetAllTranslationCasesResult.builder()
                .withTranslationCaseList(translationCaseModelList)
                .build();
    }
}
