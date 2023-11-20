package com.nashss.se.translationtracker.lambda;

import com.nashss.se.translationtracker.activity.requests.UpdateTranslationCaseRequest;
import com.nashss.se.translationtracker.activity.results.UpdateTranslationCaseResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateTranslationCaseLambda
        extends LambdaActivityRunner<UpdateTranslationCaseRequest, UpdateTranslationCaseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateTranslationCaseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateTranslationCaseRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                UpdateTranslationCaseRequest unauthenticatedRequest =
                        input.fromBody(UpdateTranslationCaseRequest.class);
                return input.fromUserClaims(claims ->
                        UpdateTranslationCaseRequest.builder()
                            .withCustomerId(claims.get("email"))
                            // The TranslationCaseId is going to come in
                            // through the front end including it in the body
                            .withTranslationCaseId(unauthenticatedRequest.getTranslationCaseId())
                            .withSourceTextTitle(unauthenticatedRequest.getSourceTextTitle())
/*                            .withSourceTextAuthor(unauthenticatedRequest.getSourceTextAuthor())
                            .withTranslatedTitle(unauthenticatedRequest.getTranslatedTitle())
                            .withDueDate(unauthenticatedRequest.getDueDate())
                            .withStartDate(unauthenticatedRequest.getStartDate())
                            .withEndDate(unauthenticatedRequest.getEndDate())
                            .withOpenCase(unauthenticatedRequest.getOpenCase())
                            .withRushJob(unauthenticatedRequest.getRushJob())
                            .withTotalWorkingHours(unauthenticatedRequest.getTotalWorkingHours())
                            .withWordsPerHour(unauthenticatedRequest.getWordsPerHour())*/
                            .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateTranslationCaseActivity().handleRequest(request)
        );
    }
}
