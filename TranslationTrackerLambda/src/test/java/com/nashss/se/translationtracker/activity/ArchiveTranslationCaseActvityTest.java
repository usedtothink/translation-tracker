package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.ArchiveTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ArchiveTranslationCaseActivityTest {
    private static final String CUSTOMER_ID = "customerId";
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    @Mock
    private TranslationCaseDao caseDao;
    @Mock
    private PaymentRecordDao paymentDao;
    private ArchiveTranslationCaseActvity archiveTranslationCaseActvity;

    @BeforeEach
    void setup() {
        openMocks(this);
        archiveTranslationCaseActvity = new ArchiveTranslationCaseActvity(caseDao, paymentDao);
    }

    @Test
    void handleRequest_validCustomerIdAndCaseId_returnsCase() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(caseDao.archiveTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        ArchiveTranslationCaseRequest request = ArchiveTranslationCaseRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .build();

        // WHEN
        ArchiveTranslationCaseResult result = archiveTranslationCaseActvity.handleRequest(request);

        // THEN
        verify(paymentDao).archivePaymentRecord(any(String.class), any(String.class));
        assertEquals(CUSTOMER_ID, result.getTranslationCase().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getTranslationCase().getTranslationCaseId());
    }

}