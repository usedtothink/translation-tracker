package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationCasesRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationCasesResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetAllTranslationCasesActivityTest {

    @Mock
    private TranslationCaseDao caseDao;
    private GetAllTranslationCasesActivity getAllTranslationCasesActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllTranslationCasesActivity = new GetAllTranslationCasesActivity(caseDao);
    }

    @Test
    void handleRequest_returnsAllTranslationCases() {
        // GIVEN
        String customerId = "customerId";

        String caseId1 = "CaseId1";
        String caseId2 = "CaseId2";
        String caseId3 = "CaseId3";

        String dueDate1 = "DueDate1";
        String dueDate2 = "DueDate2";
        String dueDate3 = "DueDate3";

        Boolean openCase1 = true;
        Boolean openCase2 = true;
        Boolean openCase3 = false;

        ProjectType projectType1 = ProjectType.COMMERCIAL;
        ProjectType projectType2 = ProjectType.JUDICIAL;
        ProjectType projectType3 = ProjectType.LITERARY;

        String translatedTitle1 = "TranslatedTitle1";
        String translatedTitle2 = "TranslatedTitle2";
        String translatedTitle3 = "TranslatedTitle3";

        TranslationCase translationCase1 = new TranslationCase();
        translationCase1.setCustomerId(customerId);
        translationCase1.setTranslationCaseId(caseId1);
        translationCase1.setDueDate(dueDate1);
        translationCase1.setOpenCase(openCase1);
        translationCase1.setProjectType(projectType1);
        translationCase1.setTranslatedTitle(translatedTitle1);

        TranslationCase translationCase2 = new TranslationCase();
        translationCase2.setCustomerId(customerId);
        translationCase2.setTranslationCaseId(caseId2);
        translationCase2.setDueDate(dueDate2);
        translationCase2.setOpenCase(openCase2);
        translationCase2.setProjectType(projectType2);
        translationCase2.setTranslatedTitle(translatedTitle2);

        TranslationCase translationCase3 = new TranslationCase();
        translationCase3.setCustomerId(customerId);
        translationCase3.setTranslationCaseId(caseId3);
        translationCase3.setDueDate(dueDate3);
        translationCase3.setOpenCase(openCase3);
        translationCase3.setProjectType(projectType3);
        translationCase3.setTranslatedTitle(translatedTitle3);

        List<TranslationCase> listOfCases = List.of(translationCase1, translationCase2, translationCase3);

        GetAllTranslationCasesRequest request = GetAllTranslationCasesRequest.builder()
                .withCustomerId(customerId)
                .build();

        when(caseDao.getAllTranslationCases(customerId)).thenReturn(listOfCases);


        // WHEN
        GetAllTranslationCasesResult result = getAllTranslationCasesActivity.handleRequest(request);

        // THEN
        assertEquals(result.getTranslationCaseList().get(0).getCustomerId(), customerId);
        assertEquals(result.getTranslationCaseList().get(1).getCustomerId(), customerId);
        assertEquals(result.getTranslationCaseList().get(2).getCustomerId(), customerId);

        assertEquals(result.getTranslationCaseList().get(0).getTranslationCaseId(), caseId1);
        assertEquals(result.getTranslationCaseList().get(1).getTranslationCaseId(), caseId2);
        assertEquals(result.getTranslationCaseList().get(2).getTranslationCaseId(), caseId3);

        assertEquals(result.getTranslationCaseList().get(0).getDueDate(), dueDate1);
        assertEquals(result.getTranslationCaseList().get(1).getDueDate(), dueDate2);
        assertEquals(result.getTranslationCaseList().get(2).getDueDate(), dueDate3);

        assertEquals(result.getTranslationCaseList().get(0).getOpenCase(), openCase1);
        assertEquals(result.getTranslationCaseList().get(1).getOpenCase(), openCase2);
        assertEquals(result.getTranslationCaseList().get(2).getOpenCase(), openCase3);

        assertEquals(result.getTranslationCaseList().get(0).getProjectType(), projectType1);
        assertEquals(result.getTranslationCaseList().get(1).getProjectType(), projectType2);
        assertEquals(result.getTranslationCaseList().get(2).getProjectType(), projectType3);

        assertEquals(result.getTranslationCaseList().get(0).getTranslatedTitle(), translatedTitle1);
        assertEquals(result.getTranslationCaseList().get(1).getTranslatedTitle(), translatedTitle2);
        assertEquals(result.getTranslationCaseList().get(2).getTranslatedTitle(), translatedTitle3);
    }

}
