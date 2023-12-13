package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.UpdatePaymentRecordRequest;
import com.nashss.se.translationtracker.activity.results.UpdatePaymentRecordResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import com.nashss.se.translationtracker.model.PaymentRecordModel;

import javax.inject.Inject;

/**
 * Implementation of the UpdatePaymentRecord Activity for Translation Tracker's UpdatePaymentRecord API.
 */
public class UpdatePaymentRecordActivity {
    private final PaymentRecordDao paymentDao;

    /**
     * Instantiates a new UpdatePaymentRecordActivity object.
     *
     * @param paymentDao PaymentRecordDao to access the payment record table.
     */
    @Inject
    public UpdatePaymentRecordActivity(PaymentRecordDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    /**
     * This method handles the incoming request by retrieving the payment record, updating it
     * and persisting the updated payment record.
     * <p>
     * It then returns the updated payment record.
     * <p>
     * If the payment record does not exist, this should throw a TranslationCaseNotFoundException.
     *
     * @param updatePaymentRecordRequest Request object containing the translation case ID, customer ID, and other
     *                                     values for update.
     * @return updatePaymentRecordResult Result object containing the API defined {@link PaymentRecordModel}
     */
    public UpdatePaymentRecordResult handleRequest(final UpdatePaymentRecordRequest updatePaymentRecordRequest) {
        String requestedTranslationCaseId = updatePaymentRecordRequest.getTranslationCaseId();
        String customerId = updatePaymentRecordRequest.getCustomerId();
        PaymentRecord paymentRecord = paymentDao.getPaymentRecord(customerId, requestedTranslationCaseId);

        if (updatePaymentRecordRequest.getTranslationCaseNickname() != null) {
            paymentRecord.setTranslationCaseNickname(updatePaymentRecordRequest.getTranslationCaseNickname());
        }

        if (updatePaymentRecordRequest.getTranslationClientId() != null) {
            paymentRecord.setTranslationClientId(updatePaymentRecordRequest.getTranslationClientId());
        }

        if (updatePaymentRecordRequest.getTranslationClientName() != null) {
            paymentRecord.setTranslationClientName(updatePaymentRecordRequest.getTranslationClientName());
        }

        if (updatePaymentRecordRequest.getCasePaid() != null) {
            paymentRecord.setCasePaid(updatePaymentRecordRequest.getCasePaid());
        }

        if (updatePaymentRecordRequest.getPaymentDate() != null) {
            paymentRecord.setPaymentDate(updatePaymentRecordRequest.getPaymentDate());
        }

        if (updatePaymentRecordRequest.getOnTime() != null) {
            paymentRecord.setOnTime(updatePaymentRecordRequest.getOnTime());
        }

        if (updatePaymentRecordRequest.getGrossPayment() != null) {
            paymentRecord.setGrossPayment(updatePaymentRecordRequest.getGrossPayment());
        }

        if (updatePaymentRecordRequest.getTaxRate() != null) {
            paymentRecord.setTaxRate(updatePaymentRecordRequest.getTaxRate());
        }

        if (updatePaymentRecordRequest.getPayRate() != null) {
            paymentRecord.setPayRate(updatePaymentRecordRequest.getPayRate());
        }

        if (updatePaymentRecordRequest.getPayRateUnit() != null) {
            paymentRecord.setPayRateUnit(updatePaymentRecordRequest.getPayRateUnit());
        }

        if (updatePaymentRecordRequest.getWordCount() != null) {
            paymentRecord.setWordCount(updatePaymentRecordRequest.getWordCount());
        }

        if (updatePaymentRecordRequest.getWordCountUnit() != null) {
            paymentRecord.setWordCountUnit(updatePaymentRecordRequest.getWordCountUnit());
        }

        paymentDao.savePaymentRecord(paymentRecord);

        return UpdatePaymentRecordResult.builder()
                .withPaymentRecord(new ModelConverter().toPaymentRecordModel(paymentRecord))
                .build();
    }
}
