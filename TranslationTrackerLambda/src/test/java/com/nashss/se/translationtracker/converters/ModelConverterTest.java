package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.model.TranslationClientModel;
import com.nashss.se.translationtracker.types.ClientType;
import com.nashss.se.translationtracker.types.ProjectType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelConverterTest {
    // Translation case data
    private String customerId = "customerId";
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

    // Translation client data
    private String translationClientName = "translationClientName";
    private ClientType translationClientType = ClientType.GOVERNMENT;


    private ModelConverter modelConverter = new ModelConverter();
    private TranslationCase translationCase = new TranslationCase();
    private TranslationClient translationClient = new TranslationClient();

    @BeforeEach
    public void setup() {
        translationCase.setCustomerId(customerId);
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

        translationClient.setCustomerId(customerId);
        translationClient.setTranslationClientId(translationClientId);
        translationClient.setTranslationClientName(translationClientName);
        translationClient.setClientType(translationClientType);
    }

    @Test
    void toTranslationCaseModel() {
        // GIVEN & WHEN
        TranslationCaseModel caseModel = modelConverter.toTranslationCaseModel(translationCase);

        // THEN
        assertEquals(customerId, caseModel.getCustomerId());
        assertEquals(translationCaseId, caseModel.getTranslationCaseId());
        assertEquals(translationClientId, caseModel.getTranslationClientId());
        assertEquals(caseNickname, caseModel.getCaseNickname());
        assertEquals(sourceTextTitle, caseModel.getSourceTextTitle());
        assertEquals(sourceTextAuthor, caseModel.getSourceTextAuthor());
        assertEquals(translatedTitle, caseModel.getTranslatedTitle());
        assertEquals(projectType, caseModel.getProjectType());
        assertEquals(dueDate, caseModel.getDueDate());
        assertEquals(startDate, caseModel.getStartDate());
        assertEquals(endDate, caseModel.getEndDate());
        assertEquals(openCase, caseModel.getOpenCase());
        assertEquals(rushJob, caseModel.getRushJob());
        assertEquals(progressLog, caseModel.getProgressLog());
        assertEquals(totalWorkingHours, caseModel.getTotalWorkingHours());
        assertEquals(wordsPerHour, caseModel.getWordsPerHour());
    }

    @Test
    void toTranslationCaseModelList() {
        // GIVEN
        List<TranslationCase> translationCaseList = new ArrayList<>(List.of(translationCase));

        // WHEN
        List<TranslationCaseModel> caseModelList = modelConverter.toTranslationCaseModelList(translationCaseList);

        // THEN
        assertEquals(translationCaseId, caseModelList.get(0).getTranslationCaseId());
        assertEquals(translationClientId, caseModelList.get(0).getTranslationClientId());
        assertEquals(caseNickname, caseModelList.get(0).getCaseNickname());
        assertEquals(sourceTextTitle, caseModelList.get(0).getSourceTextTitle());
        assertEquals(sourceTextAuthor, caseModelList.get(0).getSourceTextAuthor());
        assertEquals(translatedTitle, caseModelList.get(0).getTranslatedTitle());
        assertEquals(projectType, caseModelList.get(0).getProjectType());
        assertEquals(dueDate, caseModelList.get(0).getDueDate());
        assertEquals(startDate, caseModelList.get(0).getStartDate());
        assertEquals(endDate, caseModelList.get(0).getEndDate());
        assertEquals(openCase, caseModelList.get(0).getOpenCase());
        assertEquals(rushJob, caseModelList.get(0).getRushJob());
        assertEquals(progressLog, caseModelList.get(0).getProgressLog());
        assertEquals(totalWorkingHours, caseModelList.get(0).getTotalWorkingHours());
        assertEquals(wordsPerHour, caseModelList.get(0).getWordsPerHour());
    }

    @Test
    void toTranslationClientModel() {
        // GIVEN
        List<TranslationClient> translationClientList = List.of(translationClient);

        // WHEN
        List<TranslationClientModel> translationClientModelList = new ModelConverter()
                .toTranslationClientModelList(translationClientList);

        // THEN
        assertEquals(customerId, translationClientModelList.get(0).getCustomerId());
        assertEquals(translationClientId, translationClientModelList.get(0).getTranslationClientId());
        assertEquals(translationClientName, translationClientModelList.get(0).getTranslationClientName());
        assertEquals(translationClientType, translationClientModelList.get(0).getClientType());
    }

    @Test
    void toTranslationClientModelList() {
        // GIVEN & WHEN
        TranslationClientModel translationClientModel = new ModelConverter()
                .toTranslationClientModel(translationClient);

        // THEN
        assertEquals(customerId , translationClientModel.getCustomerId());
        assertEquals(translationClientId, translationClientModel.getTranslationClientId());
        assertEquals(translationClientName, translationClientModel.getTranslationClientName());
        assertEquals(translationClientType, translationClientModel.getClientType());
    }
}