package com.nashss.se.translationtracker.model;

import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCaseUpdate;
import com.nashss.se.translationtracker.types.ProjectType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TranslationCaseModel {
    private String translationCaseId;
    private String translationClientId;
    private String caseNickname;
    private String sourceTextTitle;
    private String sourceTextAuthor;
    private String translatedTitle;
    private ProjectType projectType;
    private String dueDate;
    private String startDate;
    private String endDate;
    private Boolean openCase;
    private Boolean rushJob;
    private List<TranslationCaseUpdate> progressLog;
    private Double totalWorkingHours;
    private Double wordsPerHour;
    private PaymentHistoryRecord paymentRecord;

    private TranslationCaseModel(String translationCaseId, String translationClientId, String caseNickname,
                                 String sourceTextTitle, String sourceTextAuthor, String translatedTitle,
                                 ProjectType projectType, String dueDate, String startDate, String endDate,
                                 Boolean openCase, Boolean rushJob, List<TranslationCaseUpdate> progressLog,
                                 Double totalWorkingHours, Double wordsPerHour, PaymentHistoryRecord paymentRecord) {
        this.translationCaseId = translationCaseId;
        this.translationClientId = translationClientId;
        this.caseNickname = caseNickname;
        this.sourceTextTitle = sourceTextTitle;
        this.sourceTextAuthor = sourceTextAuthor;
        this.translatedTitle = translatedTitle;
        this.projectType = projectType;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.openCase = openCase;
        this.rushJob = rushJob;
        this.progressLog = progressLog.stream()
                .map(TranslationCaseUpdate::defensiveCopyTranslationCaseUpdate)
                .collect(Collectors.toList());
        this.totalWorkingHours = totalWorkingHours;
        this.wordsPerHour = wordsPerHour;
        this.paymentRecord = PaymentHistoryRecord.defensiveCopyPaymentHistory(paymentRecord);
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public String getCaseNickname() {
        return caseNickname;
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

    public ProjectType getProjectType() {
        return projectType;
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

    public List<TranslationCaseUpdate> getProgressLog() {
        return progressLog.stream()
                .map(TranslationCaseUpdate::defensiveCopyTranslationCaseUpdate)
                .collect(Collectors.toList());
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public Double getWordsPerHour() {
        return wordsPerHour;
    }

    public PaymentHistoryRecord getPaymentRecord() {
        return PaymentHistoryRecord.defensiveCopyPaymentHistory(paymentRecord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranslationCaseModel that = (TranslationCaseModel) o;
        return Objects.equals(getTranslationCaseId(), that.getTranslationCaseId()) &&
                Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getCaseNickname(), that.getCaseNickname()) &&
                Objects.equals(getSourceTextTitle(), that.getSourceTextTitle()) &&
                Objects.equals(getSourceTextAuthor(), that.getSourceTextAuthor()) &&
                Objects.equals(getTranslatedTitle(), that.getTranslatedTitle()) &&
                Objects.equals(getProjectType(), that.getProjectType()) &&
                Objects.equals(getDueDate(), that.getDueDate()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getOpenCase(), that.getOpenCase()) &&
                Objects.equals(getRushJob(), that.getRushJob()) &&
                Objects.equals(getProgressLog(), that.getProgressLog()) &&
                Objects.equals(getTotalWorkingHours(), that.getTotalWorkingHours()) &&
                Objects.equals(getWordsPerHour(), that.getWordsPerHour()) &&
                Objects.equals(getPaymentRecord(), that.getPaymentRecord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationCaseId(), getTranslationClientId(), getCaseNickname(), getSourceTextTitle(),
                getSourceTextAuthor(), getTranslatedTitle(), getProjectType(), getDueDate(), getStartDate(),
                getEndDate(), getOpenCase(), getRushJob(), getProgressLog(), getTotalWorkingHours(),
                getWordsPerHour(), getPaymentRecord());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String translationCaseId;
        private String translationClientId;
        private String caseNickname;
        private String sourceTextTitle;
        private String sourceTextAuthor;
        private String translatedTitle;
        private ProjectType projectType;
        private String dueDate;
        private String startDate;
        private String endDate;
        private Boolean openCase;
        private Boolean rushJob;
        private List<TranslationCaseUpdate> progressLog;
        private Double totalWorkingHours;
        private Double wordsPerHour;
        private PaymentHistoryRecord paymentRecord;

        public Builder withTranslationCaseId(String translationCaseId){
            this.translationCaseId = translationCaseId;
            return this;
        }
        public Builder withTranslationClientId(String translationClientId){
            this.translationClientId = translationClientId;
            return this;
        }
        public Builder withCaseNickname(String caseNickname){
            this.caseNickname = caseNickname;
            return this;
        }
        public Builder withSourceTextTitle(String sourceTextTitle){
            this.sourceTextTitle = sourceTextTitle;
            return this;
        }
        public Builder withSourceTextAuthor(String sourceTextAuthor){
            this.sourceTextAuthor = sourceTextAuthor;
            return this;
        }
        public Builder withTranslatedTitle(String translatedTitle){
            this.translatedTitle = translatedTitle;
            return this;
        }
        public Builder withProjectType(ProjectType projectType){
            this.projectType = projectType;
            return this;
        }
        public Builder withDueDate(String dueDate){
            this.dueDate = dueDate;
            return this;
        }
        public Builder withStartDate(String startDate){
            this.startDate = startDate;
            return this;
        }
        public Builder withEndDate(String endDate){
            this.endDate = endDate;
            return this;
        }
        public Builder withOpenCase(Boolean openCase){
            this.openCase = openCase;
            return this;
        }
        public Builder withRushJob(Boolean rushJob){
            this.rushJob = rushJob;
            return this;
        }
        public Builder withProgressLog(List<TranslationCaseUpdate> progressLog){
            this.progressLog = progressLog;
            return this;
        }
        public Builder withTotalWorkingHours(Double totalWorkingHours){
            this.totalWorkingHours = totalWorkingHours;
            return this;
        }
        public Builder withWordsPerHour(Double wordsPerHour){
            this.wordsPerHour = wordsPerHour;
            return this;
        }
        public Builder withPaymentHistoryRecord(PaymentHistoryRecord paymentRecord) {
            this.paymentRecord = paymentRecord;
            return this;
        }

        public TranslationCaseModel build() {
            return new TranslationCaseModel(translationCaseId, translationClientId, caseNickname,
                    sourceTextTitle, sourceTextAuthor, translatedTitle, projectType, dueDate, startDate,
                    endDate, openCase, rushJob, progressLog, totalWorkingHours, wordsPerHour, paymentRecord);
        }
    }
}
