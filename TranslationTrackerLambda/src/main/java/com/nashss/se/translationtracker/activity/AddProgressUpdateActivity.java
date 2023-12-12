package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.AddProgressUpdateRequest;
import com.nashss.se.translationtracker.activity.results.AddProgressUpdateResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import javax.inject.Inject;

/**
 * Implementation of the AddProgressUpdateActivity for the TranslationTracker's AddProgressUpdateActivity API.
 * <p>
 * This API allows the customer to add a progress update.
 */
public class AddProgressUpdateActivity {
    private final TranslationCaseDao caseDao;

    /**
     * Instantiates a new AddProgressUpdateActivity object.
     *
     * @param caseDao to access the translation case table.
     */
    @Inject
    public AddProgressUpdateActivity(TranslationCaseDao caseDao) {
        this.caseDao = caseDao;
    }

    /**
     * This method handles the incoming request by persisting a progress update in an existing case
     * with the provided customer ID, translation case ID, and progress update object
     * <p>
     * Then it returns the updated translation case.
     * <p>
     * If the provided case ID does not exist, throw a TranslationCaseNotFoundException.
     * @param addProgressUpdateRequest request object containing the customerID, translation case ID and
     *                                 progress update values.
     * @return addProgressUpdateResult result object containing the API defined {@link TranslationCaseModel}
     */
    public AddProgressUpdateResult handleRequest(final AddProgressUpdateRequest addProgressUpdateRequest) {

        Integer wordCount = addProgressUpdateRequest.getWordCount();
        String startDate = addProgressUpdateRequest.getStartDate();
        String endDate = addProgressUpdateRequest.getEndDate();
        String startTime = addProgressUpdateRequest.getStartTime();
        String endTime = addProgressUpdateRequest.getEndTime();
        String notes = addProgressUpdateRequest.getNotes();

        if (wordCount == null || startDate == null || endDate == null || startTime == null || endTime == null ||
            notes == null) {
            throw new UnsupportedOperationException("All fields of the progress update must be filled in.");
        }
        ProgressUpdate progressUpdate = ProgressUpdate.builder()
                .withCustomerId(addProgressUpdateRequest.getCustomerId())
                .withTranslationCaseId(addProgressUpdateRequest.getTranslationCaseId())
                .withWordCount(wordCount)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withStartTime(startTime)
                .withEndTime(endTime)
                .withNotes(notes)
                .build();

        TranslationCase translationCase = caseDao.addProgressUpdate(progressUpdate);
        TranslationCaseModel translationCaseModel = new ModelConverter().toTranslationCaseModel(translationCase);
        return AddProgressUpdateResult.builder()
                .withTranslationCase(translationCaseModel)
                .build();
    }
}
