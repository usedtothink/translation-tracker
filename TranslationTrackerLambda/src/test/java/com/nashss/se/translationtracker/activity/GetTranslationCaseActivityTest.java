package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;
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
    private String translationCaseId = "translationCaseId";
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
    private PaymentHistoryRecord paymentRecord = PaymentHistoryRecord.builder().build();
    private TranslationCase translationCase = new TranslationCase();

    @Mock
    private TranslationCaseDao caseDao;
    private GetTranslationCaseActivity getTranslationCaseActivity;

    @BeforeEach
    void setup() {
        translationCase.setTranslationCaseId(translationCaseId);
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

        openMocks(this);
        getTranslationCaseActivity = new GetTranslationCaseActivity(caseDao);
    }

    @Test
    void handleRequest_savedTranslationCaseFound_returnsTranslationCaseModelInResult() {
        // GIVEN
        String expectedID = "ExpectedID";
        translationCase.setTranslationCaseId(expectedID);

        when(caseDao.getTranslationCase(expectedID)).thenReturn(translationCase);

        GetTranslationCaseRequest request = GetTranslationCaseRequest.builder()
                .withTranslationCaseId(expectedID)
                .build();

        // WHEN
        GetTranslationCaseResult result = getTranslationCaseActivity.handleRequest(request);

        // THEN
        assertEquals(expectedID, result.getTranslationCase().getTranslationCaseId());
        assertEquals(translationClientId, result.getTranslationCase().getTranslationClientId());
        assertEquals(caseNickname, result.getTranslationCase().getCaseNickname());
        assertEquals(sourceTextTitle, result.getTranslationCase().getSourceTextTitle());
        assertEquals(sourceTextAuthor, result.getTranslationCase().getSourceTextAuthor());
        assertEquals(translatedTitle, result.getTranslationCase().getTranslatedTitle());
        assertEquals(projectType, result.getTranslationCase().getProjectType());
        assertEquals(dueDate, result.getTranslationCase().getDueDate());
        assertEquals(startDate, result.getTranslationCase().getStartDate());
        assertEquals(endDate, result.getTranslationCase().getEndDate());
        assertEquals(openCase, result.getTranslationCase().getOpenCase());
        assertEquals(rushJob, result.getTranslationCase().getRushJob());
        assertEquals(progressLog, result.getTranslationCase().getProgressLog());
        assertEquals(totalWorkingHours, result.getTranslationCase().getTotalWorkingHours());
        assertEquals(wordsPerHour, result.getTranslationCase().getWordsPerHour());
        assertEquals(paymentRecord, result.getTranslationCase().getPaymentRecord());
    }
}
