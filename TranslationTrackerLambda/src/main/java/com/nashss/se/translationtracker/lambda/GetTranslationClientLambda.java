package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.GetTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationClientResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetTranslationClientLambda
        extends LambdaActivityRunner<GetTranslationClientRequest, GetTranslationClientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetTranslationClientRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTranslationClientRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                GetTranslationClientRequest unauthenticatedRequest = input.fromBody(
                        GetTranslationClientRequest.class);
                return input.fromUserClaims(claims ->
                        GetTranslationClientRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationClientId(unauthenticatedRequest.getTranslationClientId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetTranslationClientActivity().handleRequest(request)
        );
    }
}
