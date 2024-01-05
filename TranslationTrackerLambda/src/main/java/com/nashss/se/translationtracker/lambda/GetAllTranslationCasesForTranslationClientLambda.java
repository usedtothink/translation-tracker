package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationCasesForTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationCasesForTranslationClientResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllTranslationCasesForTranslationClientLambda
        extends LambdaActivityRunner<GetAllTranslationCasesForTranslationClientRequest,
                GetAllTranslationCasesForTranslationClientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllTranslationCasesForTranslationClientRequest>,
            LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllTranslationCasesForTranslationClientRequest>
                                                    input, Context context) {
        return super.runActivity(
            () -> {
                GetAllTranslationCasesForTranslationClientRequest unauthenticatedRequest =
                        input.fromPath(path ->
                                GetAllTranslationCasesForTranslationClientRequest.builder()
                                        .withTranslationClientId(path.get("id"))
                                        .build());
                return input.fromUserClaims(claims ->
                        GetAllTranslationCasesForTranslationClientRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationClientId(unauthenticatedRequest.getTranslationClientId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetAllTranslationCasesForTranslationClientActivity().handleRequest(request)
        );
    }
}
