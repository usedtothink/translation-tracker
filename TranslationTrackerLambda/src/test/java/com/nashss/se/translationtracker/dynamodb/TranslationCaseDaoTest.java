package com.nashss.se.translationtracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.exceptions.DuplicateCaseException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TranslationCaseDaoTest {
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String NON_EXISTENT_CASE_ID = "not a translationCaseId";
    private static final String CUSTOMER_ID = "customerId";
    private static final String CASE_NICKNAME = "caseNickname";
    private static final ProjectType PROJECT_TYPE = ProjectType.ACADEMIC;
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private TranslationCaseDao caseDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        caseDao = new TranslationCaseDao(dynamoDBMapper);
    }

    @Test
    public void getTranslationCase_withTranslationCaseId_callsMapperWithPartitionKey() {
        // GIVEN
        TranslationCase testTranslationCase = new TranslationCase();
        testTranslationCase.setCustomerId(CUSTOMER_ID);

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(testTranslationCase);

        // WHEN
        TranslationCase translationCase = caseDao.getTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID);

        // THEN
        assertNotNull(translationCase);
        verify(dynamoDBMapper).load(TranslationCase.class, TRANSLATION_CASE_ID);
    }

    @Test
    public void getTranslationCase_translationCaseNotFound_throwsException() {
        // GIVEN
        when(dynamoDBMapper.load(TranslationCase.class, NON_EXISTENT_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.getTranslationCase(CUSTOMER_ID,
                NON_EXISTENT_CASE_ID));
    }

    @Test
    public void getTranslationCase_wrongCustomerId_throwsException() {
        // GIVEN
        String wrongId = "wrongId";
        TranslationCase testTranslationCase = new TranslationCase();
        testTranslationCase.setCustomerId(CUSTOMER_ID);
        testTranslationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(TranslationCase.class, NON_EXISTENT_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.getTranslationCase(wrongId,
                TRANSLATION_CASE_ID));
    }

    @Test
    public void getAllTranslationCases_queriesDatabase() {
        // GIVEN
        // Mocking the paginated query list
        List<TranslationCase> testList = new ArrayList<>();
        testList.add(new TranslationCase());
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        // Return the test list when the mocked list is called
        when(listMock.listIterator()).thenReturn(testList.listIterator());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN
        caseDao.getAllTranslationCases(CUSTOMER_ID);
        // THEN
        verify(dynamoDBMapper).query(any(), any());
    }

    @Test
    public void createTranslationCase_caseExistsWithSameNicknameAndProjectType_throwsException() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCaseNickname(CASE_NICKNAME);
        translationCase.setProjectType(PROJECT_TYPE);
        // Mocking the paginated query list
        List<TranslationCase> testList = new ArrayList<>();
        testList.add(translationCase);
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        // Return the test list when the mocked list is called
        when(listMock.listIterator()).thenReturn(testList.listIterator());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN & THEN
        assertThrows(DuplicateCaseException.class, () -> caseDao.createTranslationCase(translationCase));
    }

    @Test
    public void archiveTranslationCase_validCustomerIdAndTranslationCaseId_callsSaveAndDelete() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        TranslationCase result = caseDao.archiveTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationCase.class));
        verify(dynamoDBMapper).delete(translationCase);
    }

    @Test
    public void archiveTranslationCase_caseDoesNotExist_throwsException() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.archiveTranslationCase(CUSTOMER_ID,
                TRANSLATION_CASE_ID));
    }

    @Test
    public void archiveTranslationCase_customerIdMismatch_throwsException() {
        // GIVEN
        String wrongId = "wrongId";
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> caseDao.archiveTranslationCase(wrongId,
                TRANSLATION_CASE_ID));
    }

    @Test
    public void saveTranslationCase_callsSave() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        // WHEN
        caseDao.saveTranslationCase(translationCase);
        // THEN
        verify(dynamoDBMapper).save(translationCase);
    }

    @Test
    public void updateTranslationCase_customerIdMatches_updatesCase() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        Boolean rushJob = true;

        TranslationCase updatedTranslationCase = new TranslationCase();
        updatedTranslationCase.setCustomerId(CUSTOMER_ID);
        updatedTranslationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        updatedTranslationCase.setRushJob(rushJob);

        // WHEN
        TranslationCase result = caseDao.updateTranslationCase(updatedTranslationCase);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationCase.class));
        assertEquals(CUSTOMER_ID, result.getCustomerId());
        assertEquals(TRANSLATION_CASE_ID, result.getTranslationCaseId());
        assertEquals(rushJob, result.getRushJob());
    }

    @Test
    public void updateTranslationCase_wrongCustomerId_throwsException() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        String wrongId = "wrongId";

        TranslationCase updatedTranslationCase = new TranslationCase();
        updatedTranslationCase.setCustomerId(wrongId);
        updatedTranslationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> caseDao.updateTranslationCase(updatedTranslationCase));
    }

}
