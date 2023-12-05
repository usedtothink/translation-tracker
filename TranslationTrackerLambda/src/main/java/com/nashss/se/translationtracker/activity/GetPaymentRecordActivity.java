package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetPaymentRecordRequest;
import com.nashss.se.translationtracker.activity.results.GetPaymentRecordResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import com.nashss.se.translationtracker.model.PaymentRecordModel;

import javax.inject.Inject;

/**
 * Implementation of the GetPaymentRecord Activity for Translation Tracker's GetPaymentRecord API.
 *<p>
 * This API allows the customer to get one of their saved payment records.
 */
public class GetPaymentRecordActivity {
    private final PaymentRecordDao paymentDao;

    /**
     * Instantiates a new GetPaymentRecordActivity object.
     *
     * @param paymentDao PaymentRecordDao to access the payment record table.
     */
    @Inject
    public GetPaymentRecordActivity(PaymentRecordDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    /**
     * This method handles the incoming request by retrieving the payment record from the database.
     * <p>
     * It then returns the payment record;
     * <p>
     * If the payment record does not exist, this should throw a TranslationCaseNotFoundException.
     *
     * @param getPaymentRecordRequest request object containing the translation case ID and customer ID.
     * @return GetPaymentRecordResult result object containing the API defined {@link PaymentRecord}
     */
    public GetPaymentRecordResult handleRequest(final GetPaymentRecordRequest getPaymentRecordRequest) {
        String requestedId = getPaymentRecordRequest.getTranslationCaseId();
        String customerId = getPaymentRecordRequest.getCustomerId();
        PaymentRecord paymentRecord = paymentDao.getPaymentRecord(customerId, requestedId);
        PaymentRecordModel paymentRecordModel = new ModelConverter().toPaymentRecordModel(paymentRecord);

        return GetPaymentRecordResult.builder()
                .withPaymentRecord(paymentRecordModel)
                .build();
    }
}
