package com.nashss.se.translationtracker.dynamodb.models;

import com.nashss.se.translationtracker.converters.ProgressLogConverter;
import com.nashss.se.translationtracker.types.ProjectType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the translationCases table.
 */
@DynamoDBTable(tableName = "translation_cases")
public class TranslationCase {
    public static final String CUSTOMER_INDEX = "CaseCustomerIdIndex";
    private String customerId;
    private String translationCaseId;
    private String caseNickname;
    private ProjectType projectType;
    private String translationClientId;
    private String translationClientName;
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

    @DynamoDBAttribute(attributeName = "customerId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = CUSTOMER_INDEX, attributeName = "customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBHashKey(attributeName = "translationCaseId")
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

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "projectType")
    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    @DynamoDBAttribute(attributeName = "translationClientId")
    public String getTranslationClientId() {
        return translationClientId;
    }

    public void setTranslationClientId(String translationClientId) {
        this.translationClientId = translationClientId;
    }

    @DynamoDBAttribute(attributeName = "translationClientName")
    public String getTranslationClientName() {
        return translationClientName;
    }

    public void setTranslationClientName(String translationClientName) {
        this.translationClientName = translationClientName;
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

    @DynamoDBTypeConverted(converter = ProgressLogConverter.class)
    @DynamoDBAttribute(attributeName = "progressLog")
    public List<ProgressUpdate> getProgressLog() {
        return progressLog;
    }

    public void setProgressLog(List<ProgressUpdate> progressLog) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationCase that = (TranslationCase) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getTranslationCaseId(), that.getTranslationCaseId()) &&
                Objects.equals(getCaseNickname(), that.getCaseNickname()) &&
                getProjectType() == that.getProjectType() &&
                Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getTranslationClientName(), that.getTranslationClientName()) &&
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
        return Objects.hash(getCustomerId(), getTranslationCaseId(), getCaseNickname(), getProjectType(),
                getTranslationClientId(), getTranslationClientName(), getSourceTextTitle(), getSourceTextAuthor(),
                getTranslatedTitle(), getDueDate(), getStartDate(), getEndDate(), getOpenCase(), getRushJob(),
                getProgressLog(), getTotalWorkingHours(), getWordsPerHour());
    }
}
