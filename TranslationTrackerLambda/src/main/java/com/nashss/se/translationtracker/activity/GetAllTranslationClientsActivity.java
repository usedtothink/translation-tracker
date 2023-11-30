package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationClientsRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationClientsResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.model.TranslationClientModel;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetAllTranslationClientsActivity for Translation Tracker's GetAllTranslationClients API.
 * <p>
 * This API allows the customer to get a list of all of their saved translation clients.
 */
public class GetAllTranslationClientsActivity {
    private final TranslationClientDao clientDao;

    /**
     * Instantiates a new GetAllTranslationClientsActivity object.
     *
     * @param clientDao TranslationClientDao to access the translation client table.
     */
    @Inject
    public GetAllTranslationClientsActivity(TranslationClientDao clientDao) {
        this.clientDao = clientDao;
    }

    /**
     * This method handles the incoming request by retrieving all the translation clients from the database that are
     * associated with the customerId.
     * <p>
     * It then returns a list of all translation clients associated with the customerId;
     * <p>
     * If there are no translation clients with that customerId, this should throw a TranslationClientNotFoundException.
     *
     * @param getAllTranslationClientsRequest request object containing the customerId.
     * @return getAllTranslationClientsResult result object containing a list of
     * the API defined {@link TranslationClientModel}
     */
    public GetAllTranslationClientsResult handleRequest(final GetAllTranslationClientsRequest
                                                        getAllTranslationClientsRequest) {
        String requestedCustomerId = getAllTranslationClientsRequest.getCustomerId();
        List<TranslationClient> translationClientList = clientDao.getAllTranslationClients(requestedCustomerId);
        List<TranslationClientModel> translationClientModelList = new ModelConverter()
                .toTranslationClientModelList(translationClientList);

        return GetAllTranslationClientsResult.builder()
                .withTranslationClientList(translationClientModelList)
                .build();
    }
}
