package com.nashss.se.translationtracker.model;

import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCaseUpdate;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranslationCaseModelTest {


    @Test
    public void basicTestForTranslationCaseModel() {
        // GIVEN
        String customerId = "customerId";
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
        List<TranslationCaseUpdate> progressLog = new ArrayList<>(List.of(TranslationCaseUpdate.builder().build()));
        Double totalWorkingHours = 3.2;
        Double wordsPerHour = 400.3;
        PaymentHistoryRecord paymentRecord = PaymentHistoryRecord.builder().build();

        TranslationCaseModel model = TranslationCaseModel.builder()
                .withCustomerId(customerId)
                .withTranslationCaseId(translationCaseId)
                .withTranslationClientId(translationClientId)
                .withCaseNickname(caseNickname)
                .withSourceTextTitle(sourceTextTitle)
                .withSourceTextAuthor(sourceTextAuthor)
                .withTranslatedTitle(translatedTitle)
                .withProjectType(projectType)
                .withDueDate(dueDate)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withOpenCase(openCase)
                .withRushJob(rushJob)
                .withProgressLog(progressLog)
                .withTotalWorkingHours(totalWorkingHours)
                .withWordsPerHour(wordsPerHour)
                .withPaymentHistoryRecord(paymentRecord)
                .build();

        // WHEN & THEN
        assertEquals(customerId, model.getCustomerId());
        assertEquals(translationCaseId, model.getTranslationCaseId());
        assertEquals(translationClientId, model.getTranslationClientId());
        assertEquals(caseNickname, model.getCaseNickname());
        assertEquals(sourceTextTitle, model.getSourceTextTitle());
        assertEquals(sourceTextAuthor, model.getSourceTextAuthor());
        assertEquals(translatedTitle, model.getTranslatedTitle());
        assertEquals(projectType, model.getProjectType());
        assertEquals(dueDate, model.getDueDate());
        assertEquals(startDate, model.getStartDate());
        assertEquals(endDate, model.getEndDate());
        assertEquals(openCase, model.getOpenCase());
        assertEquals(rushJob, model.getRushJob());
        assertEquals(progressLog, model.getProgressLog());
        assertEquals(totalWorkingHours, model.getTotalWorkingHours());
        assertEquals(wordsPerHour, model.getWordsPerHour());
        assertEquals(paymentRecord, model.getPaymentRecord());
    }



}