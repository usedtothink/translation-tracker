package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.UpdatePaymentRecordRequest;
import com.nashss.se.translationtracker.activity.results.UpdatePaymentRecordResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdatePaymentRecordLambda
        extends LambdaActivityRunner<UpdatePaymentRecordRequest, UpdatePaymentRecordResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePaymentRecordRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePaymentRecordRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                UpdatePaymentRecordRequest unauthenticatedRequest =
                        input.fromBody(UpdatePaymentRecordRequest.class);
                return input.fromUserClaims(claims ->
                        UpdatePaymentRecordRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationCaseId(unauthenticatedRequest.getTranslationCaseId())
                                // TranslationClientId is only updated in conjunction with UpdateTranslationCase
                                .withCasePaid(unauthenticatedRequest.getCasePaid())
                                .withPaymentDate(unauthenticatedRequest.getPaymentDate())
                                .withOnTime(unauthenticatedRequest.getOnTime())
                                .withGrossPayment(unauthenticatedRequest.getGrossPayment())
                                .withTaxRate(unauthenticatedRequest.getTaxRate())
                                .withPayRate(unauthenticatedRequest.getPayRate())
                                .withPayRateUnit(unauthenticatedRequest.getPayRateUnit())
                                .withWordCount(unauthenticatedRequest.getWordCount())
                                .withWordCountUnit(unauthenticatedRequest.getWordCountUnit())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdatePaymentRecordActivity().handleRequest(request)
        );
    }
}
