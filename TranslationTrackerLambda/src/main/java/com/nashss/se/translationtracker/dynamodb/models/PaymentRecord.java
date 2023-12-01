package com.nashss.se.translationtracker.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a payment history record.
 */
@DynamoDBTable(tableName = "payment_records")
public class PaymentRecord {
    public static final String CUSTOMER_INDEX = "PaymentCustomerIdIndex";
    public static final String CLIENT_INDEX = "PaymentTranslationClientIdIndex";
    private String customerId;
    private String translationCaseId;
    private String translationClientId;
    private Boolean casePaid;
    private String paymentDate;
    private Boolean onTime;
    private Double grossPayment;
    private Double taxRate;
    private Double payRate;
    private String payRateUnit;
    private Integer wordCount;
    private String wordCountUnit;

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

    @DynamoDBAttribute(attributeName = "translationClientId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = CLIENT_INDEX, attributeName = "TranslationClientId")
    public String getTranslationClientId() {
        return translationClientId;
    }

    public void setTranslationClientId(String translationClientId) {
        this.translationClientId = translationClientId;
    }

    @DynamoDBAttribute(attributeName = "casePaid")
    public Boolean getCasePaid() {
        return casePaid;
    }

    public void setCasePaid(Boolean casePaid) {
        this.casePaid = casePaid;
    }

    @DynamoDBAttribute(attributeName = "paymentDate")
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @DynamoDBAttribute(attributeName = "onTime")
    public Boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
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

    @DynamoDBAttribute(attributeName = "payRate")
    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    @DynamoDBAttribute(attributeName = "payRateUnit")
    public String getPayRateUnit() {
        return payRateUnit;
    }

    public void setPayRateUnit(String payRateUnit) {
        this.payRateUnit = payRateUnit;
    }

    @DynamoDBAttribute(attributeName = "wordCount")
    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    @DynamoDBAttribute(attributeName = "wordCountUnit")
    public String getWordCountUnit() {
        return wordCountUnit;
    }

    public void setWordCountUnit(String wordCountUnit) {
        this.wordCountUnit = wordCountUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRecord that = (PaymentRecord) o;
        return Objects.equals(getTranslationCaseId(), that.getTranslationCaseId()) &&
                Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getCasePaid(), that.getCasePaid()) &&
                Objects.equals(getPaymentDate(), that.getPaymentDate()) &&
                Objects.equals(getOnTime(), that.getOnTime()) &&
                Objects.equals(getGrossPayment(), that.getGrossPayment()) &&
                Objects.equals(getTaxRate(), that.getTaxRate()) &&
                Objects.equals(getPayRate(), that.getPayRate()) &&
                Objects.equals(getPayRateUnit(), that.getPayRateUnit()) &&
                Objects.equals(getWordCount(), that.getWordCount()) &&
                Objects.equals(getWordCountUnit(), that.getWordCountUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationCaseId(), getTranslationClientId(), getCasePaid(),
                getPaymentDate(), getOnTime(), getGrossPayment(), getTaxRate(), getPayRate(), getPayRateUnit(),
                getWordCount(), getWordCountUnit());
    }
}
