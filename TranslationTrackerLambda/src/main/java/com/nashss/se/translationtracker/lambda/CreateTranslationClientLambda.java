package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.CreateTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.CreateTranslationClientResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class CreateTranslationClientLambda
        extends LambdaActivityRunner<CreateTranslationClientRequest, CreateTranslationClientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateTranslationClientRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateTranslationClientRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                CreateTranslationClientRequest unauthenticatedRequest = input.fromBody(
                        CreateTranslationClientRequest.class);
                return input.fromUserClaims(claims ->
                        CreateTranslationClientRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationClientName(unauthenticatedRequest.getTranslationClientName())
                                .withClientType(unauthenticatedRequest.getClientType())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateTranslationClientActivity().handleRequest(request)
        );
    }
}
