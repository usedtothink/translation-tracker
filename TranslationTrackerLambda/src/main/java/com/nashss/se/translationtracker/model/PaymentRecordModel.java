package com.nashss.se.translationtracker.model;

import java.util.Objects;

public class PaymentRecordModel {
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

    private PaymentRecordModel(String customerId, String translationCaseId, String translationClientId,
                               Boolean casePaid, String paymentDate, Boolean onTime, Double grossPayment,
                               Double taxRate, Double payRate, String payRateUnit, Integer wordCount,
                               String wordCountUnit) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
        this.translationClientId = translationClientId;
        this.casePaid = casePaid;
        this.paymentDate = paymentDate;
        this.onTime = onTime;
        this.grossPayment = grossPayment;
        this.taxRate = taxRate;
        this.payRate = payRate;
        this.payRateUnit = payRateUnit;
        this.wordCount = wordCount;
        this.wordCountUnit = wordCountUnit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public Boolean getCasePaid() {
        return casePaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Boolean getOnTime() {
        return onTime;
    }

    public Double getGrossPayment() {
        return grossPayment;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public Double getPayRate() {
        return payRate;
    }

    public String getPayRateUnit() {
        return payRateUnit;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public String getWordCountUnit() {
        return wordCountUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentRecordModel that = (PaymentRecordModel) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getTranslationCaseId(), that.getTranslationCaseId()) &&
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
        return Objects.hash(getCustomerId(), getTranslationCaseId(), getTranslationClientId(), getCasePaid(),
                getPaymentDate(), getOnTime(), getGrossPayment(), getTaxRate(), getPayRate(), getPayRateUnit(),
                getWordCount(), getWordCountUnit());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
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

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withTranslationCaseId(String translationCaseId) {
            this.translationCaseId = translationCaseId;
            return this;
        }

        public Builder withTranslationClientId(String translationClientId) {
            this.translationClientId = translationClientId;
            return this;
        }

        public Builder withCasePaid(Boolean casePaid) {
            this.casePaid = casePaid;
            return this;
        }

        public Builder withPaymentDate (String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder withOnTime(Boolean onTime) {
            this.onTime = onTime;
            return this;
        }

        public Builder withGrossPayment (Double grossPayment) {
            this.grossPayment = grossPayment;
            return this;
        }

        public Builder withTaxRate (Double taxRate) {
            this.taxRate = taxRate;
            return this;
        }

        public Builder withPayRate (Double payRate) {
            this.payRate = payRate;
            return this;
        }

        public Builder withPayRateUnit (String payRateUnit) {
            this.payRateUnit = payRateUnit;
            return this;
        }

        public Builder withWordCount (Integer wordCount) {
            this.wordCount = wordCount;
            return this;
        }

        public Builder withWordCountUnit (String wordCountUnit) {
            this.wordCountUnit = wordCountUnit;
            return this;
        }

        public PaymentRecordModel build() {
            return new PaymentRecordModel(customerId, translationCaseId, translationClientId, casePaid, paymentDate,
                    onTime, grossPayment, taxRate, payRate, payRateUnit, wordCount, wordCountUnit);
        }
    }
}
