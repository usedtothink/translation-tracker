package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.AddProgressUpdateRequest;
import com.nashss.se.translationtracker.activity.results.AddProgressUpdateResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddProgressUpdateLambda
        extends LambdaActivityRunner<AddProgressUpdateRequest, AddProgressUpdateResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddProgressUpdateRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddProgressUpdateRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                AddProgressUpdateRequest unauthenticatedRequest =
                        input.fromBody(AddProgressUpdateRequest.class);
                return input.fromUserClaims(claims ->
                        AddProgressUpdateRequest.builder()
                                .withCustomerId(claims.get("email"))
                                .withTranslationCaseId(unauthenticatedRequest.getTranslationCaseId())
                                .withWordCount(unauthenticatedRequest.getWordCount())
                                .withStartDate(unauthenticatedRequest.getStartDate())
                                .withStartTime(unauthenticatedRequest.getStartTime())
                                .withEndDate(unauthenticatedRequest.getEndDate())
                                .withEndTime(unauthenticatedRequest.getEndTime())
                                .withNotes(unauthenticatedRequest.getNotes())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideAddProgressUpdateActivity().handleRequest(request)
        );
    }
}
