package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddProgressUpdateRequest.Builder.class)
public class AddProgressUpdateRequest {
    private final String customerId;
    private final String translationCaseId;
    private final Integer wordCount;
    private final String startDate;
    private final String endDate;
    private final String startTime;
    private final String endTime;
    private final String notes;

    private AddProgressUpdateRequest(String customerId, String translationCaseId, Integer wordCount, String startDate,
                           String endDate, String startTime, String endTime, String notes) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
        this.wordCount = wordCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "AddProgressUpdateRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                ", wordCount=" + wordCount +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", notes=" + notes +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String translationCaseId;
        private Integer wordCount;
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        private String notes;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationCaseId(String translationCaseId) {
            this.translationCaseId = translationCaseId;
            return this;
        }

        public Builder withWordCount(Integer wordCount) {
            this.wordCount = wordCount;
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

        public Builder withStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public AddProgressUpdateRequest build() {
            return new AddProgressUpdateRequest(customerId, translationCaseId, wordCount, startDate, endDate, startTime,
                    endTime, notes);
        }
    }
}
