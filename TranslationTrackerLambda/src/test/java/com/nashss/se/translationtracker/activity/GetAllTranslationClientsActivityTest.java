package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationClientsRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationClientsResult;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.types.ClientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetAllTranslationClientsActivityTest {
    @Mock
    private TranslationClientDao clientDao;
    private GetAllTranslationClientsActivity getAllTranslationClientsActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllTranslationClientsActivity = new GetAllTranslationClientsActivity(clientDao);
    }

    @Test
    void handleRequest_returnsAllTranslationClients() {
        // GIVEN
        String customerId = "customerId";

        String translationClientId1 = "translationClientId1";
        String translationClientId2 = "translationClientId2";
        String translationClientId3 = "translationClientId3";

        String translationClientName1 = "translationClientName1";
        String translationClientName2 = "translationClientName2";
        String translationClientName3 = "translationClientName3";

        ClientType translationClientType1 = ClientType.COMMERCIAL;
        ClientType translationClientType2 = ClientType.CORPORATE;
        ClientType translationClientType3 = ClientType.MEDIA;

        TranslationClient translationClient1 = new TranslationClient();
        translationClient1.setCustomerId(customerId);
        translationClient1.setTranslationClientId(translationClientId1);
        translationClient1.setTranslationClientName(translationClientName1);
        translationClient1.setClientType(translationClientType1);

        TranslationClient translationClient2 = new TranslationClient();
        translationClient2.setCustomerId(customerId);
        translationClient2.setTranslationClientId(translationClientId2);
        translationClient2.setTranslationClientName(translationClientName2);
        translationClient2.setClientType(translationClientType2);

        TranslationClient translationClient3 = new TranslationClient();
        translationClient3.setCustomerId(customerId);
        translationClient3.setTranslationClientId(translationClientId3);
        translationClient3.setTranslationClientName(translationClientName3);
        translationClient3.setClientType(translationClientType3);

        List<TranslationClient> listOfClients = List.of(translationClient1, translationClient2, translationClient3);

        GetAllTranslationClientsRequest request = GetAllTranslationClientsRequest.builder()
                .withCustomerId(customerId)
                .build();

        when(clientDao.getAllTranslationClients(customerId)).thenReturn(listOfClients);

        // WHEN
        GetAllTranslationClientsResult result = getAllTranslationClientsActivity.handleRequest(request);

        // THEN
        assertEquals(customerId, result.getTranslationClientList().get(0).getCustomerId());
        assertEquals(customerId, result.getTranslationClientList().get(1).getCustomerId());
        assertEquals(customerId, result.getTranslationClientList().get(2).getCustomerId());

        assertEquals(translationClientId1, result.getTranslationClientList().get(0).getTranslationClientId());
        assertEquals(translationClientId2, result.getTranslationClientList().get(1).getTranslationClientId());
        assertEquals(translationClientId3, result.getTranslationClientList().get(2).getTranslationClientId());

        assertEquals(translationClientName1, result.getTranslationClientList().get(0).getTranslationClientName());
        assertEquals(translationClientName2, result.getTranslationClientList().get(1).getTranslationClientName());
        assertEquals(translationClientName3, result.getTranslationClientList().get(2).getTranslationClientName());

        assertEquals(translationClientType1, result.getTranslationClientList().get(0).getClientType());
        assertEquals(translationClientType2, result.getTranslationClientList().get(1).getClientType());
        assertEquals(translationClientType3, result.getTranslationClientList().get(2).getClientType());
    }

}