package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationClientModel;

public class CreateTranslationClientResult {
    private final TranslationClientModel translationClient;

    private CreateTranslationClientResult(TranslationClientModel translationClient) {
        this.translationClient = translationClient;
    }

    public TranslationClientModel getTranslationClient() {
        return translationClient;
    }

    @Override
    public String toString() {
        return "CreateTranslationClientResult{" +
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

        public CreateTranslationClientResult build() {
            return new CreateTranslationClientResult(translationClient);
        }
    }
}
