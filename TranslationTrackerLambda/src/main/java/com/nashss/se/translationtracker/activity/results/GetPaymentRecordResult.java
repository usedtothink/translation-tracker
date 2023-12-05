package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.PaymentRecordModel;

public class GetPaymentRecordResult {
    private final PaymentRecordModel paymentRecord;

    private GetPaymentRecordResult(PaymentRecordModel paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public PaymentRecordModel getPaymentRecord() {
        return paymentRecord;
    }

    @Override
    public String toString() {
        return "GetPaymentRecordResult{" +
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

        public GetPaymentRecordResult build() {
            return new GetPaymentRecordResult(paymentRecord);
        }
    }
}
