package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdatePaymentRecordRequest.Builder.class)
public class UpdatePaymentRecordRequest {
    private String customerId;
    private String translationCaseId;
    private String translationCaseNickname;
    private String translationClientId;
    private String translationClientName;
    private Boolean casePaid;
    private String paymentDate;
    private Boolean onTime;
    private Double grossPayment;
    private Double taxRate;
    private Double payRate;
    private String payRateUnit;
    private Integer wordCount;
    private String wordCountUnit;

    private UpdatePaymentRecordRequest(String customerId, String translationCaseId, String translationCaseNickname,
                                       String translationClientId, String translationClientName, Boolean casePaid,
                                       String paymentDate, Boolean onTime, Double grossPayment, Double taxRate,
                                       Double payRate, String payRateUnit, Integer wordCount, String wordCountUnit) {
        this.customerId = customerId;
        this.translationCaseId = translationCaseId;
        this.translationCaseNickname = translationCaseNickname;
        this.translationClientId = translationClientId;
        this.translationClientName = translationClientName;
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

    public String getTranslationCaseNickname() {
        return translationCaseNickname;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public String getTranslationClientName() {
        return translationClientName;
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
    public String toString() {
        return "UpdatePaymentRecordRequest{" +
                "customerId='" + customerId + '\'' +
                ", translationCaseId='" + translationCaseId + '\'' +
                ", translationCaseNickname='" + translationCaseNickname + '\'' +
                ", translationClientId='" + translationClientId + '\'' +
                ", translationClientName='" + translationClientName + '\'' +
                ", casePaid=" + casePaid +
                ", paymentDate='" + paymentDate + '\'' +
                ", onTime=" + onTime +
                ", grossPayment=" + grossPayment +
                ", taxRate=" + taxRate +
                ", payRate=" + payRate +
                ", payRateUnit='" + payRateUnit + '\'' +
                ", wordCount=" + wordCount +
                ", wordCountUnit='" + wordCountUnit + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String customerId;
        private String translationCaseId;
        private String translationCaseNickname;
        private String translationClientId;
        private String translationClientName;
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

        public Builder withTranslationCaseNickname(String translationCaseNickname) {
            this.translationCaseNickname = translationCaseNickname;
            return this;
        }

        public Builder withTranslationClientId(String translationClientId) {
            this.translationClientId = translationClientId;
            return this;
        }

        public Builder withTranslationClientName(String translationClientName) {
            this.translationClientName = translationClientName;
            return this;
        }

        public Builder withCasePaid(Boolean casePaid) {
            this.casePaid = casePaid;
            return this;
        }

        public Builder withPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder withOnTime(Boolean onTime) {
            this.onTime = onTime;
            return this;
        }

        public Builder withGrossPayment(Double grossPayment) {
            this.grossPayment = grossPayment;
            return this;
        }

        public Builder withTaxRate(Double taxRate) {
            this.taxRate = taxRate;
            return this;
        }

        public Builder withPayRate(Double payRate) {
            this.payRate = payRate;
            return this;
        }

        public Builder withPayRateUnit(String payRateUnit) {
            this.payRateUnit = payRateUnit;
            return this;
        }

        public Builder withWordCount(Integer wordCount) {
            this.wordCount = wordCount;
            return this;
        }
        public Builder withWordCountUnit(String wordCountUnit) {
            this.wordCountUnit = wordCountUnit;
            return this;
        }

        public UpdatePaymentRecordRequest build() {
            return new UpdatePaymentRecordRequest(customerId, translationCaseId, translationCaseNickname,
                                                  translationClientId, translationClientName, casePaid, paymentDate,
                                                  onTime, grossPayment, taxRate, payRate, payRateUnit, wordCount,
                                                  wordCountUnit);
        }
    }
}
