package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.GetTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationClientResult;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.types.ClientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetTranslationClientActivityTest {

    @Mock
    private TranslationClientDao clientDao;
    private GetTranslationClientActivity getTranslationClientActivity;
    private TranslationClient testTranslationClient;
    private String customerId = "customerId";
    private String translationClientId = "clientId";
    private String translationClientName = "clientName";
    private ClientType translationClientType = ClientType.CONTRACT;

    @BeforeEach
    void setup() {
        testTranslationClient = new TranslationClient();
        testTranslationClient.setCustomerId(customerId);
        testTranslationClient.setTranslationClientId(translationClientId);
        testTranslationClient.setTranslationClientName(translationClientName);
        testTranslationClient.setTranslationClientType(translationClientType);

        openMocks(this);
        getTranslationClientActivity = new GetTranslationClientActivity(clientDao);
    }

    @Test
    void handleRequest_savedTranslationClientFound_returnsTranslationClientModelInResult() {
        // GIVEN
        when(clientDao.getTranslationClient(customerId, translationClientId)).thenReturn(testTranslationClient);

        GetTranslationClientRequest request = GetTranslationClientRequest.builder()
                .withCustomerId(customerId)
                .withTranslationClientId(translationClientId)
                .build();

        // WHEN
        GetTranslationClientResult result = getTranslationClientActivity.handleRequest(request);

        // THEN
        assertEquals(customerId, result.getTranslationClient().getCustomerId());
        assertEquals(translationClientId, result.getTranslationClient().getTranslationClientId());
        assertEquals(translationClientName, result.getTranslationClient().getTranslationClientName());
        assertEquals(translationClientType, result.getTranslationClient().getTranslationClientType());
    }
}