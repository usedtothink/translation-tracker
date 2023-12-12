package com.nashss.se.translationtracker.model;

import com.nashss.se.translationtracker.types.ClientType;

import java.util.Objects;

public class TranslationClientModel {
    private String customerId;
    private String translationClientId;
    private String translationClientName;
    private ClientType clientType;

    private TranslationClientModel(String customerId, String translationClientId, String translationClientName,
                                   ClientType clientType) {
        this.customerId = customerId;
        this.translationClientId = translationClientId;
        this.translationClientName = translationClientName;
        this.clientType = clientType;
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

    public ClientType getClientType() {
        return clientType;
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
                Objects.equals(getClientType(), that.getClientType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getTranslationClientId(), getTranslationClientName(),
                getClientType());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String customerId;
        private String translationClientId;
        private String translationClientName;
        private ClientType clientType;

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

        public Builder withClientType(ClientType clientType) {
            this.clientType = clientType;
            return this;
        }

        public TranslationClientModel build() {
            return new TranslationClientModel(customerId, translationClientId, translationClientName,
                    clientType);
        }

    }
}

