package com.nashss.se.translationtracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.exceptions.DuplicateCaseException;
import com.nashss.se.translationtracker.exceptions.DuplicateProgressUpdateException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
import com.nashss.se.translationtracker.types.ProjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TranslationCaseDaoTest {
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String NON_EXISTENT_CASE_ID = "not a translationCaseId";
    private static final String CUSTOMER_ID = "customerId";
    private static final String WRONG_CUSTOMER_ID = "wrongCustomerId";
    private static final String CASE_NICKNAME = "caseNickname";
    private static final ProjectType PROJECT_TYPE = ProjectType.ACADEMIC;

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private TranslationCaseDao caseDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        caseDao = new TranslationCaseDao(dynamoDBMapper);
    }

    @Test
    public void createTranslationCase_noExistingCase_callsSave() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCaseNickname(CASE_NICKNAME);
        translationCase.setProjectType(PROJECT_TYPE);
        // Mocking the paginated query list
        List<TranslationCase> testList = new ArrayList<>();
        PaginatedQueryList<TranslationCase> listMock = mock(PaginatedQueryList.class);
        // Return the test list when the mocked list is called
        when(listMock.stream()).thenReturn(testList.stream());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN
        caseDao.createTranslationCase(translationCase);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationCase.class));
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
        PaginatedQueryList<TranslationCase> listMock = mock(PaginatedQueryList.class);
        // Return the test list when the mocked list is called
        when(listMock.stream()).thenReturn(testList.stream());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN & THEN
        assertThrows(DuplicateCaseException.class, () -> caseDao.createTranslationCase(translationCase));
    }

    @Test
    public void getTranslationCase_validTranslationCaseId_returnsTranslationCase() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        TranslationCase result = caseDao.getTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID);

        // THEN
        assertNotNull(result);
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
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> caseDao.getTranslationCase(WRONG_CUSTOMER_ID,
                TRANSLATION_CASE_ID));
    }

    @Test
    public void getAllTranslationCases_casesFoundForCustomerId_returnsCaseList() {
        // GIVEN
        // Mocking the paginated query list
        List<TranslationCase> testList = new ArrayList<>();
        testList.add(new TranslationCase());
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        // Return the size of the real list
        when(listMock.isEmpty()).thenReturn(testList.isEmpty());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN
        List<TranslationCase> result = caseDao.getAllTranslationCases(CUSTOMER_ID);

        // THEN
        assertFalse(result.isEmpty());
    }

    @Test
    public void getAllTranslationCases_noCasesFoundForCustomerId_throwsException() {
        // GIVEN
        // Mocking the paginated query list
        List<TranslationCase> testList = new ArrayList<>();
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        // Return the size of the real list
        when(listMock.isEmpty()).thenReturn(testList.isEmpty());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.getAllTranslationCases(CUSTOMER_ID));
    }

    @Test
    public void archiveTranslationCase_validCustomerIdAndTranslationCaseId_callsSaveAndDelete() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        TranslationCase result = caseDao.archiveTranslationCase(CUSTOMER_ID, TRANSLATION_CASE_ID);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationCase.class));
        verify(dynamoDBMapper).delete(translationCase);
        // The original translation case is returned, not the archived translation case
        assertEquals(TRANSLATION_CASE_ID, result.getTranslationCaseId());
    }

    @Test
    public void archiveTranslationCase_caseIdNotFound_throwsException() {
        // GIVEN
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.archiveTranslationCase(CUSTOMER_ID,
                TRANSLATION_CASE_ID));
    }

    @Test
    public void archiveTranslationCase_customerIdMismatch_throwsException() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(WRONG_CUSTOMER_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> caseDao.archiveTranslationCase(CUSTOMER_ID,
                TRANSLATION_CASE_ID));
    }

    @Test
    public void addProgressUpdate_validCustomerIdValidTranslationCaseId_updatesProgressLog() {
        // GIVEN
        String notes = "notes";
        ProgressUpdate progressUpdate = ProgressUpdate.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withNotes(notes)
                .build();

        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        TranslationCase result = caseDao.addProgressUpdate(progressUpdate);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationCase.class));
        assertNotNull(result.getProgressLog());
    }

    @Test
    public void addProgressUpdate_translationCaseNotFound_throwsException() {
        // GIVEN
        ProgressUpdate progressUpdate = ProgressUpdate.builder()
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .build();

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.addProgressUpdate(progressUpdate));
    }

    @Test
    public void addProgressUpdate_wrongCustomerId_throwsException() {
        // GIVEN
        ProgressUpdate progressUpdate = ProgressUpdate.builder()
                .withCustomerId(WRONG_CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .build();

        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> caseDao.addProgressUpdate(progressUpdate));
    }

    @Test
    public void addProgressUpdate_progressUpdateAlreadyExists_throwsException() {
        // GIVEN
        String notes = "notes";
        ProgressUpdate progressUpdate = ProgressUpdate.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationCaseId(TRANSLATION_CASE_ID)
                .withNotes(notes)
                .build();

        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        translationCase.setProgressLog(new ArrayList<>(List.of(progressUpdate)));

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN & THEN
        assertThrows(DuplicateProgressUpdateException.class, () -> caseDao.addProgressUpdate(progressUpdate));
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

}
