package com.nashss.se.translationtracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.translationtracker.activity.requests.GetTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.GetTranslationCaseResult;

public class GetTranslationCaseLambda
        extends LambdaActivityRunner<GetTranslationCaseRequest, GetTranslationCaseResult>
        implements RequestHandler<LambdaRequest<GetTranslationCaseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTranslationCaseRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetTranslationCaseRequest.builder()
                                .withTranslationCaseId(path.get("translationCaseId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTranslationCaseActivity().handleRequest(request)
        );
    }
}
