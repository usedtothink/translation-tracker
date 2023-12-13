package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.PaymentRecordModel;

import java.util.List;

public class GetAllPaymentRecordsForTranslationClientResult {
    private final List<PaymentRecordModel> paymentRecordList;

    private GetAllPaymentRecordsForTranslationClientResult(List<PaymentRecordModel> paymentRecordList) {
        this.paymentRecordList = paymentRecordList;
    }

    public List<PaymentRecordModel> getPaymentRecordList() {
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
        List<PaymentRecordModel> paymentRecordList;

        public Builder withPaymentRecordList(List<PaymentRecordModel> paymentRecordList) {
            this.paymentRecordList = paymentRecordList;
            return this;
        }

        public GetAllPaymentRecordsForTranslationClientResult build() {
            return new GetAllPaymentRecordsForTranslationClientResult(paymentRecordList);
        }
    }
}
