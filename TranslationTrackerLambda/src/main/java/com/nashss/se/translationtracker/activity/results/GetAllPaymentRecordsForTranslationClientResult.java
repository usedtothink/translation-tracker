package com.nashss.se.translationtracker.activity.results;


import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;

import java.util.List;

public class GetAllPaymentRecordsForTranslationClientResult {
    private final List<PaymentRecord> paymentRecordList;

    private GetAllPaymentRecordsForTranslationClientResult(List<PaymentRecord> paymentRecordList) {
        this.paymentRecordList = paymentRecordList;
    }

    public List<PaymentRecord> getPaymentRecordList() {
        return paymentRecordList;
    }

    @Override
    public String toString() {
        return "GetAllPaymentRecordsForClientResult{" +
                "paymentRecordList=" + paymentRecordList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        List<PaymentRecord> paymentRecordList;

        public Builder withPaymentRecordList(List<PaymentRecord> paymentRecordList) {
            this.paymentRecordList = paymentRecordList;
            return this;
        }

        public GetAllPaymentRecordsForTranslationClientResult build() {
            return new GetAllPaymentRecordsForTranslationClientResult(paymentRecordList);
        }
    }
}
