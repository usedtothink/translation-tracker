package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationCaseModel;

public class ArchiveTranslationCaseResult {
    private final TranslationCaseModel translationCase;

    private ArchiveTranslationCaseResult(TranslationCaseModel translationCase) {
        this.translationCase = translationCase;
    }

    public TranslationCaseModel getTranslationCase() {
        return translationCase;
    }

    @Override
    public String toString() {
        return "ArchiveTranslationCaseResult{" +
                "translationCase=" + translationCase +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TranslationCaseModel translationCase;

        public Builder withTranslationCase(TranslationCaseModel translationCase) {
            this.translationCase = translationCase;
            return this;
        }

        public ArchiveTranslationCaseResult build() {
            return new ArchiveTranslationCaseResult(translationCase);
        }
    }
}
