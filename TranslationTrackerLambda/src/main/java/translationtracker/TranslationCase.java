package translationtracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "projectType")
    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
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

    @DynamoDBAttribute(attributeName = "rushJob")
    public Boolean getRushJob() {
        return rushJob;
    }

    public void setRushJob(Boolean rushJob) {
        this.rushJob = rushJob;
    }

    @DynamoDBTypeConverted(converter = TranslationCaseUpdateConverter.class)
    @DynamoDBAttribute(attributeName = "progressLog")
    public List<TranslationCaseUpdate> getProgressLog() {
        return progressLog;
    }

    public void setProgressLog(List<TranslationCaseUpdate> progressLog) {
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

    @DynamoDBTypeConverted(converter = PaymentHistoryRecordConverter.class)
    @DynamoDBAttribute(attributeName = "paymentRecord")
    public PaymentHistoryRecord getPaymentRecord() {
        return paymentRecord;
    }

    public void setPaymentRecord(PaymentHistoryRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranslationCase that = (TranslationCase) o;
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
                Objects.equals(getWordsPerHour(), that.getWordsPerHour())&&
                Objects.equals(getPaymentRecord(), that.getPaymentRecord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationCaseId(), getTranslationClientId(), getCaseNickname(), getSourceTextTitle(),
                getSourceTextAuthor(), getTranslatedTitle(), getProjectType(), getDueDate(), getStartDate(), getEndDate(),
                getOpenCase(), getRushJob(), getProgressLog(), getTotalWorkingHours(),
                getWordsPerHour(), getPaymentRecord());
    }
}
