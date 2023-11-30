package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationClientModel;

public class ArchiveTranslationClientResult {
    private final TranslationClientModel translationClient;

    private ArchiveTranslationClientResult(TranslationClientModel translationClient) {
        this.translationClient = translationClient;
    }

    public TranslationClientModel getTranslationClient() {
        return translationClient;
    }

    @Override
    public String toString() {
        return "ArchiveTranslationClientResult{" +
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

        public ArchiveTranslationClientResult build() {
            return new ArchiveTranslationClientResult(translationClient);
        }
    }
}
