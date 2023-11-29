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
        String expectedCustomerId = "expectedCustomer";
        String expectedClientName = "clientName";
        ClientType expectedClientType = ClientType.CONTRACT;

        CreateTranslationClientRequest request = CreateTranslationClientRequest.builder()
                .withCustomerId(expectedCustomerId)
                .withTranslationClientName(expectedClientName)
                .withTranslationClientType(expectedClientType.name())
                .build();

        // WHEN
        CreateTranslationClientResult result = createTranslationClientActivity.handleRequest(request);

        // THEN
        verify(clientDao).createTranslationClient(any(TranslationClient.class));

        assertNotNull(result.getTranslationClient().getTranslationClientId());
        assertEquals(expectedCustomerId, result.getTranslationClient().getCustomerId());
        assertEquals(expectedClientName, result.getTranslationClient().getTranslationClientName());
        assertEquals(expectedClientType, result.getTranslationClient().getTranslationClientType());
    }

}