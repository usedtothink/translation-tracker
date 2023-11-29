package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.UpdateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.UpdateTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateTranslationCaseActivityTest {

    @Mock
    private TranslationCaseDao caseDao;
    private UpdateTranslationCaseActivity updateTranslationCaseActivity;
    private TranslationCase testTranslationCase;
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String CUSTOMER_ID = "customerId";

    @BeforeEach
    void setup() {
        openMocks(this);
        updateTranslationCaseActivity = new UpdateTranslationCaseActivity(caseDao);
        testTranslationCase = getTestTranslationCase();
    }

    @Test
    void handleRequest_updateAllValuesCustomerIdMatches_updatesCase() {
        // GIVEN
        String translationClientId = "updatedTranslationClientId";
        String sourceTextTitle = "updatedSourceTextTitle";
        String sourceTextAuthor = "updatedSourceTextAuthor";
        String translatedTitle = "updatedTranslatedTitle";
        String dueDate = "updatedDueDate";
        String startDate = "updatedStartDate";
        String endDate = "updatedEndDate";
        Boolean openCase = true;
        Boolean rushJob = true;
        Double totalWorkingHours = 3456.25;
        Double wordsPerHour = 453.5;

        UpdateTranslationCaseRequest request = UpdateTranslationCaseRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withTranslationClientId(translationClientId)
                .withSourceTextTitle(sourceTextTitle)
                .withSourceTextAuthor(sourceTextAuthor)
                .withTranslatedTitle(translatedTitle)
                .withDueDate(dueDate)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withOpenCase(openCase)
                .withRushJob(rushJob)
                .withTotalWorkingHours(totalWorkingHours)
                .withWordsPerHour(wordsPerHour)
                .build();

        when(caseDao.getTranslationCase(TRANSLATION_CASE_ID)).thenReturn(testTranslationCase);

        // WHEN
        UpdateTranslationCaseResult result = updateTranslationCaseActivity.handleRequest(request);

        // THEN
        assertEquals(CUSTOMER_ID, request.getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, request.getTranslationCaseId());
        assertEquals(translationClientId, request.getTranslationClientId());
        assertEquals(sourceTextTitle, request.getSourceTextTitle());
        assertEquals(sourceTextAuthor, request.getSourceTextAuthor());
        assertEquals(translatedTitle, request.getTranslatedTitle());
        assertEquals(dueDate, request.getDueDate());
        assertEquals(startDate, request.getStartDate());
        assertEquals(endDate, request.getEndDate());
        assertEquals(openCase, request.getOpenCase());
        assertEquals(rushJob, request.getRushJob());
        assertEquals(totalWorkingHours, request.getTotalWorkingHours());
        assertEquals(wordsPerHour, request.getWordsPerHour());
    }

    @Test
    void handleRequest_wrongCustomerId_throwsException() {
        // GIVEN
        UpdateTranslationCaseRequest request = UpdateTranslationCaseRequest.builder()
                .withCustomerId("NewCustomerId")
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .build();
        when(caseDao.getTranslationCase(TRANSLATION_CASE_ID)).thenReturn(testTranslationCase);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> updateTranslationCaseActivity.handleRequest(request));
    }

    private TranslationCase getTestTranslationCase() {
        String translationClientId = "translationClientId";
        String caseNickname = "caseNickname";
        String sourceTextTitle = "sourceTextTitle";
        String sourceTextAuthor = "sourceTextAuthor";
        String translatedTitle = "translatedTitle";
        ProjectType projectType = ProjectType.ACADEMIC;
        String dueDate = "01/01/2023";
        String startDate = "28/12/2022";
        String endDate = "31/12/2022";
        Boolean openCase = false;
        Boolean rushJob = false;
        List<ProgressUpdate> progressLog = new ArrayList<>(List.of(ProgressUpdate.builder().build()));
        Double totalWorkingHours = 3.2;
        Double wordsPerHour = 400.3;
        PaymentHistoryRecord paymentRecord = PaymentHistoryRecord.builder().build();

        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        translationCase.setTranslationClientId(translationClientId);
        translationCase.setCaseNickname(caseNickname);
        translationCase.setSourceTextTitle(sourceTextTitle);
        translationCase.setSourceTextAuthor(sourceTextAuthor);
        translationCase.setTranslatedTitle(translatedTitle);
        translationCase.setProjectType(projectType);
        translationCase.setDueDate(dueDate);
        translationCase.setStartDate(startDate);
        translationCase.setEndDate(endDate);
        translationCase.setOpenCase(openCase);
        translationCase.setRushJob(rushJob);
        translationCase.setProgressLog(progressLog);
        translationCase.setTotalWorkingHours(totalWorkingHours);
        translationCase.setWordsPerHour(wordsPerHour);
        translationCase.setPaymentRecord(paymentRecord);

        return translationCase;
    }

}