package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationCaseModel;

public class AddProgressUpdateResult {
    private final TranslationCaseModel translationCase;

    private AddProgressUpdateResult(TranslationCaseModel translationCase) {
        this.translationCase = translationCase;
    }

    public TranslationCaseModel getTranslationCase() {
        return translationCase;
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

        public AddProgressUpdateResult build() {
            return new AddProgressUpdateResult(translationCase);
        }
    }
}
