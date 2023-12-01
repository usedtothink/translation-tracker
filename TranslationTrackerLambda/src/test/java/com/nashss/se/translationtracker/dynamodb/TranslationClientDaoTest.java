package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.exceptions.DuplicateTranslationClientException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
import com.nashss.se.translationtracker.exceptions.TranslationClientNotFoundException;
import com.nashss.se.translationtracker.types.ClientType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

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

class TranslationClientDaoTest {

    private static final String TRANSLATION_CLIENT_ID = "translationClientId";
    private static final String NON_EXISTENT_CLIENT_ID = "not a translationClientId";
    private static final String CUSTOMER_ID = "customerId";
    private static final String WRONG_CUSTOMER_ID = "wrongCustomerId";
    private static final String CLIENT_NAME = "clientName";
    private static final ClientType CLIENT_TYPE = ClientType.CONTRACT;

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private TranslationClientDao clientDao;

    @BeforeEach
    public void setup() {
        openMocks(this);
        clientDao = new TranslationClientDao(dynamoDBMapper);
    }

    @Test
    void getTranslationClient_validCustomerIdValidClientId_returnsTranslationClient() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        translationClient.setCustomerId(CUSTOMER_ID);

        when(dynamoDBMapper.load(TranslationClient.class, TRANSLATION_CLIENT_ID)).thenReturn(translationClient);

        // WHEN
        TranslationClient result = clientDao.getTranslationClient(CUSTOMER_ID, TRANSLATION_CLIENT_ID);

        //THEN
        assertNotNull(result);
    }

    @Test
    void getTranslationClient_clientIdNotFound_throwsException() {
        // GIVEN
        when(dynamoDBMapper.load(TranslationClient.class, NON_EXISTENT_CLIENT_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationClientNotFoundException.class, () -> clientDao.getTranslationClient(CUSTOMER_ID,
                NON_EXISTENT_CLIENT_ID));
    }

    @Test
    void getTranslationClient_wrongCustomerId_throwsException() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        translationClient.setCustomerId(CUSTOMER_ID);

        when(dynamoDBMapper.load(TranslationClient.class, TRANSLATION_CLIENT_ID)).thenReturn(translationClient);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> clientDao.getTranslationClient(WRONG_CUSTOMER_ID,
                TRANSLATION_CLIENT_ID));
    }

    @Test
    void getAllTranslationClients_validCustomerId_returnsClientList() {
        // GIVEN
        // Mocking the paginated query list
        List<TranslationClient> testList = new ArrayList<>();
        testList.add(new TranslationClient());
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        // Return the size of the real list
        when(listMock.isEmpty()).thenReturn(testList.isEmpty());
        when(dynamoDBMapper.query(eq(TranslationClient.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN
        List<TranslationClient> result = clientDao.getAllTranslationClients(CUSTOMER_ID);

        // THEN
        assertFalse(result.isEmpty());
    }

    @Test
    void getAllTranslationClients_noClientsFoundForCustomerId_throwsException() {
        // GIVEN
        // Mocking the paginated query list
        List<TranslationClient> testList = new ArrayList<>();
        PaginatedQueryList listMock = mock(PaginatedQueryList.class);
        // Return the size of the real list
        when(listMock.isEmpty()).thenReturn(testList.isEmpty());
        when(dynamoDBMapper.query(eq(TranslationClient.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN & THEN
        assertThrows(TranslationClientNotFoundException.class, () -> clientDao.getAllTranslationClients(CUSTOMER_ID));
    }

    @Test
    public void createTranslationClient_noExistingClient_callsSave() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        translationClient.setTranslationClientName(CLIENT_NAME);
        translationClient.setTranslationClientType(CLIENT_TYPE);
        // Mocking the paginated query list
        List<TranslationClient> testList = new ArrayList<>();
        PaginatedQueryList<TranslationClient> listMock = mock(PaginatedQueryList.class);
        // Return the test list when the mocked list is called
        when(listMock.stream()).thenReturn(testList.stream());
        when(dynamoDBMapper.query(eq(TranslationClient.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN
        clientDao.createTranslationClient(translationClient);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationClient.class));
    }

    @Test
    public void createTranslationClient_clientExistsWithSameNameAndClientType_throwsException() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        translationClient.setTranslationClientName(CLIENT_NAME);
        translationClient.setTranslationClientType(CLIENT_TYPE);
        // Mocking the paginated query list
        List<TranslationClient> testList = new ArrayList<>();
        testList.add(translationClient);
        PaginatedQueryList<TranslationClient> listMock = mock(PaginatedQueryList.class);
        // Return the test list when the mocked list is called
        when(listMock.stream()).thenReturn(testList.stream());
        when(dynamoDBMapper.query(eq(TranslationClient.class), any(DynamoDBQueryExpression.class))).thenReturn(listMock);

        // WHEN & THEN
        assertThrows(DuplicateTranslationClientException.class, () -> clientDao.createTranslationClient(translationClient));
    }

    @Test
    public void archiveTranslationCase_validCustomerIdAndTranslationClientId_callsSaveAndDelete() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        translationClient.setCustomerId(CUSTOMER_ID);
        when(dynamoDBMapper.load(TranslationClient.class, TRANSLATION_CLIENT_ID)).thenReturn(translationClient);

        // WHEN
        TranslationClient result = clientDao.archiveTranslationClient(CUSTOMER_ID, TRANSLATION_CLIENT_ID);

        // THEN
        verify(dynamoDBMapper).save(any(TranslationClient.class));
        verify(dynamoDBMapper).delete(translationClient);
        // The original translation client is returned, not the archived translation client
        assertEquals(TRANSLATION_CLIENT_ID, result.getTranslationClientId());
    }

    @Test
    public void archiveTranslationClient_clientIdNotFound_throwsException() {
        // GIVEN
        when(dynamoDBMapper.load(TranslationClient.class, TRANSLATION_CLIENT_ID)).thenReturn(null);

        // WHEN & THEN
        assertThrows(TranslationCaseNotFoundException.class, () -> clientDao.archiveTranslationClient(CUSTOMER_ID,
                TRANSLATION_CLIENT_ID));
    }

    @Test
    public void archiveTranslationCase_customerIdMismatch_throwsException() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        translationClient.setCustomerId(WRONG_CUSTOMER_ID);
        when(dynamoDBMapper.load(TranslationClient.class, TRANSLATION_CLIENT_ID)).thenReturn(translationClient);

        // WHEN & THEN
        assertThrows(SecurityException.class, () -> clientDao.archiveTranslationClient(CUSTOMER_ID,
                TRANSLATION_CLIENT_ID));
    }

    @Test
    void saveTranslationClient_callsSave() {
        // GIVEN
        TranslationClient translationClient = new TranslationClient();
        // WHEN
        clientDao.saveTranslationClient(translationClient);
        // THEN
        verify(dynamoDBMapper).save(translationClient);
    }
}