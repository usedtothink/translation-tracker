package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.PaymentRecordModel;

public class UpdatePaymentRecordResult {
    private final PaymentRecordModel paymentRecord;

    private UpdatePaymentRecordResult(PaymentRecordModel paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public PaymentRecordModel getPaymentRecord() {
        return paymentRecord;
    }

    @Override
    public String toString() {
        return "UpdatePaymentRecordResult{" +
                "paymentRecord=" + paymentRecord +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PaymentRecordModel paymentRecord;

        public Builder withPaymentRecord(PaymentRecordModel paymentRecord) {
            this.paymentRecord = paymentRecord;
            return this;
        }

        public UpdatePaymentRecordResult build() {
            return new UpdatePaymentRecordResult(paymentRecord);
        }
    }
}
