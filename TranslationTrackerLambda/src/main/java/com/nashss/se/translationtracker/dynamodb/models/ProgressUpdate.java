package com.nashss.se.translationtracker.dynamodb.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

/**
 * Represents a progress update record.
 */
@JsonDeserialize(builder = ProgressUpdate.Builder.class)
public final class ProgressUpdate {
    private final String customerId;
    private final String translationCaseId;
    private final Integer wordCount;
    private final String startDate;
    private final String endDate;
    private final String startTime;
    private final String endTime;
    private final String notes;

    private ProgressUpdate(String customerId, String translationCaseId, Integer wordCount, String startDate,
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

    /**
     * Makes a defensive copy of a single TranslationCaseUpdate object.
     * @param original the original TranslationCaseUpdate to be copied.
     * @return the new, copied TranslationCaseUpdate.
     */
    public static ProgressUpdate defensiveCopyTranslationCaseUpdate(ProgressUpdate original) {
        return ProgressUpdate.builder()
                .withCustomerId(original.getCustomerId())
                .withTranslationCaseId(original.getTranslationCaseId())
                .withWordCount(original.getWordCount())
                .withStartDate(original.getStartDate())
                .withEndDate(original.getEndDate())
                .withStartTime(original.getStartTime())
                .withEndTime(original.getEndTime())
                .withNotes(original.getNotes())
                .build();
    }

    @Override
    public String toString() {
        return "ProgressUpdate{" +
                "customerId='" + customerId + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                ", wordCount=" + wordCount +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProgressUpdate that = (ProgressUpdate) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getTranslationCaseId(), that.getTranslationCaseId()) &&
                Objects.equals(getWordCount(), that.getWordCount()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(),
                getTranslationCaseId(), getWordCount(), getStartDate(), getEndDate(),
                getStartTime(), getEndTime(), getNotes());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

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

        public ProgressUpdate build() {
            return new ProgressUpdate(customerId, translationCaseId, wordCount, startDate, endDate, startTime,
                    endTime, notes);
        }

    }
}
