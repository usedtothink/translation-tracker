package com.nashss.se.translationtracker.activity.requests;

import com.nashss.se.translationtracker.utils.IdGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateTranslationCaseRequest.Builder.class)
public class CreateTranslationCaseRequest {
    private final String customerId;
    private final String projectType;
    private final String translationCaseId;
    private final String caseNickname;

    private CreateTranslationCaseRequest(String customerId, String projectType, String caseNickname) {
        this.customerId = customerId;
        this.projectType = projectType;
        this.translationCaseId = IdGenerator.newTranslationCaseId(projectType, caseNickname);
        this.caseNickname = caseNickname;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public String getCaseNickname() {
        return caseNickname;
    }

    @Override
    public String toString() {
        return "CreateTranslationCaseRequest{" +
                "customerId='" + customerId + '\'' +
                ", projectType='" + projectType + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                ", caseNickname='" + caseNickname + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String projectType;
        private String caseNickname;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withProjectType(String projectType) {
            this.projectType = projectType;
            return this;
        }

        public Builder withCaseNickname(String caseNickname) {
            this.caseNickname = caseNickname;
            return this;
        }

        public CreateTranslationCaseRequest build() {
            return new CreateTranslationCaseRequest(customerId, projectType, caseNickname);
        }
    }
}
