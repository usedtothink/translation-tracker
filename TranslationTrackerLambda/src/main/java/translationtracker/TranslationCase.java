package translationtracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.Map;

/**
 * Represents a record in the translationCases table.
 */
@DynamoDBTable(tableName = "translationCases")
public class TranslationCase {
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

    @DynamoDBHashKey(attributeName = "translationClientId")
    public String getTranslationClientId() {
        return translationClientId;
    }

    public void setTranslationClientId(String translationClientId) {
        this.translationClientId = translationClientId;
    }

    @DynamoDBRangeKey(attributeName = "translationCaseId")
    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public void setTranslationCaseId(String translationCaseId) {
        this.translationCaseId = translationCaseId;
    }

    @DynamoDBAttribute(attributeName = "caseNickname")
    public String getCaseNickname() {
        return caseNickname;
    }

    public void setCaseNickname(String caseNickname) {
        this.caseNickname = caseNickname;
    }

    @DynamoDBAttribute(attributeName = "sourceTextTitle")
    public String getSourceTextTitle() {
        return sourceTextTitle;
    }

    public void setSourceTextTitle(String sourceTextTitle) {
        this.sourceTextTitle = sourceTextTitle;
    }

    @DynamoDBAttribute(attributeName = "sourceTextAuthor")
    public String getSourceTextAuthor() {
        return sourceTextAuthor;
    }

    public void setSourceTextAuthor(String sourceTextAuthor) {
        this.sourceTextAuthor = sourceTextAuthor;
    }

    @DynamoDBAttribute(attributeName = "translatedTitle")
    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    @DynamoDBAttribute(attributeName = "rate")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @DynamoDBAttribute(attributeName = "rateUnit")
    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    @DynamoDBAttribute(attributeName = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @DynamoDBAttribute(attributeName = "countUnit")
    public String getCountUnit() {
        return countUnit;
    }

    public void setCountUnit(String countUnit) {
        this.countUnit = countUnit;
    }

    @DynamoDBAttribute(attributeName = "grossPayment")
    public Double getGrossPayment() {
        return grossPayment;
    }

    public void setGrossPayment(Double grossPayment) {
        this.grossPayment = grossPayment;
    }

    @DynamoDBAttribute(attributeName = "taxRate")
    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @DynamoDBAttribute(attributeName = "projectType")
    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @DynamoDBAttribute(attributeName = "dueDate")
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @DynamoDBAttribute(attributeName = "startDate")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @DynamoDBAttribute(attributeName = "getDate")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @DynamoDBAttribute(attributeName = "openCase")
    public Boolean getOpenCase() {
        return openCase;
    }

    public void setOpenCase(Boolean openCase) {
        this.openCase = openCase;
    }

    @DynamoDBAttribute(attributeName = "casePaid")
    public Boolean getCasePaid() {
        return casePaid;
    }

    public void setCasePaid(Boolean casePaid) {
        this.casePaid = casePaid;
    }

    @DynamoDBAttribute(attributeName = "rushJob")
    public Boolean getRushJob() {
        return rushJob;
    }

    public void setRushJob(Boolean rushJob) {
        this.rushJob = rushJob;
    }

    @DynamoDBTypeConverted(converter = ProgressLogMapConverter.class)
    @DynamoDBAttribute(attributeName = "progressLog")
    public Map<String, Map<String, String>> getProgressLog() {
        return progressLog;
    }

    public void setProgressLog(Map<String, Map<String, String>> progressLog) {
        this.progressLog = progressLog;
    }

    @DynamoDBAttribute(attributeName = "totalWorkingHours")
    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(Double totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    @DynamoDBAttribute(attributeName = "wordsPerHour")
    public Double getWordsPerHour() {
        return wordsPerHour;
    }

    public void setWordsPerHour(Double wordsPerHour) {
        this.wordsPerHour = wordsPerHour;
    }
}
