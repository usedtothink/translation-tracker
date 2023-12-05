package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetTranslationCaseActivityTest {
    private static final String CUSTOMER_ID = "customerId";
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    @Mock
    private TranslationCaseDao caseDao;
    private GetTranslationCaseActivity getTranslationCaseActivity;
    private TranslationCase translationCase;

    @BeforeEach
    void setup() {
        translationCase = getTranslationCase();
        openMocks(this);
        getTranslationCaseActivity = new GetTranslationCaseActivity(caseDao);
    }

    @Test
    void handleRequest_savedTranslationCaseFound_returnsTranslationCaseModelInResult() {
        // GIVEN
        when(caseDao.getTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID))
                .thenReturn(translationCase);

        GetTranslationCaseRequest request = GetTranslationCaseRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .build();

        // WHEN
        GetTranslationCaseResult result = getTranslationCaseActivity.handleRequest(request);

        // THEN
        assertEquals(CUSTOMER_ID, result.getTranslationCase().getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getTranslationCase().getTranslationCaseId());
        assertEquals(translationCase.getTranslationClientId(), result.getTranslationCase().getTranslationClientId());
        assertEquals(translationCase.getCaseNickname(), result.getTranslationCase().getCaseNickname());
        assertEquals(translationCase.getSourceTextTitle(), result.getTranslationCase().getSourceTextTitle());
        assertEquals(translationCase.getSourceTextAuthor(), result.getTranslationCase().getSourceTextAuthor());
        assertEquals(translationCase.getTranslatedTitle(), result.getTranslationCase().getTranslatedTitle());
        assertEquals(translationCase.getProjectType(), result.getTranslationCase().getProjectType());
        assertEquals(translationCase.getDueDate(), result.getTranslationCase().getDueDate());
        assertEquals(translationCase.getStartDate(), result.getTranslationCase().getStartDate());
        assertEquals(translationCase.getEndDate(), result.getTranslationCase().getEndDate());
        assertEquals(translationCase.getOpenCase(), result.getTranslationCase().getOpenCase());
        assertEquals(translationCase.getRushJob(), result.getTranslationCase().getRushJob());
        assertEquals(translationCase.getProgressLog(), result.getTranslationCase().getProgressLog());
        assertEquals(translationCase.getTotalWorkingHours(), result.getTranslationCase().getTotalWorkingHours());
        assertEquals(translationCase.getWordsPerHour(), result.getTranslationCase().getWordsPerHour());
    }

    private TranslationCase getTranslationCase() {
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

        return translationCase;
    }
}
