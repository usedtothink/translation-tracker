package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.UpdatePaymentRecordRequest;
import com.nashss.se.translationtracker.activity.results.UpdatePaymentRecordResult;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdatePaymentRecordActivityTest {
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String CUSTOMER_ID = "customerId";
    private String translationClientId = "translationClientId";
    private Boolean casePaid = true;
    private String paymentDate = "paymentDate";
    private Boolean onTime = false;
    private Double grossPayment = 33.3;
    private Double taxRate = 2.4;
    private Double payRate = 4.5;
    private String payRateUnit = "payRateUnit";
    private Integer wordCount = 300;
    private String wordCountUnit = "wordCountUnit";
    @Mock
    private PaymentRecordDao paymentDao;
    private UpdatePaymentRecordActivity updatePaymentRecordActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        updatePaymentRecordActivity = new UpdatePaymentRecordActivity(paymentDao);
    }

    @Test
    void handleRequest_allValuesExistingUpdateAllValues_updatesPaymentRecord() {
        // GIVEN
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

        String updatedTranslationClientId = "updatedTranslationClientId";
        Boolean updatedCasePaid = true;
        String updatedPaymentDate = "updatedPaymentDate";
        Boolean updatedOnTime = false;
        Double updatedGrossPayment = 65.3;
        Double updatedTaxRate = 12.8;
        Double updatedPayRate = 2.9;
        String updatedPayRateUnit = "updatedPayRateUnit";
        Integer updatedWordCount = 8455;
        String updatedWordCountUnit = "updatedWordCountUnit";

        UpdatePaymentRecordRequest request = UpdatePaymentRecordRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withTranslationClientId(updatedTranslationClientId)
                .withCasePaid(updatedCasePaid)
                .withPaymentDate(updatedPaymentDate)
                .withOnTime(updatedOnTime)
                .withGrossPayment(updatedGrossPayment)
                .withTaxRate(updatedTaxRate)
                .withPayRate(updatedPayRate)
                .withPayRateUnit(updatedPayRateUnit)
                .withWordCount(updatedWordCount)
                .withWordCountUnit(updatedWordCountUnit)
                .build();

        when(paymentDao.getPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN
        UpdatePaymentRecordResult result = updatePaymentRecordActivity.handleRequest(request);

        // THEN
        verify(paymentDao).savePaymentRecord(any(PaymentRecord.class));
        assertEquals(CUSTOMER_ID, result.getPaymentRecord().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getPaymentRecord().getTranslationCaseId());
        assertEquals(updatedTranslationClientId, result.getPaymentRecord().getTranslationClientId());
        assertEquals(updatedCasePaid, result.getPaymentRecord().getCasePaid());
        assertEquals(updatedPaymentDate, result.getPaymentRecord().getPaymentDate());
        assertEquals(updatedOnTime, result.getPaymentRecord().getOnTime());
        assertEquals(updatedGrossPayment, result.getPaymentRecord().getGrossPayment());
        assertEquals(updatedTaxRate, result.getPaymentRecord().getTaxRate());
        assertEquals(updatedPayRate, result.getPaymentRecord().getPayRate());
        assertEquals(updatedPayRateUnit, result.getPaymentRecord().getPayRateUnit());
        assertEquals(updatedWordCount, result.getPaymentRecord().getWordCount());
        assertEquals(updatedWordCountUnit, result.getPaymentRecord().getWordCountUnit());
    }

    @Test
    void handleRequest_noExistingValuesUpdateAllValues_updatesPaymentRecord() {
        // GIVEN
        String updatedTranslationClientId = "updatedTranslationClientId";
        Boolean updatedCasePaid = true;
        String updatedPaymentDate = "updatedPaymentDate";
        Boolean updatedOnTime = false;
        Double updatedGrossPayment = 65.3;
        Double updatedTaxRate = 12.8;
        Double updatedPayRate = 2.9;
        String updatedPayRateUnit = "updatedPayRateUnit";
        Integer updatedWordCount = 8455;
        String updatedWordCountUnit = "updatedWordCountUnit";

        UpdatePaymentRecordRequest request = UpdatePaymentRecordRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withTranslationClientId(updatedTranslationClientId)
                .withCasePaid(updatedCasePaid)
                .withPaymentDate(updatedPaymentDate)
                .withOnTime(updatedOnTime)
                .withGrossPayment(updatedGrossPayment)
                .withTaxRate(updatedTaxRate)
                .withPayRate(updatedPayRate)
                .withPayRateUnit(updatedPayRateUnit)
                .withWordCount(updatedWordCount)
                .withWordCountUnit(updatedWordCountUnit)
                .build();

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setCustomerId(CUSTOMER_ID);
        paymentRecord.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(paymentDao.getPaymentRecord(CUSTOMER_ID, TRANSLATION_CASE_ID)).thenReturn(paymentRecord);

        // WHEN
        UpdatePaymentRecordResult result = updatePaymentRecordActivity.handleRequest(request);

        // THEN
        verify(paymentDao).savePaymentRecord(any(PaymentRecord.class));
        assertEquals(CUSTOMER_ID, result.getPaymentRecord().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getPaymentRecord().getTranslationCaseId());
        assertEquals(updatedTranslationClientId, result.getPaymentRecord().getTranslationClientId());
        assertEquals(updatedCasePaid, result.getPaymentRecord().getCasePaid());
        assertEquals(updatedPaymentDate, result.getPaymentRecord().getPaymentDate());
        assertEquals(updatedOnTime, result.getPaymentRecord().getOnTime());
        assertEquals(updatedGrossPayment, result.getPaymentRecord().getGrossPayment());
        assertEquals(updatedTaxRate, result.getPaymentRecord().getTaxRate());
        assertEquals(updatedPayRate, result.getPaymentRecord().getPayRate());
        assertEquals(updatedPayRateUnit, result.getPaymentRecord().getPayRateUnit());
        assertEquals(updatedWordCount, result.getPaymentRecord().getWordCount());
        assertEquals(updatedWordCountUnit, result.getPaymentRecord().getWordCountUnit());
    }

}
