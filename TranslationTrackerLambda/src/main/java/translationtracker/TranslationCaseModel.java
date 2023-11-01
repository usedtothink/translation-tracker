package translationtracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TranslationCaseModel {
    private String translationCaseId;
    private String translationClientId;
    private String caseNickname;
    private String sourceTextTitle;
    private String sourceTextAuthor;
    private String translatedTitle;
    private Double rate;
    private String rateUnit;
    private Integer count;
    private String countUnit;
    private Double grossPayment;
    private Double taxRate;
    private String projectType;
    private String dueDate;
    private String startDate;
    private String endDate;
    private Boolean openCase;
    private Boolean casePaid;
    private Boolean rushJob;
    private Map<String, Map<String, String>> progressLog;
    private Double totalWorkingHours;
    private Double wordsPerHour;

    private TranslationCaseModel(String translationCaseId, String translationClientId, String caseNickname,
                                 String sourceTextTitle, String sourceTextAuthor, String translatedTitle, Double rate,
                                 String rateUnit, Integer count, String countUnit, Double grossPayment, Double taxRate,
                                 String projectType, String dueDate, String startDate, String endDate, Boolean openCase,
                                 Boolean casePaid, Boolean rushJob, Map<String, Map<String, String>> progressLog,
                                 Double totalWorkingHours, Double wordsPerHour) {
        this.translationCaseId = translationCaseId;
        this.translationClientId = translationClientId;
        this.caseNickname = caseNickname;
        this.sourceTextTitle = sourceTextTitle;
        this.sourceTextAuthor = sourceTextAuthor;
        this.translatedTitle = translatedTitle;
        this.rate = rate;
        this.rateUnit = rateUnit;
        this.count = count;
        this.countUnit = countUnit;
        this.grossPayment = grossPayment;
        this.taxRate = taxRate;
        this.projectType = projectType;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.openCase = openCase;
        this.casePaid = casePaid;
        this.rushJob = rushJob;
        this.progressLog = defensiveCopyProgressLog(progressLog);
        this.totalWorkingHours = totalWorkingHours;
        this.wordsPerHour = wordsPerHour;
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

    public Double getRate() {
        return rate;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public Integer getCount() {
        return count;
    }

    public String getCountUnit() {
        return countUnit;
    }

    public Double getGrossPayment() {
        return grossPayment;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public String getProjectType() {
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

    public Boolean getCasePaid() {
        return casePaid;
    }

    public Boolean getRushJob() {
        return rushJob;
    }

    public Map<String, Map<String, String>> getProgressLog() {
        return defensiveCopyProgressLog(progressLog);
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public Double getWordsPerHour() {
        return wordsPerHour;
    }

    private Map<String, Map<String, String>> defensiveCopyProgressLog(Map<String, Map<String, String>> original) {
        Map<String, Map<String, String>> outputProgressLog = new HashMap<>();
        for (String key : original.keySet()) {
            for (Map.Entry<String, String> internalEntry : original.get(key).entrySet()) {
                Map<String, String> outputInternalMap = new HashMap<>();
                outputInternalMap.put(internalEntry.getKey(), internalEntry.getValue());
                outputProgressLog.put(key, outputInternalMap);
            }
        }
        return outputProgressLog;
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
                Objects.equals(getRate(), that.getRate()) &&
                Objects.equals(getRateUnit(), that.getRateUnit()) &&
                Objects.equals(getCount(), that.getCount()) &&
                Objects.equals(getCountUnit(), that.getCountUnit()) &&
                Objects.equals(getGrossPayment(), that.getGrossPayment()) &&
                Objects.equals(getTaxRate(), that.getTaxRate()) &&
                Objects.equals(getProjectType(), that.getProjectType()) &&
                Objects.equals(getDueDate(), that.getDueDate()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getOpenCase(), that.getOpenCase()) &&
                Objects.equals(getCasePaid(), that.getCasePaid()) &&
                Objects.equals(getRushJob(), that.getRushJob()) &&
                Objects.equals(getProgressLog(), that.getProgressLog()) &&
                Objects.equals(getTotalWorkingHours(), that.getTotalWorkingHours()) &&
                Objects.equals(getWordsPerHour(), that.getWordsPerHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationCaseId(), getTranslationClientId(), getCaseNickname(), getSourceTextTitle(),
                getSourceTextAuthor(), getTranslatedTitle(), getRate(), getRateUnit(), getCount(), getCountUnit(),
                getGrossPayment(), getTaxRate(), getProjectType(), getDueDate(), getStartDate(), getEndDate(),
                getOpenCase(), getCasePaid(), getRushJob(), getProgressLog(), getTotalWorkingHours(),
                getWordsPerHour());
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
        private Double rate;
        private String rateUnit;
        private Integer count;
        private String countUnit;
        private Double grossPayment;
        private Double taxRate;
        private String projectType;
        private String dueDate;
        private String startDate;
        private String endDate;
        private Boolean openCase;
        private Boolean casePaid;
        private Boolean rushJob;
        private Map<String, Map<String, String>> progressLog;
        private Double totalWorkingHours;
        private Double wordsPerHour;

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
        public Builder withRate(Double rate){
            this.rate = rate;
            return this;
        }
        public Builder withRateUnit(String rateUnit){
            this.rateUnit = rateUnit;
            return this;
        }
        public Builder withCount(Integer count){
            this.count = count;
            return this;
        }
        public Builder withCountUnit(String countUnit){
            this.countUnit = countUnit;
            return this;
        }
        public Builder withGrossPayment(Double grossPayment){
            this.grossPayment = grossPayment;
            return this;
        }
        public Builder withTaxRate(Double taxRate){
            this.taxRate = taxRate;
            return this;
        }
        public Builder withProjectType(String projectType){
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
        public Builder withCasePaid(Boolean casePaid){
            this.casePaid = casePaid;
            return this;
        }
        public Builder withRushJob(Boolean rushJob){
            this.rushJob = rushJob;
            return this;
        }
        public Builder withProgressLog(Map<String, Map<String, String>> progressLog){
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
            return new TranslationCaseModel(translationCaseId, translationClientId, caseNickname,
                    sourceTextTitle, sourceTextAuthor, translatedTitle, rate,
                    rateUnit, count, countUnit, grossPayment, taxRate,
                    projectType, dueDate, startDate, endDate, openCase,
                    casePaid, rushJob, progressLog, totalWorkingHours, wordsPerHour);
        }
    }
}
