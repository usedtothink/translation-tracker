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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetTranslationClientActivityTest {

    private static final String CUSTOMER_ID = "customerId";
    private static final String TRANSLATION_CLIENT_ID = "clientId";
    private static final String TRANSLATION_CLIENT_NAME = "clientName";
    private static final ClientType TRANSLATION_CLIENT_TYPE = ClientType.CONTRACT;
    @Mock
    private TranslationClientDao clientDao;
    private GetTranslationClientActivity getTranslationClientActivity;
    private TranslationClient translationClient;


    @BeforeEach
    void setup() {
        translationClient = new TranslationClient();
        translationClient.setCustomerId(CUSTOMER_ID);
        translationClient.setTranslationClientId(TRANSLATION_CLIENT_ID);
        translationClient.setTranslationClientName(TRANSLATION_CLIENT_NAME);
        translationClient.setClientType(TRANSLATION_CLIENT_TYPE);

        openMocks(this);
        getTranslationClientActivity = new GetTranslationClientActivity(clientDao);
    }

    @Test
    void handleRequest_savedTranslationClientFound_returnsTranslationClientModelInResult() {
        // GIVEN
        when(clientDao.getTranslationClient(CUSTOMER_ID, TRANSLATION_CLIENT_ID)).thenReturn(translationClient);

        GetTranslationClientRequest request = GetTranslationClientRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationClientId(TRANSLATION_CLIENT_ID)
                .build();

        // WHEN
        GetTranslationClientResult result = getTranslationClientActivity.handleRequest(request);

        // THEN
        verify(clientDao).getTranslationClient(CUSTOMER_ID, TRANSLATION_CLIENT_ID);
        assertEquals(CUSTOMER_ID, result.getTranslationClient().getCustomerId());
        assertEquals(TRANSLATION_CLIENT_ID, result.getTranslationClient().getTranslationClientId());
        assertEquals(TRANSLATION_CLIENT_NAME, result.getTranslationClient().getTranslationClientName());
        assertEquals(TRANSLATION_CLIENT_TYPE, result.getTranslationClient().getClientType());
    }
}