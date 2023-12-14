package com.nashss.se.translationtracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import com.nashss.se.translationtracker.exceptions.DuplicatePaymentRecordException;
import com.nashss.se.translationtracker.exceptions.PaymentRecordNotFoundException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
import com.nashss.se.translationtracker.exceptions.TranslationClientNotFoundException;
import com.nashss.se.translationtracker.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PaymentRecordDaoTest {
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String NON_EXISTENT_CASE_ID = "not a translationCaseId";
    private static final String TRANSLATION_CLIENT_ID = "translationClientId";
    private static final String CUSTOMER_ID = "customerId";
    private static final String WRONG_CUSTOMER_ID = "wrongCustomerId";

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    private PaymentRecordDao paymentDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        paymentDao = new PaymentRecordDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void createPaymentRecord_noExistingClient_callsSave() {
        // GIVEN
        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN
        paymentDao.createPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID);

        // THEN
        verify(dynamoDBMapper).save(any(PaymentRecord.class));
    }

    @Test
    public void createPaymentRecord_paymentRecordAlreadyExists_throwsException() {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN & THEN
        assertThrows(DuplicatePaymentRecordException.class, () ->
                paymentDao.createPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID));
    }

    @Test
    void getPaymentRecord_validCustomerIdValidClientId_returnsPaymentRecord() {
        // GIVEN
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN
        PaymentRecord result = paymentDao.getPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID);

        //THEN
        assertNotNull(result);
    }

    @Test
    void getPaymentRecord_translationCaseIdNotFound_throwsException() {
        // GIVEN
        when(dynamoDBMapper.load(PaymentRecord.class, NON_EXISTENT_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(PaymentRecordNotFoundException.class, () -> paymentDao.getPaymentRecord(CUSTOMER_ID,
                NON_EXISTENT_CASE_ID));
    }

    @Test
    void getPaymentRecord_wrongCustomerId_throwsException() {
        // GIVEN
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> paymentDao.getPaymentRecord(WRONG_CUSTOMER_ID,
                TRANSLATION_CASE_ID));
    }

    @Test
    void archivePaymentRecord_validCustomerIdValidTranslationCaseId_callsDeleteAndSave() {
        // GIVEN
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN
        Boolean result = paymentDao.archivePaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID);

        // THEN
        assertTrue(result);
        verify(dynamoDBMapper).save(any(PaymentRecord.class));
        verify(dynamoDBMapper).delete(any(PaymentRecord.class));
    }

    @Test
    void archivePaymentRecord_translationCaseIdNotFound_throwsException() {
        // GIVEN
        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> paymentDao
                .archivePaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID));
    }

    @Test
    void archivePaymentRecord_wrongCustomerId_throwsException() {
        // GIVEN
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(PaymentRecord.class, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> paymentDao
                .archivePaymentRecord(WRONG_CUSTOMER_ID, TRANSLATION_CASE_ID));
    }

    @Test
    public void saveTranslationCase_callsSave() {
        // GIVEN
        PaymentRecord paymentRecord = new PaymentRecord();
        // WHEN
        paymentDao.savePaymentRecord(paymentRecord);
        // THEN
        verify(dynamoDBMapper).save(paymentRecord);
    }
}