package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ArchiveTranslationClientRequest.Builder.class)
public class ArchiveTranslationClientRequest {
    private String customerId;
    private String translationClientId;

    private ArchiveTranslationClientRequest(String customerId, String translationClientId) {
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
        return "UpdateTranslationClientRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationClientId='" + translationClientId +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
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

        public ArchiveTranslationClientRequest build() {
            return new ArchiveTranslationClientRequest(customerId, translationClientId);
        }
    }
}
