package com.nashss.se.translationtracker.model;

import com.nashss.se.translationtracker.types.TranslationClientType;

import java.time.ZonedDateTime;
import java.util.Objects;

public class TranslationClientModel {
    private String customerId;
    private String translationClientId;
    private String translationClientName;
    private TranslationClientType translationClientType;
    private String mostRecentActivity;

    private TranslationClientModel(String customerId, String translationClientId, String translationClientName,
                                   TranslationClientType translationClientType) {
        this.customerId = customerId;
        this.translationClientId = translationClientId;
        this.translationClientName = translationClientName;
        this.translationClientType = translationClientType;
        this.mostRecentActivity = ZonedDateTime.now().toString();
    }

    public String getCustomerId() {
        return customerId;
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
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getTranslationClientName(), that.getTranslationClientName()) &&
                Objects.equals(getTranslationClientType(), that.getTranslationClientType()) &&
                Objects.equals(getMostRecentActivity(), that.getMostRecentActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getTranslationClientId(), getTranslationClientName(),
                getTranslationClientType(), getMostRecentActivity());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;
        private String translationClientId;
        private String translationClientName;
        private TranslationClientType translationClientType;

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

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
            return new TranslationClientModel(customerId, translationClientId, translationClientName,
                    translationClientType);
        }

    }
}

