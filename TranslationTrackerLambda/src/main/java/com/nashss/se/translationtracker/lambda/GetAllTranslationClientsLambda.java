package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.GetAllTranslationClientsRequest;
import com.nashss.se.translationtracker.activity.results.GetAllTranslationClientsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllTranslationClientsLambda
        extends LambdaActivityRunner<GetAllTranslationClientsRequest, GetAllTranslationClientsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllTranslationClientsRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllTranslationClientsRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                    GetAllTranslationClientsRequest.builder()
                        .withCustomerId(claims.get("email"))
                        .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetAllTranslationClientsActivity().handleRequest(request)
        );
    }

}
