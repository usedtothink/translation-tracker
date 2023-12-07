package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.ArchiveTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.requests.GetPaymentRecordRequest;
import com.nashss.se.translationtracker.activity.results.GetPaymentRecordResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPaymentRecordLambda
        extends LambdaActivityRunner<GetPaymentRecordRequest, GetPaymentRecordResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPaymentRecordRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPaymentRecordRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetPaymentRecordRequest unauthenticatedRequest =
                        input.fromPath(path ->
                                GetPaymentRecordRequest.builder()
                                        .withTranslationCaseId(path.get("id"))
                                        .build());
                return input.fromUserClaims(claims ->
                        GetPaymentRecordRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationCaseId(unauthenticatedRequest.getTranslationCaseId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetPaymentRecordActivity().handleRequest(request)
        );
    }
}
