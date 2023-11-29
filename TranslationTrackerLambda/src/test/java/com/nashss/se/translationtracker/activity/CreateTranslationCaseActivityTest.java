package com.nashss.se.translationtracker.activity;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationCaseResult;
import com.nashss.se.translationtracker.dynamodb.TranslationCaseDao;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.types.ProjectType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateTranslationCaseActivityTest {
    @Mock
    private TranslationCaseDao caseDao;
    private CreateTranslationCaseActivity createTranslationCaseActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createTranslationCaseActivity = new CreateTranslationCaseActivity(caseDao);
    }

    @Test
    void handleRequest_createsAndSavesTranslationCase() {
        // GIVEN
        String expectedCustomerId = "expectedCustomer";
        String expectedCaseNickname = "expectedNickname";
        ProjectType expectedProjectType = ProjectType.ACADEMIC;

        CreateTranslationCaseRequest request = CreateTranslationCaseRequest.builder()
                .withCaseNickname(expectedCaseNickname)
                .withProjectType(expectedProjectType.name())
                .withCustomerId(expectedCustomerId)
                .build();

        // WHEN
        CreateTranslationCaseResult result = createTranslationCaseActivity.handleRequest(request);

        // THEN
        verify(caseDao).createTranslationCase(any(TranslationCase.class));

        assertNotNull(result.getTranslationCase().getTranslationCaseId());
        assertEquals(expectedCustomerId, result.getTranslationCase().getCustomerId());
        assertEquals(expectedCaseNickname, result.getTranslationCase().getCaseNickname());
        assertEquals(expectedProjectType, result.getTranslationCase().getProjectType());
    }
}
