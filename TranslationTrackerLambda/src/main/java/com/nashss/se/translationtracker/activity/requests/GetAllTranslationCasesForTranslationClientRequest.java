package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(builder = GetAllTranslationCasesForTranslationClientRequest.Builder.class)
public class GetAllTranslationCasesForTranslationClientRequest {
    private final String customerId;
    private final String translationClientId;

    private GetAllTranslationCasesForTranslationClientRequest(String customerId, String translationClientId) {
        this.customerId = customerId;
        this.translationClientId = translationClientId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    @Override
    public String toString() {
        return "GetAllTranslationCasesForTranslationClientRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationClientId='" + translationClientId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;
        private String translationClientId;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationClientId(String translationClientId) {
            this.translationClientId = translationClientId;
            return this;
        }

        public GetAllTranslationCasesForTranslationClientRequest build() {
            return new GetAllTranslationCasesForTranslationClientRequest(customerId, translationClientId);
        }
    }

}
