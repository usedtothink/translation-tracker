package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationClientResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.model.TranslationClientModel;

import javax.inject.Inject;

/**
 * Implementation of the GetTranslationClientActivity for Translation Tracker's GetTranslationClient API.
 *<p>
 * This API allows the customer to get one of their saved translation clients.
 */
public class GetTranslationClientActivity {
    private final TranslationClientDao clientDao;

    /**
     * Instantiates a new GetTranslationClientActivity object.
     *
     * @param clientDao TranslationClientDao to access the translation client table.
     */
    @Inject
    public GetTranslationClientActivity(TranslationClientDao clientDao) {
        this.clientDao = clientDao;
    }

    /**
     * This method handles the incoming request by retrieving the translation client from the database.
     * <p>
     * It then returns the translation client;
     * <p>
     * If the translation client does not exist, this should throw a TranslationClientNotFoundException.
     *
     * @param getTranslationClientRequest request object containing the translation client ID.
     * @return GetTranslationClientResult result object containing the API defined {@link TranslationClientModel}
     */
    public GetTranslationClientResult handleRequest(final GetTranslationClientRequest getTranslationClientRequest) {
        String requestedId = getTranslationClientRequest.getTranslationClientId();
        String customerId = getTranslationClientRequest.getCustomerId();
        TranslationClient translationClient = clientDao.getTranslationClient(customerId, requestedId);
        TranslationClientModel translationClientModel = new ModelConverter()
                .toTranslationClientModel(translationClient);

        return GetTranslationClientResult.builder()
                .withTranslationClient(translationClientModel)
                .build();
    }
}
