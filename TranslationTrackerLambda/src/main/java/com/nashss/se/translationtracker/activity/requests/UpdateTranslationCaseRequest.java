package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateTranslationCaseRequest.Builder.class)
public class UpdateTranslationCaseRequest {
    private final String customerId;
    private final String translationCaseId;
    private final String translationClientId;
    private final String sourceTextTitle;
    private final String sourceTextAuthor;
    private final String translatedTitle;
    private final String dueDate;
    private final String startDate;
    private final String endDate;
    private final Boolean openCase;
    private final Boolean rushJob;
    private final Double totalWorkingHours;
    private final Double wordsPerHour;

    private UpdateTranslationCaseRequest(String customerId, String translationCaseId, String translationClientId,
                                         String sourceTextTitle, String sourceTextAuthor, String translatedTitle,
                                         String dueDate, String startDate, String endDate, Boolean openCase,
                                         Boolean rushJob, Double totalWorkingHours, Double wordsPerHour) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
        this.translationClientId = translationClientId;
        this.sourceTextTitle = sourceTextTitle;
        this.sourceTextAuthor = sourceTextAuthor;
        this.translatedTitle = translatedTitle;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.openCase = openCase;
        this.rushJob = rushJob;
        this.totalWorkingHours = totalWorkingHours;
        this.wordsPerHour = wordsPerHour;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
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

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public Double getWordsPerHour() {
        return wordsPerHour;
    }


    @Override
    public String toString() {
        return "UpdateTranslationCaseRequest{ " +
                "customerId='" + customerId + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                ", translationClientId='" + translationClientId + '\'' +
                ", sourceTextTitle='" + sourceTextTitle + '\'' +
                ", sourceTextAuthor='" + sourceTextAuthor + '\'' +
                ", translatedTitle='" + translatedTitle + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", openCase=" + openCase +
                ", rushJob=" + rushJob +
                ", totalWorkingHours=" + totalWorkingHours +
                ", wordsPerHour=" + wordsPerHour +
                " }";
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String translationCaseId;
        private String translationClientId;
        private String sourceTextTitle;
        private String sourceTextAuthor;
        private String translatedTitle;
        private String dueDate;
        private String startDate;
        private String endDate;
        private Boolean openCase;
        private Boolean rushJob;
        private Double totalWorkingHours;
        private Double wordsPerHour;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationCaseId(String translationCaseId) {
            this.translationCaseId = translationCaseId;
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

        public Builder withTotalWorkingHours(Double totalWorkingHours) {
            this.totalWorkingHours = totalWorkingHours;
            return this;
        }

        public Builder withWordsPerHour(Double wordsPerHour) {
            this.wordsPerHour = wordsPerHour;
            return this;
        }

        public UpdateTranslationCaseRequest build() {
            return new UpdateTranslationCaseRequest(customerId, translationCaseId, translationClientId, sourceTextTitle,
                    sourceTextAuthor, translatedTitle, dueDate, startDate, endDate, openCase, rushJob,
                    totalWorkingHours, wordsPerHour);
        }
    }
}
