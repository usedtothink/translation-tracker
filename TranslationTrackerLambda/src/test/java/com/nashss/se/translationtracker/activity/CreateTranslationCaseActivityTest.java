package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.PaymentRecordDao;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.types.ProjectType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateTranslationCaseActivityTest {
    private static final String CUSTOMER_ID = "customerId";
    private static final String CASE_NICKNAME = "caseNickname";
    private static final ProjectType PROJECT_TYPE = ProjectType.ACADEMIC;
    @Mock
    private TranslationCaseDao caseDao;
    @Mock
    private PaymentRecordDao paymentRecordDao;
    private CreateTranslationCaseActivity createTranslationCaseActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createTranslationCaseActivity = new CreateTranslationCaseActivity(caseDao, paymentRecordDao);
    }

    @Test
    void handleRequest_createsAndSavesTranslationCase() {
        // GIVEN
        CreateTranslationCaseRequest request = CreateTranslationCaseRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withCaseNickname(CASE_NICKNAME)
                .withProjectType(PROJECT_TYPE.name())
                .build();

        // WHEN
        CreateTranslationCaseResult result = createTranslationCaseActivity.handleRequest(request);

        // THEN
        verify(caseDao).createTranslationCase(any(TranslationCase.class));
        verify(paymentRecordDao).createPaymentRecord(any(String.class), any(String.class));

        assertNotNull(result.getTranslationCase().getTranslationCaseId());
        assertEquals(CUSTOMER_ID, result.getTranslationCase().getCustomerId());
        assertEquals(CASE_NICKNAME, result.getTranslationCase().getCaseNickname());
        assertEquals(PROJECT_TYPE, result.getTranslationCase().getProjectType());
    }
}
