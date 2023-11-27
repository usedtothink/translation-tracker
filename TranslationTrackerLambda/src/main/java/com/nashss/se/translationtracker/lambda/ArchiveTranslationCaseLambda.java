package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.ArchiveTranslationCaseResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ArchiveTranslationCaseLambda
        extends LambdaActivityRunner<ArchiveTranslationCaseRequest, ArchiveTranslationCaseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<ArchiveTranslationCaseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<ArchiveTranslationCaseRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                ArchiveTranslationCaseRequest unauthenticatedRequest =
                        input.fromBody(ArchiveTranslationCaseRequest.class);
                return input.fromUserClaims(claims ->
                        ArchiveTranslationCaseRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationCaseId(unauthenticatedRequest.getTranslationCaseId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideArchiveTranslationCaseActivity().handleRequest(request)
        );
    }
}
