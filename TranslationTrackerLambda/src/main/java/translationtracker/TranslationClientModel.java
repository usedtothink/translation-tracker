package translationtracker;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranslationClientModel {

    private String translationClientId;
    private String translationClientName;
    private String translationClientType;
    private String mostRecentActivity;
    private List<String> translationCaseHistory;
    private List<PaymentHistoryRecord> paymentHistory;

    private TranslationClientModel(String translationClientId,
                                   String translationClientName,
                                   String translationClientType,
                                   List<String> translationCaseHistory,
                                   List<PaymentHistoryRecord> paymentHistory) {
        this.translationClientId = translationClientId;
        this.translationClientName = translationClientName;
        this.translationClientType = translationClientType;
        this.mostRecentActivity = ZonedDateTime.now().toString();
        this.translationCaseHistory = new ArrayList<>(translationCaseHistory);
        this.paymentHistory = PaymentHistoryRecord.defensiveCopyPaymentHistoryList(paymentHistory);
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public String getTranslationClientName() {
        return translationClientName;
    }

    public String getTranslationClientType() {
        return translationClientType;
    }

    public String getMostRecentActivity() {
        return mostRecentActivity;
    }

    public List<String> getTranslationCaseHistory() {
        return new ArrayList<>(translationCaseHistory);
    }

    public List<PaymentHistoryRecord> getPaymentHistory() {
        return PaymentHistoryRecord.defensiveCopyPaymentHistoryList(paymentHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranslationClientModel that = (TranslationClientModel) o;
        return Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getTranslationClientName(), that.getTranslationClientName()) &&
                Objects.equals(getTranslationClientType(), that.getTranslationClientType()) &&
                Objects.equals(getMostRecentActivity(), that.getMostRecentActivity()) &&
                Objects.equals(getTranslationCaseHistory(), that.getTranslationCaseHistory()) &&
                Objects.equals(getPaymentHistory(), that.getPaymentHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationClientId(),
                getTranslationClientName(), getTranslationClientType(), getMostRecentActivity(),
                getTranslationCaseHistory(), getPaymentHistory());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String translationClientId;
        private String translationClientName;
        private String translationClientType;
        private List<String> translationCaseHistory;
        private List<PaymentHistoryRecord> paymentHistory;

        public Builder withTranslationClientId(String translationClientId){
            this.translationClientId = translationClientId;
            return this;
        }

        public Builder withTranslationClientName(String translationClientName) {
            this.translationClientName = translationClientName;
            return this;
        }

        public Builder withTranslationClientType(String translationClientType) {
            this.translationClientType = translationClientType;
            return this;
        }

        public Builder withTranslationCaseHistory(List<String> translationCaseHistory) {
            this.translationCaseHistory = translationCaseHistory;
            return this;
        }

        public Builder withPaymentHistory(List<PaymentHistoryRecord> paymentHistory) {
            this.paymentHistory = paymentHistory;
            return this;
        }

        public TranslationClientModel build() {
            return new TranslationClientModel(translationClientId, translationClientName, translationClientType,
                    translationCaseHistory, paymentHistory);
        }

    }
}

