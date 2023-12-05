package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationClientResult;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.types.ClientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateTranslationClientActivityTest {
    private static final String CUSTOMER_ID = "expectedCustomer";
    private static final String TRANSLATION_CLIENT_NAME = "clientName";
    private static final ClientType CLIENT_TYPE = ClientType.CONTRACT;
    @Mock
    private TranslationClientDao clientDao;
    private CreateTranslationClientActivity createTranslationClientActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createTranslationClientActivity = new CreateTranslationClientActivity(clientDao);
    }

    @Test
    void handleRequest_createsAndSavesTranslationClient() {
        // GIVEN
        CreateTranslationClientRequest request = CreateTranslationClientRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationClientName(TRANSLATION_CLIENT_NAME)
                .withTranslationClientType(CLIENT_TYPE.name())
                .build();

        // WHEN
        CreateTranslationClientResult result = createTranslationClientActivity.handleRequest(request);

        // THEN
        verify(clientDao).createTranslationClient(any(TranslationClient.class));

        assertNotNull(result.getTranslationClient().getTranslationClientId());
        assertEquals(CUSTOMER_ID, result.getTranslationClient().getCustomerId());
        assertEquals(TRANSLATION_CLIENT_NAME, result.getTranslationClient().getTranslationClientName());
        assertEquals(CLIENT_TYPE, result.getTranslationClient().getTranslationClientType());
    }

}