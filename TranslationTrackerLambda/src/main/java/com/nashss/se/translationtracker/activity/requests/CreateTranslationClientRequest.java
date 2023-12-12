package com.nashss.se.translationtracker.activity.requests;

import com.nashss.se.translationtracker.utils.IdGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateTranslationClientRequest.Builder.class)
public class CreateTranslationClientRequest {
    private final String customerId;
    private final String translationClientId;
    private final String translationClientName;
    private final String clientType;

    private CreateTranslationClientRequest(String customerId, String translationClientName,
                                           String clientType) {
        this.customerId = customerId;
        this.translationClientId = IdGenerator.newTranslationClientId(clientType, translationClientName);
        this.translationClientName = translationClientName;
        this.clientType = clientType;
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

    public String getClientType() {
        return clientType;
    }

    @Override
    public String toString() {
        return "CreateTranslationClientRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationClientId='" + translationClientId + '\'' +
                ", translationClientName='" + translationClientName + '\'' +
                ", clientType='" + clientType + '\'' +
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
        private String clientType;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationClientName(String translationClientName) {
            this.translationClientName = translationClientName;
            return this;
        }

        public Builder withClientType(String clientType) {
            this.clientType = clientType;
            return this;
        }

        public CreateTranslationClientRequest build() {
            return new CreateTranslationClientRequest(customerId, translationClientName,
                    clientType);
        }
    }
}
