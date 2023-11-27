package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.ArchiveTranslationCaseResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import javax.inject.Inject;

/**
 * Implementation of the ArchiveTranslationCaseActivity for Translation Tracker's ArchiveTranslationCase API.
 *<p>
 * This API allows the customer to archive one of their saved translation cases.
 */
public class ArchiveTranslationCaseActvity {
    private final TranslationCaseDao caseDao;

    /**
     * Instantiates a new ArchiveTranslationCaseActivity object.
     *
     * @param caseDao TranslationCaseDao to access the translation case table.
     */
    @Inject
    public ArchiveTranslationCaseActvity(TranslationCaseDao caseDao) {
        this.caseDao = caseDao;
    }

    /**
     * This method handles the incoming request by archiving the translation case in the database.
     * <p>
     * It then returns the translation case;
     * <p>
     * If the translation case does not exist, this should throw a TranslationCaseNotFoundException.
     *
     * @param archiveTranslationCaseRequest request object containing the translation case ID.
     * @return archiveTranslationCaseResult result object containing the API defined {@link TranslationCaseModel}
     */
    public ArchiveTranslationCaseResult handleRequest(
            final ArchiveTranslationCaseRequest archiveTranslationCaseRequest) {
        String customerId = archiveTranslationCaseRequest.getCustomerId();
        String translationCaseId = archiveTranslationCaseRequest.getTranslationCaseId();

        TranslationCase translationCase = caseDao.archiveTranslationCase(customerId, translationCaseId);
        TranslationCaseModel translationCaseModel = new ModelConverter().toTranslationCaseModel(translationCase);

        return ArchiveTranslationCaseResult.builder()
                .withTranslationCase(translationCaseModel)
                .build();
    }
}
