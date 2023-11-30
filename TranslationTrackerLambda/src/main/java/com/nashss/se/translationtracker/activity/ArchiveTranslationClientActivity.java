package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.ArchiveTranslationClientResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.model.TranslationClientModel;

import javax.inject.Inject;

public class ArchiveTranslationClientActivity {
    private final TranslationClientDao clientDao;

    /**
     * Instantiates a new ArchiveTranslationClientActivity object.
     *
     * @param clientDao TranslationClientDao to access the translation client table.
     */
    @Inject
    public ArchiveTranslationClientActivity(TranslationClientDao clientDao) {
        this.clientDao = clientDao;
    }

    /**
     * This method handles the incoming request by archiving the translation client in the database.
     * <p>
     * It then returns the translation client;
     * <p>
     * If the translation case does not exist, this should throw a TranslationClientNotFoundException.
     *
     * @param archiveTranslationClientRequest request object containing the translation client ID.
     * @return archiveTranslationClientResult result object containing the API defined {@link TranslationClientModel}
     */
    public ArchiveTranslationClientResult handleRequest(
            final ArchiveTranslationClientRequest archiveTranslationClientRequest) {
        String customerId = archiveTranslationClientRequest.getCustomerId();
        String translationClientId = archiveTranslationClientRequest.getTranslationClientId();

        TranslationClient translationClient = clientDao.archiveTranslationClient(customerId, translationClientId);
        TranslationClientModel translationClientModel = new ModelConverter()
                .toTranslationClientModel(translationClient);

        return ArchiveTranslationClientResult.builder()
                .withTranslationClient(translationClientModel)
                .build();
    }
}
