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

    @Mock
    private TranslationCaseDao caseDao;
    private GetTranslationCaseActivity getTranslationCaseActivity;
    private TranslationCase testTranslationCase;

    @BeforeEach
    void setup() {
        testTranslationCase = getTestTranslationCase();
        openMocks(this);
        getTranslationCaseActivity = new GetTranslationCaseActivity(caseDao);
    }

    @Test
    void handleRequest_savedTranslationCaseFound_returnsTranslationCaseModelInResult() {
        // GIVEN
        String expectedID = "ExpectedID";
        testTranslationCase.setTranslationCaseId(expectedID);

        when(caseDao.getTranslationCase(testTranslationCase.getCustomerId(), expectedID))
                .thenReturn(testTranslationCase);

        GetTranslationCaseRequest request = GetTranslationCaseRequest.builder()
                .withTranslationCaseId(expectedID)
                .build();

        // WHEN
        GetTranslationCaseResult result = getTranslationCaseActivity.handleRequest(request);

        // THEN
        assertEquals(expectedID, result.getTranslationCase().getTranslationCaseId());
        assertEquals(testTranslationCase.getTranslationClientId(), result.getTranslationCase().getTranslationClientId());
        assertEquals(testTranslationCase.getCaseNickname(), result.getTranslationCase().getCaseNickname());
        assertEquals(testTranslationCase.getSourceTextTitle(), result.getTranslationCase().getSourceTextTitle());
        assertEquals(testTranslationCase.getSourceTextAuthor(), result.getTranslationCase().getSourceTextAuthor());
        assertEquals(testTranslationCase.getTranslatedTitle(), result.getTranslationCase().getTranslatedTitle());
        assertEquals(testTranslationCase.getProjectType(), result.getTranslationCase().getProjectType());
        assertEquals(testTranslationCase.getDueDate(), result.getTranslationCase().getDueDate());
        assertEquals(testTranslationCase.getStartDate(), result.getTranslationCase().getStartDate());
        assertEquals(testTranslationCase.getEndDate(), result.getTranslationCase().getEndDate());
        assertEquals(testTranslationCase.getOpenCase(), result.getTranslationCase().getOpenCase());
        assertEquals(testTranslationCase.getRushJob(), result.getTranslationCase().getRushJob());
        assertEquals(testTranslationCase.getProgressLog(), result.getTranslationCase().getProgressLog());
        assertEquals(testTranslationCase.getTotalWorkingHours(), result.getTranslationCase().getTotalWorkingHours());
        assertEquals(testTranslationCase.getWordsPerHour(), result.getTranslationCase().getWordsPerHour());
        assertEquals(testTranslationCase.getPaymentRecord(), result.getTranslationCase().getPaymentRecord());
    }

    private TranslationCase getTestTranslationCase() {
        String translationCaseId = "translationCaseId";
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

        return translationCase;
    }
}
