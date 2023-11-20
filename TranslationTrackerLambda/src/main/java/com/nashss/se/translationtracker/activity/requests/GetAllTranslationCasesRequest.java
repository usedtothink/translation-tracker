package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = GetAllTranslationCasesRequest.Builder.class)
public class GetAllTranslationCasesRequest {
    private final String customerId;

    private GetAllTranslationCasesRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "GetAllTranslaionCasesRequest{" +
                "CustomerId='" + customerId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public GetAllTranslationCasesRequest build() {
            return new GetAllTranslationCasesRequest(customerId);
        }
    }

}
