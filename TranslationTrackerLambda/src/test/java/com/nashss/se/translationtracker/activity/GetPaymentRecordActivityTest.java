package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetPaymentRecordRequest;
import com.nashss.se.translationtracker.activity.results.GetPaymentRecordResult;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetPaymentRecordActivityTest {
    private static final String CUSTOMER_ID = "customerId";
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    @Mock
    private PaymentRecordDao paymentDao;
    private GetPaymentRecordActivity getPaymentRecordActivity;
    private PaymentRecord paymentRecord;

    @BeforeEach
    void setup() {
        paymentRecord = getTestPaymentRecord();
        openMocks(this);
        getPaymentRecordActivity = new GetPaymentRecordActivity(paymentDao);
    }

    @Test
    void handleRequest_savedPaymentRecordFound_returnsPaymentRecordModelInResult() {
        // GIVEN
        GetPaymentRecordRequest request = GetPaymentRecordRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .build();

        when(paymentDao.getPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN
        GetPaymentRecordResult result = getPaymentRecordActivity.handleRequest(request);

        // THEN
        verify(paymentDao).getPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID);
        assertEquals(CUSTOMER_ID, result.getPaymentRecord().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getPaymentRecord().getTranslationCaseId());
        assertEquals(paymentRecord.getTranslationClientId(), result.getPaymentRecord().getTranslationClientId());
        assertEquals(paymentRecord.getCasePaid(), result.getPaymentRecord().getCasePaid());
        assertEquals(paymentRecord.getPaymentDate(), result.getPaymentRecord().getPaymentDate());
        assertEquals(paymentRecord.getOnTime(), result.getPaymentRecord().getOnTime());
        assertEquals(paymentRecord.getGrossPayment(), result.getPaymentRecord().getGrossPayment());
        assertEquals(paymentRecord.getTaxRate(), result.getPaymentRecord().getTaxRate());
        assertEquals(paymentRecord.getPayRate(), result.getPaymentRecord().getPayRate());
        assertEquals(paymentRecord.getPayRateUnit(), result.getPaymentRecord().getPayRateUnit());
        assertEquals(paymentRecord.getWordCount(), result.getPaymentRecord().getWordCount());
        assertEquals(paymentRecord.getWordCountUnit(), result.getPaymentRecord().getWordCountUnit());
    }

    private PaymentRecord getTestPaymentRecord() {
        String translationClientId = "translationClientId";
        Boolean casePaid = true;
        String paymentDate = "paymentDate";
        Boolean onTime = true;
        Double grossPayment = 133.0;
        Double taxRate = 2.1;
        Double payRate = 3.4;
        String payRateUnit = "payRateUnit";
        Integer wordCount = 33;
        String wordCountUnit = "wordCountUnit";

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);
        paymentRecord.setTranslationClientId(translationClientId);
        paymentRecord.setCasePaid(casePaid);
        paymentRecord.setPaymentDate(paymentDate);
        paymentRecord.setOnTime(onTime);
        paymentRecord.setGrossPayment(grossPayment);
        paymentRecord.setTaxRate(taxRate);
        paymentRecord.setPayRate(payRate);
        paymentRecord.setPayRateUnit(payRateUnit);
        paymentRecord.setWordCount(wordCount);
        paymentRecord.setWordCountUnit(wordCountUnit);

        return paymentRecord;
    }
}