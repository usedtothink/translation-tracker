package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationCaseModel;

import java.util.List;

public class GetAllTranslationCasesResult {
    private final List<TranslationCaseModel> translationCaseList;

    private GetAllTranslationCasesResult(List<TranslationCaseModel> translationCaseList) {
        this.translationCaseList = translationCaseList;
    }

    public List<TranslationCaseModel> getTranslationCaseList() {
        return translationCaseList;
    }

    @Override
    public String toString() {
        return "GetAllTranslationCasesResult{" +
                "translationCaseList=" + translationCaseList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TranslationCaseModel> translationCaseList;

        public Builder withTranslationCaseList(List<TranslationCaseModel> translationCaseList) {
            this.translationCaseList = translationCaseList;
            return this;
        }

        public GetAllTranslationCasesResult build() {
            return new GetAllTranslationCasesResult(translationCaseList);
        }
    }
}
