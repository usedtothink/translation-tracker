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
                                .withCaseNickname(unauthenticatedRequest.getCaseNickname())
                                .withProjectType(unauthenticatedRequest.getProjectType())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateTranslationCaseActivity().handleRequest(request)
        );
    }
}
