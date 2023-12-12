package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.ArchiveTranslationClientResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ArchiveTranslationClientLambda
        extends LambdaActivityRunner<ArchiveTranslationClientRequest, ArchiveTranslationClientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<ArchiveTranslationClientRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<ArchiveTranslationClientRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                ArchiveTranslationClientRequest unauthenticatedRequest =
                        input.fromPath(path ->
                                ArchiveTranslationClientRequest.builder()
                                        .withTranslationClientId(path.get("id"))
                                        .build());
                return input.fromUserClaims(claims ->
                        ArchiveTranslationClientRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationClientId(unauthenticatedRequest.getTranslationClientId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideArchiveTranslationClientActivity().handleRequest(request)
        );
    }
}
