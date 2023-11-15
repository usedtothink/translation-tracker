package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationCaseModel;

public class GetTranslationCaseResult {
    private final TranslationCaseModel translationCase;

    private GetTranslationCaseResult(TranslationCaseModel translationCase) {
        this.translationCase = translationCase;
    }

    public TranslationCaseModel getTranslationCase() {
        return translationCase;
    }

    @Override
    public String toString() {
        return "GetTranslationCaseResult{ " +
                "translationCase=" + translationCase +
                " }";
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

        public GetTranslationCaseResult build() {
            return new GetTranslationCaseResult(translationCase);
        }
    }
}
