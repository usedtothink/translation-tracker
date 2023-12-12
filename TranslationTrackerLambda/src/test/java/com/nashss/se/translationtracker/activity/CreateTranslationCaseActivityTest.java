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
        String customerId = "customerId";
        String caseNickname = "caseNickname";
        ProjectType projectType = ProjectType.ACADEMIC;
        String translationClientId = "translationClientId";
        String sourceTextTitle = "sourceTextTitle";
        String sourceTextAuthor = "sourceTextAuthor";
        String translatedTitle = "translatedTitle";
        String dueDate = "dueDate";
        String startDate = "startDate";
        String endDate = "endDate";
        Boolean openCase = true;
        Boolean rushJob = false;

        CreateTranslationCaseRequest request = CreateTranslationCaseRequest.builder()
                .withCustomerId(customerId)
                .withCaseNickname(caseNickname)
                .withProjectType(projectType.name())
                .withTranslationClientId(translationClientId)
                .withSourceTextTitle(sourceTextTitle)
                .withSourceTextAuthor(sourceTextAuthor)
                .withTranslatedTitle(translatedTitle)
                .withDueDate(dueDate)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withOpenCase(openCase)
                .withRushJob(rushJob)
                .build();

        // WHEN
        CreateTranslationCaseResult result = createTranslationCaseActivity.handleRequest(request);

        // THEN
        verify(caseDao).createTranslationCase(any(TranslationCase.class));
        verify(paymentRecordDao).createPaymentRecord(any(String.class), any(String.class));

        // Generated in the activity class
        assertNotNull(result.getTranslationCase().getTranslationCaseId());
        assertNotNull(result.getTranslationCase().getProgressLog());
        // Passed in values
        assertEquals(customerId, result.getTranslationCase().getCustomerId());
        assertEquals(caseNickname, result.getTranslationCase().getCaseNickname());
        assertEquals(projectType, result.getTranslationCase().getProjectType());
        assertEquals(translationClientId, result.getTranslationCase().getTranslationClientId());
        assertEquals(sourceTextTitle, result.getTranslationCase().getSourceTextTitle());
        assertEquals(sourceTextAuthor, result.getTranslationCase().getSourceTextAuthor());
        assertEquals(translatedTitle, result.getTranslationCase().getTranslatedTitle());
        assertEquals(dueDate, result.getTranslationCase().getDueDate());
        assertEquals(startDate, result.getTranslationCase().getStartDate());
        assertEquals(endDate, result.getTranslationCase().getEndDate());
        assertEquals(openCase, result.getTranslationCase().getOpenCase());
        assertEquals(rushJob, result.getTranslationCase().getRushJob());
    }
}
