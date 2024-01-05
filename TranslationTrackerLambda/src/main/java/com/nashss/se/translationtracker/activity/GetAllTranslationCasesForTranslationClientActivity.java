package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationCasesForTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationCasesForTranslationClientResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetAllTranslationCasesForTranslationClientActivity for Translation Tracker's
 * GetAllTranslationCases API.
 * <p>
 * This API allows the customer to get a list of all of saved translation cases that correspond to the
 * given translation client id..
 */
public class GetAllTranslationCasesForTranslationClientActivity {
    private final TranslationCaseDao caseDao;

    /**
     * Instantiates a new GetAllTranslationCasesForTranslationClientActivity object.
     *
     * @param caseDao TranslationCaseDao to access the translation case table.
     */
    @Inject
    public GetAllTranslationCasesForTranslationClientActivity(TranslationCaseDao caseDao) {
        this.caseDao = caseDao;
    }

    /**
     * This method handles the incoming request by retrieving all the translation cases from the database that are
     * associated with the translation customer id.
     * <p>
     * It then returns a list of all translation cases associated with the translation case id;
     * <p>
     *
     * @param request request object containing the customerId and translation
     *                                                         client id.
     * @return getAllTranslationCasesForTranslationClientResult result object containing a list of
     * the API defined {@link TranslationCaseModel}
     */
    public GetAllTranslationCasesForTranslationClientResult handleRequest(
            final GetAllTranslationCasesForTranslationClientRequest request) {
        String requestedCustomerId = request.getCustomerId();
        String requestedClientID = request.getTranslationClientId();

        List<TranslationCase> translationCaseList = caseDao.getAllTranslationCasesForTranslationClientId(
                requestedCustomerId, requestedClientID);
        List<TranslationCaseModel> translationCaseModelList = new ModelConverter()
                .toTranslationCaseModelList(translationCaseList);

        return GetAllTranslationCasesForTranslationClientResult.builder()
                .withTranslationCaseList(translationCaseModelList)
                .build();
    }


}
