package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCaseUpdate;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ModelConverterTest {

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
    private List<TranslationCaseUpdate> progressLog = new ArrayList<>(List.of(TranslationCaseUpdate.builder().build()));
    private Double totalWorkingHours = 3.2;
    private Double wordsPerHour = 400.3;
    private PaymentHistoryRecord paymentRecord = PaymentHistoryRecord.builder().build();

    private ModelConverter modelConverter = new ModelConverter();
    private TranslationCase translationCase = new TranslationCase();

    @BeforeEach
    public void setup() {
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
    }

    @Test
    void toTranslationCaseModel() {
        // GIVEN & WHEN
        TranslationCaseModel caseModel = modelConverter.toTranslationCaseModel(translationCase);

        // THEN
        assertEquals(translationCase.getTranslationCaseId(), caseModel.getTranslationCaseId());
        assertEquals(translationCase.getTranslationClientId(), caseModel.getTranslationClientId());
        assertEquals(translationCase.getCaseNickname(), caseModel.getCaseNickname());
        assertEquals(translationCase.getSourceTextTitle(), caseModel.getSourceTextTitle());
        assertEquals(translationCase.getSourceTextAuthor(), caseModel.getSourceTextAuthor());
        assertEquals(translationCase.getTranslatedTitle(), caseModel.getTranslatedTitle());
        assertEquals(translationCase.getProjectType(), caseModel.getProjectType());
        assertEquals(translationCase.getDueDate(), caseModel.getDueDate());
        assertEquals(translationCase.getStartDate(), caseModel.getStartDate());
        assertEquals(translationCase.getEndDate(), caseModel.getEndDate());
        assertEquals(translationCase.getOpenCase(), caseModel.getOpenCase());
        assertEquals(translationCase.getRushJob(), caseModel.getRushJob());
        assertEquals(translationCase.getProgressLog(), caseModel.getProgressLog());
        assertEquals(translationCase.getTotalWorkingHours(), caseModel.getTotalWorkingHours());
        assertEquals(translationCase.getWordsPerHour(), caseModel.getWordsPerHour());
        assertEquals(translationCase.getPaymentRecord(), caseModel.getPaymentRecord());
    }

    @Test
    void toTranslationCaseModelList() {
        // GIVEN
        List<TranslationCase> translationCaseList = new ArrayList<>(List.of(translationCase));

        // WHEN
        List<TranslationCaseModel> caseModelList = modelConverter.toTranslationCaseModelList(translationCaseList);

        // THEN
        assertEquals(translationCase.getTranslationCaseId(), caseModelList.get(0).getTranslationCaseId());
        assertEquals(translationCase.getTranslationClientId(), caseModelList.get(0).getTranslationClientId());
        assertEquals(translationCase.getCaseNickname(), caseModelList.get(0).getCaseNickname());
        assertEquals(translationCase.getSourceTextTitle(), caseModelList.get(0).getSourceTextTitle());
        assertEquals(translationCase.getSourceTextAuthor(), caseModelList.get(0).getSourceTextAuthor());
        assertEquals(translationCase.getTranslatedTitle(), caseModelList.get(0).getTranslatedTitle());
        assertEquals(translationCase.getProjectType(), caseModelList.get(0).getProjectType());
        assertEquals(translationCase.getDueDate(), caseModelList.get(0).getDueDate());
        assertEquals(translationCase.getStartDate(), caseModelList.get(0).getStartDate());
        assertEquals(translationCase.getEndDate(), caseModelList.get(0).getEndDate());
        assertEquals(translationCase.getOpenCase(), caseModelList.get(0).getOpenCase());
        assertEquals(translationCase.getRushJob(), caseModelList.get(0).getRushJob());
        assertEquals(translationCase.getProgressLog(), caseModelList.get(0).getProgressLog());
        assertEquals(translationCase.getTotalWorkingHours(), caseModelList.get(0).getTotalWorkingHours());
        assertEquals(translationCase.getWordsPerHour(), caseModelList.get(0).getWordsPerHour());
        assertEquals(translationCase.getPaymentRecord(), caseModelList.get(0).getPaymentRecord());

    }
}