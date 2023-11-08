package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCaseUpdate;
import com.nashss.se.translationtracker.model.TranslationCaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link TranslationCase} into a {@link TranslationCaseModel} representation.
     *
     * @param translationCase the translation case to convert
     * @return the converted translation case
     */
    public TranslationCaseModel toTranslationCaseModel(TranslationCase translationCase) {
        List<TranslationCaseUpdate> progress = null;
        if (translationCase.getProgressLog() != null) {
            progress = new ArrayList<>(translationCase.getProgressLog());
        }

        return TranslationCaseModel.builder()
                .withTranslationCaseId(translationCase.getTranslationCaseId())
                .withTranslationClientId(translationCase.getTranslationClientId())
                .withCaseNickname(translationCase.getCaseNickname())
                .withSourceTextTitle(translationCase.getSourceTextTitle())
                .withSourceTextTitle(translationCase.getSourceTextTitle())
                .withSourceTextAuthor(translationCase.getSourceTextAuthor())
                .withTranslatedTitle(translationCase.getTranslatedTitle())
                .withProjectType(translationCase.getProjectType())
                .withDueDate(translationCase.getDueDate())
                .withStartDate(translationCase.getStartDate())
                .withEndDate(translationCase.getEndDate())
                .withOpenCase(translationCase.getOpenCase())
                .withRushJob(translationCase.getRushJob())
                .withProgressLog(progress)
                .withTotalWorkingHours(translationCase.getTotalWorkingHours())
                .withWordsPerHour(translationCase.getWordsPerHour())
                .withPaymentHistoryRecord(translationCase.getPaymentRecord())
                .build();
    }

    /**
     * Converts a list of TranslationCases to a list of TranslationCaseModels.
     *
     * @param translationCases The TranslationCases to convert to TranslationCaseModels
     * @return The converted list of TranslationCaseModels
     */
    public List<TranslationCaseModel> toTranslationCaseModelList(List<TranslationCase> translationCases) {
        List<TranslationCaseModel> translationCaseModels = new ArrayList<>();

        for (TranslationCase translationCase  : translationCases) {
            translationCaseModels.add(toTranslationCaseModel(translationCase));
        }

        return translationCaseModels;
    }

}
