package com.nashss.se.translationtracker.activity.requests;

import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.types.ProjectType;
import com.nashss.se.translationtracker.utils.IdGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = CreateTranslationCaseRequest.Builder.class)
public class CreateTranslationCaseRequest {
    private String customerId;
    private String caseNickname;
    private String projectType;
    private String translationClientId;
    private String sourceTextTitle;
    private String sourceTextAuthor;
    private String translatedTitle;
    private String dueDate;
    private String startDate;
    private String endDate;
    private Boolean openCase;
    private Boolean rushJob;

    private CreateTranslationCaseRequest(String customerId, String caseNickname, String projectType,
                                         String translationClientId, String sourceTextTitle, String sourceTextAuthor,
                                         String translatedTitle, String dueDate, String startDate,
                                         String endDate, Boolean openCase, Boolean rushJob) {
        this.customerId = customerId;
        this.caseNickname = caseNickname;
        this.projectType = projectType;
        this.translationClientId = translationClientId;
        this.sourceTextTitle = sourceTextTitle;
        this.sourceTextAuthor = sourceTextAuthor;
        this.translatedTitle = translatedTitle;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.openCase = openCase;
        this.rushJob = rushJob;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCaseNickname() {
        return caseNickname;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public String getSourceTextTitle() {
        return sourceTextTitle;
    }

    public String getSourceTextAuthor() {
        return sourceTextAuthor;
    }

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getOpenCase() {
        return openCase;
    }

    public Boolean getRushJob() {
        return rushJob;
    }

    @Override
    public String toString() {
        return "CreateTranslationCaseRequest{" +
                "customerId='" + customerId + '\'' +
                ", caseNickname='" + caseNickname + '\'' +
                ", projectType='" + projectType + '\'' +
                ", translationClientId='" + translationClientId + '\'' +
                ", sourceTextTitle='" + sourceTextTitle + '\'' +
                ", sourceTextAuthor='" + sourceTextAuthor + '\'' +
                ", translatedTitle='" + translatedTitle + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", openCase=" + openCase +
                ", rushJob=" + rushJob +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String caseNickname;
        private String projectType;
        private String translationClientId;
        private String sourceTextTitle;
        private String sourceTextAuthor;
        private String translatedTitle;
        private String dueDate;
        private String startDate;
        private String endDate;
        private Boolean openCase;
        private Boolean rushJob;
        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withCaseNickname(String caseNickname) {
            this.caseNickname = caseNickname;
            return this;
        }

        public Builder withProjectType(String projectType) {
            this.projectType = projectType;
            return this;
        }

        public Builder withTranslationClientId(String translationClientId) {
            this.translationClientId = translationClientId;
            return this;
        }

        public Builder withSourceTextTitle(String sourceTextTitle) {
            this.sourceTextTitle = sourceTextTitle;
            return this;
        }

        public Builder withSourceTextAuthor(String sourceTextAuthor) {
            this.sourceTextAuthor = sourceTextAuthor;
            return this;
        }

        public Builder withTranslatedTitle(String translatedTitle) {
            this.translatedTitle = translatedTitle;
            return this;
        }

        public Builder withDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder withStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withOpenCase(Boolean openCase) {
            this.openCase = openCase;
            return this;
        }

        public Builder withRushJob(Boolean rushJob) {
            this.rushJob = rushJob;
            return this;
        }

        public CreateTranslationCaseRequest build() {
            return new CreateTranslationCaseRequest(customerId, caseNickname, projectType, translationClientId,
                                                    sourceTextTitle, sourceTextAuthor, translatedTitle,
                                                    dueDate, startDate, endDate, openCase, rushJob);
        }
    }
}
