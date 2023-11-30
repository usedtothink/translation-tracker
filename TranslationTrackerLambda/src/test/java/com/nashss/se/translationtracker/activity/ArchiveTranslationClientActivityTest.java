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
        String customerId = "customerId";
        String translationClientId = "translationClientId";

        TranslationClient translationClient = new TranslationClient();
        translationClient.setCustomerId(customerId);
        translationClient.setTranslationClientId(translationClientId);

        when(clientDao.archiveTranslationClient(customerId, translationClientId)).thenReturn(translationClient);

        ArchiveTranslationClientRequest request = ArchiveTranslationClientRequest.builder()
                .withCustomerId(customerId)
                .withTranslationClientId(translationClientId)
                .build();

        // WHEN
        ArchiveTranslationClientResult result = archiveTranslationClientActivity.handleRequest(request);

        // THEN
        assertEquals(customerId, result.getTranslationClient().getCustomerId());
        assertEquals(translationClientId, result.getTranslationClient().getTranslationClientId());
    }
}