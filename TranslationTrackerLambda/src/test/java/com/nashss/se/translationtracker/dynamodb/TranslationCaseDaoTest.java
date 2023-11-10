package com.nashss.se.translationtracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.exceptions.DuplicateCaseException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TranslationCaseDaoTest {
    private static final String TRANSLATION_CASE_ID = "translationCaseId";
    private static final String NON_EXISTENT_CASE_ID = "not a translationCaseId";
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
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(new TranslationCase());

        // WHEN
        TranslationCase translationCase = caseDao.getTranslationCase(TRANSLATION_CASE_ID);

        // THEN
        assertNotNull(translationCase);
        verify(dynamoDBMapper).load(TranslationCase.class, TRANSLATION_CASE_ID);
    }

    @Test
    public void getTranslationCase_translationCaseNotFound_throwsTranslationCaseNotFoundException() {
        // GIVEN
        when(dynamoDBMapper.load(TranslationCase.class, NON_EXISTENT_CASE_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> caseDao.getTranslationCase(NON_EXISTENT_CASE_ID));
    }

    @Test
    public void updateTranslationCase_callsSave() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();

        // WHEN
        TranslationCase result = caseDao.createTranslationCase(translationCase);

        // THEN
        verify(dynamoDBMapper).save(translationCase);
        assertEquals(translationCase, result);
    }

    @Test
    public void createTranslationCase_caseNameDoesNotExist_callsSave() {
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
    public void createTranslationCase_caseNameAlreadyExists_throwsException() {
        // GIVEN
        TranslationCase translationCase = new TranslationCase();
        translationCase.setTranslationCaseId(TRANSLATION_CASE_ID);
        when(dynamoDBMapper.load(TranslationCase.class, TRANSLATION_CASE_ID)).thenReturn(new TranslationCase());

        // WHEN
        assertThrows(DuplicateCaseException.class, () -> caseDao.createTranslationCase(translationCase));
    }























}