package com.nashss.se.translationtracker.activity.requests;

import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCaseUpdate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateTranslationCaseRequest.class)
public class UpdateTranslationCaseRequest {
    private String customerId;
    private final String translationCaseId;
    private final String sourceTextTitle;
    private final String sourceTextAuthor;
    private final String translatedTitle;
    private final String dueDate;
    private final String startDate;
    private final String endDate;
    private final Boolean openCase;
    private final Boolean rushJob;
    private final TranslationCaseUpdate progressUpdate;
    private final Double totalWorkingHours;
    private final Double wordsPerHour;
    private final PaymentHistoryRecord paymentRecord;

    private UpdateTranslationCaseRequest(String customerId, String translationCaseId, String sourceTextTitle,
                                         String sourceTextAuthor, String translatedTitle, String dueDate,
                                         String startDate, String endDate, Boolean openCase, Boolean rushJob,
                                         TranslationCaseUpdate progressUpdate, Double totalWorkingHours,
                                         Double wordsPerHour, PaymentHistoryRecord paymentRecord) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
        this.sourceTextTitle = sourceTextTitle;
        this.sourceTextAuthor = sourceTextAuthor;
        this.translatedTitle = translatedTitle;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.openCase = openCase;
        this.rushJob = rushJob;
        this.progressUpdate = progressUpdate;
        this.totalWorkingHours = totalWorkingHours;
        this.wordsPerHour = wordsPerHour;
        this.paymentRecord = paymentRecord;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
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

    public TranslationCaseUpdate getProgressUpdate() {
        return progressUpdate;
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public Double getWordsPerHour() {
        return wordsPerHour;
    }

    public PaymentHistoryRecord getPaymentRecord() {
        return paymentRecord;
    }

    @Override
    public String toString() {
        return "UpdateTranslationCaseRequest{ " +
                "customerId='" + customerId + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                ", sourceTextTitle='" + sourceTextTitle + '\'' +
                ", sourceTextAuthor='" + sourceTextAuthor + '\'' +
                ", translatedTitle='" + translatedTitle + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", openCase=" + openCase +
                ", rushJob=" + rushJob +
                ", progressUpdate=" + progressUpdate +
                ", totalWorkingHours=" + totalWorkingHours +
                ", wordsPerHour=" + wordsPerHour +
                ", paymentRecord=" + paymentRecord +
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
        private String sourceTextTitle;
        private String sourceTextAuthor;
        private String translatedTitle;
        private String dueDate;
        private String startDate;
        private String endDate;
        private Boolean openCase;
        private Boolean rushJob;
        private TranslationCaseUpdate progressUpdate;
        private Double totalWorkingHours;
        private Double wordsPerHour;
        private PaymentHistoryRecord paymentRecord;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationCaseId(String translationCaseId) {
            this.translationCaseId = translationCaseId;
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

        public Builder withProgressUpdate(TranslationCaseUpdate progressUpdate) {
            this.progressUpdate = progressUpdate;
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

        public Builder withPaymentHistoryRecord(PaymentHistoryRecord paymentRecord) {
            this.paymentRecord = paymentRecord;
            return this;
        }

        public UpdateTranslationCaseRequest build() {
            return new UpdateTranslationCaseRequest(customerId, translationCaseId, sourceTextTitle, sourceTextAuthor,
                    translatedTitle, dueDate, startDate, endDate, openCase, rushJob, progressUpdate,
                    totalWorkingHours, wordsPerHour, paymentRecord);
        }
    }
}
