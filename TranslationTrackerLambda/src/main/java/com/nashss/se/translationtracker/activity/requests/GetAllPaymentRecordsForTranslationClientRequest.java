package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = GetAllPaymentRecordsForTranslationClientRequest.Builder.class)
public class GetAllPaymentRecordsForTranslationClientRequest {
    private final String customerId;
    private final String translationClientId;

    private GetAllPaymentRecordsForTranslationClientRequest(String customerId, String translationClientId) {
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
        return "GetAllPaymentRecordsForTranslationClientRequest{" +
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

        public GetAllPaymentRecordsForTranslationClientRequest build() {
            return new GetAllPaymentRecordsForTranslationClientRequest(customerId, translationClientId);
        }
    }
}
