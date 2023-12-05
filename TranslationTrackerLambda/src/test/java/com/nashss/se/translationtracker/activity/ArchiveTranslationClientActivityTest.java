package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.ArchiveTranslationClientResult;
import com.nashss.se.translationtracker.dynamodb.TranslationClientDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ArchiveTranslationClientActivityTest {
    private static final String CUSTOMER_ID = "customerId";
    private static final String TRANSLATION_CLIENT_ID = "translationClientId";
    @Mock
    private TranslationClientDao clientDao;
    private ArchiveTranslationClientActivity archiveTranslationClientActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        archiveTranslationClientActivity = new ArchiveTranslationClientActivity(clientDao);
    }

    @Test
    void handleRequest_validCustomerIdAndClientId_returnsClient() {
        // GIVEN

        TranslationClient translationClient = new TranslationClient();
        translationClient.setCustomerId(CUSTOMER_ID);
        translationClient.setTranslationClientId(TRANSLATION_CLIENT_ID);

        when(clientDao.archiveTranslationClient(CUSTOMER_ID, TRANSLATION_CLIENT_ID)).thenReturn(translationClient);

        ArchiveTranslationClientRequest request = ArchiveTranslationClientRequest.builder()
                .withCustomerId(CUSTOMER_ID)
                .withTranslationClientId(TRANSLATION_CLIENT_ID)
                .build();

        // WHEN
        ArchiveTranslationClientResult result = archiveTranslationClientActivity.handleRequest(request);

        // THEN
        assertEquals(CUSTOMER_ID, result.getTranslationClient().getCustomerId());
        assertEquals(TRANSLATION_CLIENT_ID, result.getTranslationClient().getTranslationClientId());
    }
}