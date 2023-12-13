package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationCaseResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateTranslationCaseLambda
        extends LambdaActivityRunner<CreateTranslationCaseRequest, CreateTranslationCaseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateTranslationCaseRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateTranslationCaseRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                CreateTranslationCaseRequest unauthenticatedRequest =
                        input.fromBody(CreateTranslationCaseRequest.class);
                return input.fromUserClaims(claims ->
                        CreateTranslationCaseRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withCaseNickname(unauthenticatedRequest.getCaseNickname())
                                .withProjectType(unauthenticatedRequest.getProjectType())
                                .withTranslationClientId(unauthenticatedRequest.getTranslationClientId())
                                .withTranslationClientName(unauthenticatedRequest.getTranslationClientName())
                                .withSourceTextTitle(unauthenticatedRequest.getSourceTextTitle())
                                .withSourceTextAuthor(unauthenticatedRequest.getSourceTextAuthor())
                                .withTranslatedTitle(unauthenticatedRequest.getTranslatedTitle())
                                .withDueDate(unauthenticatedRequest.getDueDate())
                                .withStartDate(unauthenticatedRequest.getStartDate())
                                .withEndDate(unauthenticatedRequest.getEndDate())
                                .withOpenCase(unauthenticatedRequest.getOpenCase())
                                .withRushJob(unauthenticatedRequest.getRushJob())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateTranslationCaseActivity().handleRequest(request)
        );
    }
}
