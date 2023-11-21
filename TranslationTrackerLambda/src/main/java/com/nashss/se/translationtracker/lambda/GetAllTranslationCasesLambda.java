package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationCasesRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationCasesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllTranslationCasesLambda
        extends LambdaActivityRunner<GetAllTranslationCasesRequest, GetAllTranslationCasesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllTranslationCasesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllTranslationCasesRequest> input,
                                    Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                            GetAllTranslationCasesRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .build()),
            (request, serviceComponent) ->
                        serviceComponent.provideGetAllTranslationCasesActivity().handleRequest(request)
        );

    }
}
