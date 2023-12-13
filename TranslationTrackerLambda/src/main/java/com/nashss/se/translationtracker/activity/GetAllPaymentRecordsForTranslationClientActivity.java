package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetAllPaymentRecordsForTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetAllPaymentRecordsForTranslationClientResult;
import com.nashss.se.translationtracker.converters.ModelConverter;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import com.nashss.se.translationtracker.model.PaymentRecordModel;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetAllPaymentRecordsForClient Activity for Translation Tracker's
 * GetAllPaymentRecordsForClient API.
 * <p>
 * This API allows the customer to get a list of all payment records associated with a translationClientId.
 */
public class GetAllPaymentRecordsForTranslationClientActivity {
    private final PaymentRecordDao paymentDao;

    /**
     * Instantiates a new GetAllPaymentRecordsForClientActivity object.
     *
     * @param paymentDao PaymentRecordDao to access the payment record table.
     */
    @Inject
    public GetAllPaymentRecordsForTranslationClientActivity(PaymentRecordDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    /**
     * This method handles the incoming request by retrieving all the payment records from the database that are
     * associated with the translationClientId.
     * <p>
     * It then returns a list of all payment records associated with the translationClientId.
     * <p>
     * If there are no payment records associated with that customerId, returns an empty list.
     *
     * @param getAllPaymentRecordsForTranslationClientRequest request object containing the customerId and
     *                                                        translationClientId.
     * @return etAllPaymentRecordsForClientResult result object containing a list of
     * the API defined {@link PaymentRecordModel}
     */
    public GetAllPaymentRecordsForTranslationClientResult handleRequest(
            final GetAllPaymentRecordsForTranslationClientRequest getAllPaymentRecordsForTranslationClientRequest) {
        String customerId = getAllPaymentRecordsForTranslationClientRequest.getCustomerId();
        String translationClientId = getAllPaymentRecordsForTranslationClientRequest.getTranslationClientId();
        List<PaymentRecord> paymentRecordList =
                paymentDao.getAllPaymentRecordsForTranslationClient(customerId, translationClientId);
        List<PaymentRecordModel> paymentRecordModelList = new ModelConverter()
                .toPaymentRecordModelList(paymentRecordList);

        return GetAllPaymentRecordsForTranslationClientResult.builder()
            .withPaymentRecordList(paymentRecordModelList)
            .build();
    }


}
