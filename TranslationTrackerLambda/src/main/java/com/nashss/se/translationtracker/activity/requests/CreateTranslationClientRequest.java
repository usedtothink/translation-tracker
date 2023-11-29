package com.nashss.se.translationtracker.activity.requests;

import com.nashss.se.translationtracker.utils.IdGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateTranslationClientRequest.Builder.class)
public class CreateTranslationClientRequest {
    private String customerId;
    private String translationClientId;
    private String translationClientName;
    private String translationClientType;

    private CreateTranslationClientRequest(String customerId, String translationClientName,
                                           String translationClientType) {
        this.customerId = customerId;
        this.translationClientId = IdGenerator.newTranslationClientId(translationClientType, translationClientName);
        this.translationClientName = translationClientName;
        this.translationClientType = translationClientType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public String getTranslationClientName() {
        return translationClientName;
    }

    public String getTranslationClientType() {
        return translationClientType;
    }

    @Override
    public String toString() {
        return "CreateTranslationClientRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationClientId='" + translationClientId + '\'' +
                ", translationClientName='" + translationClientName + '\'' +
                ", translationClientType='" + translationClientType + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String translationClientName;
        private String translationClientType;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationClientName(String translationClientName) {
            this.translationClientName = translationClientName;
            return this;
        }

        public Builder withTranslationClientType(String translationClientType) {
            this.translationClientType = translationClientType;
            return this;
        }

        public CreateTranslationClientRequest build() {
            return new CreateTranslationClientRequest(customerId, translationClientName,
                    translationClientType);
        }
    }
}
