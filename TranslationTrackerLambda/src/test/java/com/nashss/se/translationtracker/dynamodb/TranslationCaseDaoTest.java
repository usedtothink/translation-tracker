package com.nashss.se.translationtracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.exceptions.DuplicateCaseException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
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
        String customerId = "customerId";
        List<TranslationCase> testList = new ArrayList<>();
        testList.add(new TranslationCase());
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        when(listMock.listIterator()).thenReturn(testList.listIterator());
        when(dynamoDBMapper.query(eq(TranslationCase.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN
        caseDao.getAllTranslationCases(customerId);
        // THEN
        verify(dynamoDBMapper).query(any(), any());
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
    public void createTranslationCase_callsSave() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();

        // WHEN
        TranslationCase result = caseDao.createTranslationCase(translationCase);

        // THEN
        verify(dynamoDBMapper).save(translationCase);
        assertEquals(translationCase, result);
    }

    @Test
    public void createTranslationCase_noExistingCase_callsSave() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN
        TranslationCase result = caseDao.createTranslationCase(translationCase);

        // THEN
        verify(dynamoDBMapper).save(translationCase);
        assertEquals(translationCase, result);
    }

    @Test
    public void createTranslationCase_wrongCustomerId_throwsException() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(CUSTOMER_ID);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        TranslationCase wrongIdTranslationCase = new TranslationCase();
        wrongIdTranslationCase.setCustomerId("wrongId");
        wrongIdTranslationCase.setTranslationCaseId(TRANSLATION_CASE_ID);

        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        assertThrows(SecurityException.class, () -> caseDao.createTranslationCase(wrongIdTranslationCase));
    }

    @Test
    public void archiveTranslationCase_validCustomerIdAndTranslationCaseId_callsSaveAndDelete() {
        // GIVEN
        String customerId = "customerId";
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(customerId);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(translationCase);

        // WHEN
        TranslationCase result = caseDao.archiveTranslationCase(customerId, TRANSLATION_CASE_ID);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationCase.class));
        verify(dynamoDBMapper).delete(translationCase);
    }

    @Test
    public void archiveTranslationCase_caseDoesNotExist_throwsException() {
        // GIVEN
        String customerId = "customerId";
        TranslationCase translationCase = new TranslationCase();
        translationCase.setCustomerId(customerId);
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.archiveTranslationCase(customerId,
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

}
