package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.AddProgressUpdateRequest;
import com.nashss.se.translationtracker.activity.results.AddProgressUpdateResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AddProgressUpdateActivityTest {
    @Mock
    private TranslationCaseDao caseDao;
    private AddProgressUpdateActivity addProgressUpdateActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        addProgressUpdateActivity = new AddProgressUpdateActivity(caseDao);
    }

    @Test
    void handleRequest_addsProgressUpdateToTranslationCase_returnsUpdatedTranslationCase() {
        // GIVEN
        String customerId = "customerId";
        String translationCaseId = "translationCaseId";
        Integer wordCount = 55;
        String startDate = "startDate";
        String endDate = "endDate";
        String startTime = "startTime";
        String endTime = "endTime";
        String notes = "notes";

        ProgressUpdate progressUpdate = ProgressUpdate.builder()
                .withCustomerId(customerId)
                .withTranslationCaseId(translationCaseId)
                .withWordCount(wordCount)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withStartTime(startTime)
                .withEndTime(endTime)
                .withNotes(notes)
                .build();

        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(customerId);
        translationCase.setTranslationCaseId(translationCaseId);
        translationCase.setProgressLog(List.of(progressUpdate));

        when(caseDao.addProgressUpdate(any(ProgressUpdate.class))).thenReturn(translationCase);

        AddProgressUpdateRequest request = AddProgressUpdateRequest.builder()
                .withCustomerId(customerId)
                .withTranslationCaseId(translationCaseId)
                .withWordCount(wordCount)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withStartTime(startTime)
                .withEndTime(endTime)
                .withNotes(notes)
                .build();

        // WHEN
        AddProgressUpdateResult result = addProgressUpdateActivity.handleRequest(request);

        // THEN
        assertEquals(customerId, result.getTranslationCase().getProgressLog().get(0).getCustomerId());
        assertEquals(translationCaseId, result.getTranslationCase().getProgressLog().get(0).getTranslationCaseId());
        assertEquals(wordCount, result.getTranslationCase().getProgressLog().get(0).getWordCount());
        assertEquals(startDate, result.getTranslationCase().getProgressLog().get(0).getStartDate());
        assertEquals(endDate, result.getTranslationCase().getProgressLog().get(0).getEndDate());
        assertEquals(startTime, result.getTranslationCase().getProgressLog().get(0).getStartTime());
        assertEquals(endTime, result.getTranslationCase().getProgressLog().get(0).getEndTime());
        assertEquals(notes, result.getTranslationCase().getProgressLog().get(0).getNotes());
    }

}