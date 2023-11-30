package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = GetAllTranslationClientsRequest.Builder.class)
public class GetAllTranslationClientsRequest {
    private final String customerId;

    private GetAllTranslationClientsRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "GetAllTranslationClientsRequest{" +
                "customerId='" + customerId + '\'' +
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

        public GetAllTranslationClientsRequest build() {
            return new GetAllTranslationClientsRequest(customerId);
        }
    }
}
