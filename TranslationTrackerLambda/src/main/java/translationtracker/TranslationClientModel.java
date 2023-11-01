package translationtracker;

import java.util.Map;
import java.util.Objects;

public class TranslationClientModel {

    private String translationClientId;
    private String translationClientName;
    private String translationClientType;
    private Map<String, Map<String, String>> translationCaseHistory;
    private Map<String, Map<String, String>> paymentHistory;

    private TranslationClientModel(String translationClientId,
                                   String translationClientName,
                                   String translationClientType,
                                   Map<String, Map<String, String>> translationCaseHistory,
                                   Map<String, Map<String, String>> paymentHistory) {
        this.translationClientId = translationClientId;
        this.translationClientName = translationClientName;
        this.translationClientType = translationClientType;
        this.translationCaseHistory = MapUtils.defensiveCopyNestedMaps(translationCaseHistory);
        this.paymentHistory = MapUtils.defensiveCopyNestedMaps(paymentHistory);
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

    public Map<String, Map<String, String>> getTranslationCaseHistory() {
        return MapUtils.defensiveCopyNestedMaps(translationCaseHistory);
    }

    public Map<String, Map<String, String>> getPaymentHistory() {
        return MapUtils.defensiveCopyNestedMaps(paymentHistory);
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
                Objects.equals(getTranslationCaseHistory(), that.getTranslationCaseHistory()) &&
                Objects.equals(getPaymentHistory(), that.getPaymentHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationClientId(),
                getTranslationClientName(), getTranslationClientType(), getTranslationCaseHistory(),
                getPaymentHistory());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String translationClientId;
        private String translationClientName;
        private String translationClientType;
        private Map<String, Map<String, String>> translationCaseHistory;
        private Map<String, Map<String, String>> paymentHistory;

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

        public Builder withTranslationCaseHistory(Map<String, Map<String, String>> translationCaseHistory) {
            this.translationCaseHistory = translationCaseHistory;
            return this;
        }

        public Builder withPaymentHistory(Map<String, Map<String, String>> paymentHistory) {
            this.paymentHistory = paymentHistory;
            return this;
        }

        public TranslationClientModel build() {
            return new TranslationClientModel(translationClientId, translationClientName, translationClientType,
                    translationCaseHistory, paymentHistory);
        }

    }
}

