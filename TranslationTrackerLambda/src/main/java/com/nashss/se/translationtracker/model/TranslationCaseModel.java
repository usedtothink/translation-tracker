package com.nashss.se.translationtracker.model;

import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.types.ProjectType;

import java.util.List;
import java.util.Objects;

public class TranslationCaseModel {
    private String customerId;
    private String translationCaseId;
    private String caseNickname;
    private ProjectType projectType;
    private String translationClientId;
    private String sourceTextTitle;
    private String sourceTextAuthor;
    private String translatedTitle;
    private String dueDate;
    private String startDate;
    private String endDate;
    private Boolean openCase;
    private Boolean rushJob;
    private List<ProgressUpdate> progressLog;
    private Double totalWorkingHours;
    private Double wordsPerHour;

    private TranslationCaseModel(String customerId, String translationCaseId, String caseNickname,
                                 ProjectType projectType, String translationClientId, String sourceTextTitle,
                                 String sourceTextAuthor, String translatedTitle, String dueDate, String startDate,
                                 String endDate, Boolean openCase, Boolean rushJob, List<ProgressUpdate> progressLog,
                                 Double totalWorkingHours, Double wordsPerHour) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
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
        this.progressLog = progressLog;
        this.totalWorkingHours = totalWorkingHours;
        this.wordsPerHour = wordsPerHour;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public String getCaseNickname() {
        return caseNickname;
    }

    public ProjectType getProjectType() {
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

    public List<ProgressUpdate> getProgressLog() {
        return progressLog;
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public Double getWordsPerHour() {
        return wordsPerHour;
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
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getTranslationCaseId(), that.getTranslationCaseId()) &&
                Objects.equals(getCaseNickname(), that.getCaseNickname()) &&
                getProjectType() == that.getProjectType() &&
                Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getSourceTextTitle(), that.getSourceTextTitle()) &&
                Objects.equals(getSourceTextAuthor(), that.getSourceTextAuthor()) &&
                Objects.equals(getTranslatedTitle(), that.getTranslatedTitle()) &&
                Objects.equals(getDueDate(), that.getDueDate()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getOpenCase(), that.getOpenCase()) &&
                Objects.equals(getRushJob(), that.getRushJob()) &&
                Objects.equals(getProgressLog(), that.getProgressLog()) &&
                Objects.equals(getTotalWorkingHours(), that.getTotalWorkingHours()) &&
                Objects.equals(getWordsPerHour(), that.getWordsPerHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getTranslationCaseId(), getCaseNickname(),
                getProjectType(), getTranslationClientId(), getSourceTextTitle(),
                getSourceTextAuthor(), getTranslatedTitle(), getDueDate(), getStartDate(),
                getEndDate(), getOpenCase(), getRushJob(), getProgressLog(),
                getTotalWorkingHours(), getWordsPerHour());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;
        private String translationCaseId;
        private String caseNickname;
        private ProjectType projectType;
        private String translationClientId;
        private String sourceTextTitle;
        private String sourceTextAuthor;
        private String translatedTitle;
        private String dueDate;
        private String startDate;
        private String endDate;
        private Boolean openCase;
        private Boolean rushJob;
        private List<ProgressUpdate> progressLog;
        private Double totalWorkingHours;
        private Double wordsPerHour;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }
        public Builder withTranslationCaseId(String translationCaseId){
            this.translationCaseId = translationCaseId;
            return this;
        }
        public Builder withCaseNickname(String caseNickname){
            this.caseNickname = caseNickname;
            return this;
        }
        public Builder withProjectType(ProjectType projectType){
            this.projectType = projectType;
            return this;
        }
        public Builder withTranslationClientId(String translationClientId){
            this.translationClientId = translationClientId;
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
        public Builder withProgressLog(List<ProgressUpdate> progressLog){
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

        public TranslationCaseModel build() {
            return new TranslationCaseModel(customerId, translationCaseId, caseNickname, projectType,
                    translationClientId, sourceTextTitle, sourceTextAuthor, translatedTitle, dueDate, startDate,
                    endDate, openCase, rushJob, progressLog, totalWorkingHours, wordsPerHour);
        }
    }
}
