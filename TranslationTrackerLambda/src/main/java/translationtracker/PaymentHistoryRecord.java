package translationtracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a payment history record.
 */
public class PaymentHistoryRecord {
    private final String paymentHistoryId;
    private final String translationCaseId;
    private final String translationClientId;
    private Boolean casePaid;
    private String paymentDate;
    private Boolean onTime;
    private Double grossPayment;
    private Double taxRate;
    private Double payRate;
    private String payRateUnit;
    private Integer wordCount;
    private String wordCountUnit;


    private PaymentHistoryRecord(String translationCaseId, String translationClientId, Boolean casePaid,
                                 String paymentDate, Boolean onTime, Double grossPayment, Double taxRate,
                                 Double payRate, String payRateUnit, Integer wordCount, String wordCountUnit) {
        this.paymentHistoryId = "case:" + translationCaseId + "client:" + translationClientId;
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

    /**
     * Makes a defensive copy of a single PaymentHistoryRecord object.
     * @param original the original PaymentHistoryRecord to be copied.
     * @return the new, copied PaymentHistoryRecord.
     */
    public static PaymentHistoryRecord defensiveCopyPaymentHistory(PaymentHistoryRecord original) {
        return PaymentHistoryRecord.builder()
                .withTranslationCaseId(original.getTranslationCaseId())
                .withTranslationClientId(original.getTranslationClientId())
                .withCasePaid(original.getCasePaid())
                .withPaymentDate(original.getPaymentDate())
                .withOnTime(original.getOnTime())
                .withGrossPayment(original.getGrossPayment())
                .withTaxRate(original.getTaxRate())
                .withPayRate(original.getPayRate())
                .withPayRateUnit(original.getPayRateUnit())
                .withWordCount(original.getWordCount())
                .withWordCountUnit(original.getWordCountUnit())
                .build();
    }

    /**
     * Makes a defensive copy of a list of PaymentHistoryRecords.
     * @param originalList the original list of PaymentHistoryRecords to be copied.
     * @return the new, copied list of PaymentHistoryRecords.
     */
    public static List<PaymentHistoryRecord> defensiveCopyPaymentHistoryList(List<PaymentHistoryRecord> originalList) {
        List<PaymentHistoryRecord> outputList = new ArrayList<>();

        for (PaymentHistoryRecord record : originalList) {
            outputList.add(defensiveCopyPaymentHistory(record));
        }

        return outputList;
    }

    // paymentHistoryId, translationCaseId, and translationCaseId shouldn't be changed
    public String getPaymentHistoryId() {
        return paymentHistoryId;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    // All the rest can be set or changed after the object is initialized
    public Boolean getCasePaid() {
        return casePaid;
    }

    public void setCasePaid(Boolean casePaid) {
        this.casePaid = casePaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(Boolean onTime) {
        this.onTime = onTime;
    }

    public Double getGrossPayment() {
        return grossPayment;
    }

    public void setGrossPayment(Double grossPayment) {
        this.grossPayment = grossPayment;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public String getPayRateUnit() {
        return payRateUnit;
    }

    public void setPayRateUnit(String payRateUnit) {
        this.payRateUnit = payRateUnit;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public String getWordCountUnit() {
        return wordCountUnit;
    }

    public void setWordCountUnit(String wordCountUnit) {
        this.wordCountUnit = wordCountUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentHistoryRecord that = (PaymentHistoryRecord) o;
        return Objects.equals(getPaymentHistoryId(), that.getPaymentHistoryId()) &&
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
        return Objects.hash(getPaymentHistoryId(), getTranslationCaseId(), getTranslationClientId(),
                getCasePaid(), getPaymentDate(), getOnTime(), getGrossPayment(), getTaxRate(), getPayRate(),
                getPayRateUnit(), getWordCount(), getWordCountUnit());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String paymentHistoryId;
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

        public PaymentHistoryRecord build() {
            return new PaymentHistoryRecord(translationCaseId, translationClientId, casePaid, paymentDate, onTime,
                    grossPayment, taxRate, payRate, payRateUnit, wordCount, wordCountUnit);
        }
    }
}