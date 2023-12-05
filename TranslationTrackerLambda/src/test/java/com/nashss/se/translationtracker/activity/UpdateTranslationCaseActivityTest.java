package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.UpdateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.UpdateTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateTranslationCaseActivityTest {
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String CUSTOMER_ID = "customerId";
    private String translationClientId = "translationClientId";
    private String caseNickname = "caseNickname";
    private String sourceTextTitle = "sourceTextTitle";
    private String sourceTextAuthor = "sourceTextAuthor";
    private String translatedTitle = "translatedTitle";
    private ProjectType projectType = ProjectType.ACADEMIC;
    private String dueDate = "01/01/2023";
    private String startDate = "28/12/2022";
    private String endDate = "31/12/2022";
    private Boolean openCase = false;
    private Boolean rushJob = false;
    private List<ProgressUpdate> progressLog = new ArrayList<>(List.of(ProgressUpdate.builder().build()));
    private Double totalWorkingHours = 3.2;
    private Double wordsPerHour = 400.3;
    @Mock
    private TranslationCaseDao caseDao;
    private UpdateTranslationCaseActivity updateTranslationCaseActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        updateTranslationCaseActivity = new UpdateTranslationCaseActivity(caseDao);
    }

    @Test
    void handleRequest_allValuesExistingUpdateAllValues_returnsUpdatedCase() {
        // GIVEN
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

        String updatedTranslationClientId = "updatedTranslationClientId";
        String updatedSourceTextTitle = "updatedSourceTextTitle";
        String updatedSourceTextAuthor = "updatedSourceTextAuthor";
        String updatedTranslatedTitle = "updatedTranslatedTitle";
        String updatedDueDate = "updatedDueDate";
        String updatedStartDate = "updatedStartDate";
        String updatedEndDate = "updatedEndDate";
        Boolean updatedOpenCase = true;
        Boolean updatedRushJob = true;
        Double updatedTotalWorkingHours = 3456.25;
        Double updatedWordsPerHour = 453.5;

        UpdateTranslationCaseRequest request = UpdateTranslationCaseRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withTranslationClientId(updatedTranslationClientId)
                .withSourceTextTitle(updatedSourceTextTitle)
                .withSourceTextAuthor(updatedSourceTextAuthor)
                .withTranslatedTitle(updatedTranslatedTitle)
                .withDueDate(updatedDueDate)
                .withStartDate(updatedStartDate)
                .withEndDate(updatedEndDate)
                .withOpenCase(updatedOpenCase)
                .withRushJob(updatedRushJob)
                .withTotalWorkingHours(updatedTotalWorkingHours)
                .withWordsPerHour(updatedWordsPerHour)
                .build();

        when(caseDao.getTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        UpdateTranslationCaseResult result = updateTranslationCaseActivity.handleRequest(request);

        // THEN
        verify(caseDao).saveTranslationCase(any(TranslationCase.class));
        assertEquals(CUSTOMER_ID, result.getTranslationCase().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getTranslationCase().getTranslationCaseId());
        assertEquals(updatedTranslationClientId, result.getTranslationCase().getTranslationClientId());
        assertEquals(caseNickname, result.getTranslationCase().getCaseNickname());
        assertEquals(updatedSourceTextTitle, result.getTranslationCase().getSourceTextTitle());
        assertEquals(updatedSourceTextAuthor, result.getTranslationCase().getSourceTextAuthor());
        assertEquals(updatedTranslatedTitle, result.getTranslationCase().getTranslatedTitle());
        assertEquals(projectType, result.getTranslationCase().getProjectType());
        assertEquals(updatedDueDate, result.getTranslationCase().getDueDate());
        assertEquals(updatedStartDate, result.getTranslationCase().getStartDate());
        assertEquals(updatedEndDate, result.getTranslationCase().getEndDate());
        assertEquals(updatedOpenCase, result.getTranslationCase().getOpenCase());
        assertEquals(updatedRushJob, result.getTranslationCase().getRushJob());
        assertEquals(updatedTotalWorkingHours, result.getTranslationCase().getTotalWorkingHours());
        assertEquals(updatedWordsPerHour, result.getTranslationCase().getWordsPerHour());
    }

    @Test
    void handleRequest_noExistingValuesUpdateAllValues_returnsUpdatedCase() {
        // GIVEN
        String updatedTranslationClientId = "updatedTranslationClientId";
        String updatedSourceTextTitle = "updatedSourceTextTitle";
        String updatedSourceTextAuthor = "updatedSourceTextAuthor";
        String updatedTranslatedTitle = "updatedTranslatedTitle";
        String updatedDueDate = "updatedDueDate";
        String updatedStartDate = "updatedStartDate";
        String updatedEndDate = "updatedEndDate";
        Boolean updatedOpenCase = true;
        Boolean updatedRushJob = true;
        Double updatedTotalWorkingHours = 3456.25;
        Double updatedWordsPerHour = 453.5;

        UpdateTranslationCaseRequest request = UpdateTranslationCaseRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withTranslationClientId(updatedTranslationClientId)
                .withSourceTextTitle(updatedSourceTextTitle)
                .withSourceTextAuthor(updatedSourceTextAuthor)
                .withTranslatedTitle(updatedTranslatedTitle)
                .withDueDate(updatedDueDate)
                .withStartDate(updatedStartDate)
                .withEndDate(updatedEndDate)
                .withOpenCase(updatedOpenCase)
                .withRushJob(updatedRushJob)
                .withTotalWorkingHours(updatedTotalWorkingHours)
                .withWordsPerHour(updatedWordsPerHour)
                .build();

        TranslationCase emptyTranslationCase = new TranslationCase();
        emptyTranslationCase.setCustomerId(CUSTOMER_ID);
        emptyTranslationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        emptyTranslationCase.setCaseNickname(caseNickname);
        emptyTranslationCase.setProjectType(projectType);

        when(caseDao.getTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID)).thenReturn(emptyTranslationCase);

        // WHEN
        UpdateTranslationCaseResult result = updateTranslationCaseActivity.handleRequest(request);

        // THEN
        verify(caseDao).saveTranslationCase(any(TranslationCase.class));
        assertEquals(CUSTOMER_ID, result.getTranslationCase().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getTranslationCase().getTranslationCaseId());
        assertEquals(updatedTranslationClientId, result.getTranslationCase().getTranslationClientId());
        assertEquals(caseNickname, result.getTranslationCase().getCaseNickname());
        assertEquals(updatedSourceTextTitle, result.getTranslationCase().getSourceTextTitle());
        assertEquals(updatedSourceTextAuthor, result.getTranslationCase().getSourceTextAuthor());
        assertEquals(updatedTranslatedTitle, result.getTranslationCase().getTranslatedTitle());
        assertEquals(projectType, result.getTranslationCase().getProjectType());
        assertEquals(updatedDueDate, result.getTranslationCase().getDueDate());
        assertEquals(updatedStartDate, result.getTranslationCase().getStartDate());
        assertEquals(updatedEndDate, result.getTranslationCase().getEndDate());
        assertEquals(updatedOpenCase, result.getTranslationCase().getOpenCase());
        assertEquals(updatedRushJob, result.getTranslationCase().getRushJob());
        assertEquals(updatedTotalWorkingHours, result.getTranslationCase().getTotalWorkingHours());
        assertEquals(updatedWordsPerHour, result.getTranslationCase().getWordsPerHour());
    }
}