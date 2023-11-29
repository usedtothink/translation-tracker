package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = GetTranslationClientRequest.Builder.class)
public class GetTranslationClientRequest {
    private final String customerId;
    private final String translationClientId;

    private GetTranslationClientRequest(String customerId, String translationClientId) {
        this.customerId = customerId;
        this.translationClientId = translationClientId;

    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationClientId() {
        return this.translationClientId;
    }

    @Override
    public String toString() {
        return "GetTranslationClientRequest{" +
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

        public GetTranslationClientRequest build() {
            return new GetTranslationClientRequest(customerId, translationClientId);
        }
    }
}
