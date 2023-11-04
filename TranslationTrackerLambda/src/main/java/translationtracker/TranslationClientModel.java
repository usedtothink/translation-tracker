package translationtracker;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranslationClientModel {
    private String translationClientId;
    private String translationClientName;
    private TranslationClientType translationClientType;
    private String mostRecentActivity;

    private TranslationClientModel(String translationClientId,
                                   String translationClientName,
                                   TranslationClientType translationClientType) {
        this.translationClientId = translationClientId;
        this.translationClientName = translationClientName;
        this.translationClientType = translationClientType;
        this.mostRecentActivity = ZonedDateTime.now().toString();
    }

    public String getTranslationClientId() {
        return translationClientId;
    }

    public String getTranslationClientName() {
        return translationClientName;
    }

    public TranslationClientType getTranslationClientType() {
        return translationClientType;
    }

    public String getMostRecentActivity() {
        return mostRecentActivity;
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
                Objects.equals(getMostRecentActivity(), that.getMostRecentActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationClientId(),
                getTranslationClientName(), getTranslationClientType(), getMostRecentActivity());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String translationClientId;
        private String translationClientName;
        private TranslationClientType translationClientType;

        public Builder withTranslationClientId(String translationClientId){
            this.translationClientId = translationClientId;
            return this;
        }

        public Builder withTranslationClientName(String translationClientName) {
            this.translationClientName = translationClientName;
            return this;
        }

        public Builder withTranslationClientType(TranslationClientType translationClientType) {
            this.translationClientType = translationClientType;
            return this;
        }

        public TranslationClientModel build() {
            return new TranslationClientModel(translationClientId, translationClientName, translationClientType);
        }

    }
}

