package com.nashss.se.translationtracker.activity.results;

import com.nashss.se.translationtracker.model.TranslationClientModel;

import java.util.List;

public class GetAllTranslationClientsResult {
    private final List<TranslationClientModel> translationClientList;

    private GetAllTranslationClientsResult(List<TranslationClientModel> translationClientList) {
        this.translationClientList = translationClientList;
    }

    public List<TranslationClientModel> getTranslationClientList() {
        return translationClientList;
    }

    @Override
    public String toString() {
        return "GetAllTranslationClientsResult{" +
                "translationClientList=" + translationClientList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TranslationClientModel> translationClientList;

        public Builder withTranslationClientList(List<TranslationClientModel> translationClientList) {
            this.translationClientList = translationClientList;
            return this;
        }

        public GetAllTranslationClientsResult build() {
            return new GetAllTranslationClientsResult(translationClientList);
        }
    }
}
