package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = GetPaymentRecordRequest.Builder.class)
public class GetPaymentRecordRequest {
    private final String customerId;
    private final String translationCaseId;

    private GetPaymentRecordRequest(String customerId, String translationCaseId) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    @Override
    public String toString() {
        return "GetPaymentRecordRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;
        private String translationCaseId;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationCaseId(String translationCaseId) {
            this.translationCaseId = translationCaseId;
            return this;
        }

        public GetPaymentRecordRequest build() {
            return new GetPaymentRecordRequest(customerId, translationCaseId);
        }
    }
}
