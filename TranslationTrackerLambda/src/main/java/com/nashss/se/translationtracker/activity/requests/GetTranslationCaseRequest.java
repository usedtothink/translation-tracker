package com.nashss.se.translationtracker.activity.requests;

public class GetTranslationCaseRequest {
    private final String translationCaseId;

    private GetTranslationCaseRequest(String translationCaseId) {
        this.translationCaseId = translationCaseId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    @Override
    public String toString() {
        return "GetTransalationCaseRequest{" +
                "TranslationCaseId='" + translationCaseId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String translationCaseId;

        public Builder withTranslationCaseId(String translationCaseId) {
            this.translationCaseId = translationCaseId;
            return this;
        }

        public GetTranslationCaseRequest build() {
            return new GetTranslationCaseRequest(translationCaseId);
        }
    }
}
