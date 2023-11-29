package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationClientModel;

public class GetTranslationClientResult {
    private final TranslationClientModel translationClient;

    private GetTranslationClientResult(TranslationClientModel translationClient) {
        this.translationClient = translationClient;
    }

    public TranslationClientModel getTranslationClient() {
        return this.translationClient;
    }

    @Override
    public String toString() {
        return "GetTranslationClientResult{" +
                "translationClient=" + translationClient +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TranslationClientModel translationClient;

        public Builder withTranslationClient(TranslationClientModel translationClient) {
            this.translationClient = translationClient;
            return this;
        }

        public GetTranslationClientResult build() {
            return new GetTranslationClientResult(translationClient);
        }
    }

}
