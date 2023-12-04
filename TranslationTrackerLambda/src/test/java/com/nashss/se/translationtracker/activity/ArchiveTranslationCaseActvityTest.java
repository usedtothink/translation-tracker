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

    @Mock
    private TranslationCaseDao caseDao;
    @Mock
    private PaymentRecordDao paymentRecordDao;
    private ArchiveTranslationCaseActvity archiveTranslationCaseActvity;

    @BeforeEach
    void setup() {
        openMocks(this);
        archiveTranslationCaseActvity = new ArchiveTranslationCaseActvity(caseDao, paymentRecordDao);
    }

    @Test
    void handleRequest_validCustomerIdAndCaseId_returnsCase() {
        // GIVEN
        String customerId = "customerId";
        String translationCaseId = "translationCaseId";

        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(customerId);
        translationCase.setTranslationCaseId(translationCaseId);

        when(caseDao.archiveTranslationCase(customerId, translationCaseId)).thenReturn(translationCase);

        ArchiveTranslationCaseRequest request = ArchiveTranslationCaseRequest.builder()
                .withCustomerId(customerId)
                .withTranslationCaseId(translationCaseId)
                .build();

        // WHEN
        ArchiveTranslationCaseResult result = archiveTranslationCaseActvity.handleRequest(request);

        // THEN
        verify(paymentRecordDao).archivePaymentRecord(any(String.class), any(String.class));
        assertEquals(customerId, result.getTranslationCase().getCustomerId());
        assertEquals(translationCaseId, result.getTranslationCase().getTranslationCaseId());
    }

}