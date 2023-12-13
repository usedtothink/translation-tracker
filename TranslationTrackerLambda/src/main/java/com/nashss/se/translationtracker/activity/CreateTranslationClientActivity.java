package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationClientResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.model.TranslationClientModel;
import com.nashss.se.translationtracker.types.ClientType;
import com.nashss.se.translationtracker.utils.IdGenerator;

import javax.inject.Inject;

/**
 * Implementation of the CreateTranslationClient Activity for the TranslationTracker's CreateTranslationClient API.
 * <p>
 * This API allows the customer to create a new translation client.
 */
public class CreateTranslationClientActivity {
    private final TranslationClientDao clientDao;

    /**
     * Instantiates a new CreateTranslationClientActivity object.
     *
     * @param clientDao to access the translation clients table.
     */
    @Inject
    public CreateTranslationClientActivity(TranslationClientDao clientDao) {
        this.clientDao = clientDao;
    }

    /**
     * This method handles the incoming request by persisting a new translation client
     * with the provided customer ID, translation client name, and translation client type
     * <p>
     * Then it returns the newly created translation client.
     * <p>
     * If no translation client name is provided, throws an IllegalArgumentException.
     * @param createTranslationClientRequest request object containing the customerID, translation client name,
     *                                       and translation client type.
     * @return createTranslationClientResult result object containing the API defined {@link TranslationClientModel}
     */
    public CreateTranslationClientResult handleRequest(final CreateTranslationClientRequest
                                                               createTranslationClientRequest) {
        String translationClientName = createTranslationClientRequest.getTranslationClientName();
        if (translationClientName == null || translationClientName.isBlank()) {
            throw new IllegalArgumentException("A name must be provided for the translation client.");
        }
        TranslationClient newTranslationClient = new TranslationClient();
        newTranslationClient.setCustomerId(createTranslationClientRequest.getCustomerId());
        newTranslationClient.setTranslationClientId(IdGenerator.newTranslationClientId(translationClientName,
                createTranslationClientRequest.getClientType()));
        newTranslationClient.setClientType(ClientType.valueOf(createTranslationClientRequest
                .getClientType()));
        newTranslationClient.setTranslationClientName(createTranslationClientRequest.getTranslationClientName());

        clientDao.createTranslationClient(newTranslationClient);

        TranslationClientModel translationClientModel = new ModelConverter()
                .toTranslationClientModel(newTranslationClient);
        return CreateTranslationClientResult.builder()
                .withTranslationClient(translationClientModel)
                .build();
    }
}
