package com.nashss.se.translationtracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.translationtracker.activity.requests.GetAllPaymentRecordsForTranslationClientRequest;
import com.nashss.se.translationtracker.activity.results.GetAllPaymentRecordsForTranslationClientResult;

public class GetAllPaymentRecordsForTranslationClientLambda
        extends LambdaActivityRunner<GetAllPaymentRecordsForTranslationClientRequest,
        GetAllPaymentRecordsForTranslationClientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllPaymentRecordsForTranslationClientRequest>,
            LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllPaymentRecordsForTranslationClientRequest>
                                                    input, Context context) {
        return super.runActivity(
                () -> {
                    GetAllPaymentRecordsForTranslationClientRequest unauthenticatedRequest =
                            input.fromBody(GetAllPaymentRecordsForTranslationClientRequest.class);
                    return input.fromUserClaims(claims ->
                            GetAllPaymentRecordsForTranslationClientRequest.builder()
                                    .withCustomerId(claims.get("email"))
                                    .withTranslationClientId(unauthenticatedRequest.getTranslationClientId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllPaymentRecordsForTranslationClientActivity()
                                .handleRequest(request)
        );
    }
}
