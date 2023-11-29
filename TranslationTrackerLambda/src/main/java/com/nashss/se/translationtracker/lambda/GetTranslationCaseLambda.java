package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.GetTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationCaseResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class GetTranslationCaseLambda
        extends LambdaActivityRunner<GetTranslationCaseRequest, GetTranslationCaseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetTranslationCaseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTranslationCaseRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetTranslationCaseRequest unauthenticatedRequest =
                        input.fromBody(GetTranslationCaseRequest.class);
                return input.fromUserClaims(claims ->
                        GetTranslationCaseRequest.builder()
                                .withTranslationCaseId(unauthenticatedRequest.getTranslationCaseId())
                                .withCustomerId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetTranslationCaseActivity().handleRequest(request)
        );
    }
}
