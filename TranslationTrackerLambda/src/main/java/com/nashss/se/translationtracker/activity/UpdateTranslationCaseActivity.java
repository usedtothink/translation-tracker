package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.UpdatePaymentRecordRequest;
import com.nashss.se.translationtracker.activity.requests.UpdateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.UpdateTranslationCaseResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import javax.inject.Inject;

/**
 * Implementation of the UpdateTranslationCaseActivity for Translation Tracker's UpdateTranslationCase API.
 */
public class UpdateTranslationCaseActivity {
    private final TranslationCaseDao caseDao;
    private final UpdatePaymentRecordActivity updatePaymentRecordActivity;

    /**
     * Instantiates a new UpdateTranslationCaseActivity object.
     *
     * @param caseDao TranslationCaseDao to access the translation case table.
     * @param updatePaymentRecordActivity UpdatePaymentRecordActivity to update translationClientId.
     */
    @Inject
    public UpdateTranslationCaseActivity(TranslationCaseDao caseDao,
                                         UpdatePaymentRecordActivity updatePaymentRecordActivity) {
        this.caseDao = caseDao;
        this.updatePaymentRecordActivity = updatePaymentRecordActivity;
    }

    /**
     * This method handles the incoming request by retrieving the translation case, updating it
     * and persisting the updated translation case.
     * <p>
     * It then returns the updated translation case.
     * <p>
     * If the translation case does not exist, this should throw a TranslationCaseNotFoundException.
     *
     * @param updateTranslationCaseRequest Request object containing the translation case ID, customer ID, and other
     *                                     values for update.
     * @return updateTranslationCaseResult Result object containing the API defined {@link TranslationCaseModel}
     */
    public UpdateTranslationCaseResult handleRequest(final UpdateTranslationCaseRequest updateTranslationCaseRequest) {
        String requestedTranslationCaseId = updateTranslationCaseRequest.getTranslationCaseId();
        String customerId = updateTranslationCaseRequest.getCustomerId();
        TranslationCase translationCase = caseDao.getTranslationCase(customerId, requestedTranslationCaseId);

        // When the translation case TranslationClientId is set,
        // it should also be set in the corresponding PaymentRecord
        if (updateTranslationCaseRequest.getTranslationClientId() != null) {
            translationCase.setTranslationClientId(updateTranslationCaseRequest.getTranslationClientId());

            UpdatePaymentRecordRequest request = UpdatePaymentRecordRequest.builder()
                    .withCustomerId(updateTranslationCaseRequest.getCustomerId())
                    .withTranslationCaseId(updateTranslationCaseRequest.getTranslationCaseId())
                    .withTranslationClientId(updateTranslationCaseRequest.getTranslationClientId())
                    .build();

            updatePaymentRecordActivity.handleRequest(request);
        }

        if (updateTranslationCaseRequest.getSourceTextTitle() != null) {
            translationCase.setSourceTextTitle(updateTranslationCaseRequest.getSourceTextTitle());
        }

        if (updateTranslationCaseRequest.getSourceTextAuthor() != null) {
            translationCase.setSourceTextAuthor(updateTranslationCaseRequest.getSourceTextAuthor());
        }

        if (updateTranslationCaseRequest.getTranslatedTitle() != null) {
            translationCase.setTranslatedTitle(updateTranslationCaseRequest.getTranslatedTitle());
        }

        if (updateTranslationCaseRequest.getDueDate() != null) {
            translationCase.setDueDate(updateTranslationCaseRequest.getDueDate());
        }

        if (updateTranslationCaseRequest.getStartDate() != null) {
            translationCase.setStartDate(updateTranslationCaseRequest.getStartDate());
        }

        if (updateTranslationCaseRequest.getEndDate() != null) {
            translationCase.setEndDate(updateTranslationCaseRequest.getEndDate());
        }

        if (updateTranslationCaseRequest.getOpenCase() != null) {
            translationCase.setOpenCase(updateTranslationCaseRequest.getOpenCase());
        }

        if (updateTranslationCaseRequest.getRushJob() != null) {
            translationCase.setRushJob(updateTranslationCaseRequest.getRushJob());
        }

        if (updateTranslationCaseRequest.getTotalWorkingHours() != null) {
            translationCase.setTotalWorkingHours(updateTranslationCaseRequest.getTotalWorkingHours());
        }

        if (updateTranslationCaseRequest.getWordsPerHour() != null) {
            translationCase.setWordsPerHour(updateTranslationCaseRequest.getWordsPerHour());
        }

        caseDao.saveTranslationCase(translationCase);

        return UpdateTranslationCaseResult.builder()
                .withTranslationCase(new ModelConverter().toTranslationCaseModel(translationCase))
                .build();
    }
}
