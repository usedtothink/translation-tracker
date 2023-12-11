package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.model.PaymentRecordModel;
import com.nashss.se.translationtracker.model.TranslationCaseModel;
import com.nashss.se.translationtracker.model.TranslationClientModel;

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
        return TranslationCaseModel.builder()
                .withCustomerId(translationCase.getCustomerId())
                .withTranslationCaseId(translationCase.getTranslationCaseId())
                .withCaseNickname(translationCase.getCaseNickname())
                .withProjectType(translationCase.getProjectType())
                .withTranslationClientId(translationCase.getTranslationClientId())
                .withSourceTextTitle(translationCase.getSourceTextTitle())
                .withSourceTextAuthor(translationCase.getSourceTextAuthor())
                .withTranslatedTitle(translationCase.getTranslatedTitle())
                .withDueDate(translationCase.getDueDate())
                .withStartDate(translationCase.getStartDate())
                .withEndDate(translationCase.getEndDate())
                .withOpenCase(translationCase.getOpenCase())
                .withRushJob(translationCase.getRushJob())
                .withProgressLog(translationCase.getProgressLog())
                .withTotalWorkingHours(translationCase.getTotalWorkingHours())
                .withWordsPerHour(translationCase.getWordsPerHour())
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

        for (TranslationCase translationCase : translationCases) {
            translationCaseModels.add(toTranslationCaseModel(translationCase));
        }

        return translationCaseModels;
    }

    /**
     * Converts a provided {@link TranslationClient} into a {@link TranslationClientModel} representation.
     *
     * @param translationClient the translation client to convert
     * @return the converted translation client
     */
    public TranslationClientModel toTranslationClientModel(TranslationClient translationClient) {

        return TranslationClientModel.builder()
                .withCustomerId(translationClient.getCustomerId())
                .withTranslationClientId(translationClient.getTranslationClientId())
                .withTranslationClientType(translationClient.getTranslationClientType())
                .withTranslationClientName(translationClient.getTranslationClientName())
                .build();
    }

    /**
     * Converts a list of TranslationClients to a list of TranslationClientModels.
     *
     * @param translationClients The TranslationClients to convert to TranslationClientModels
     * @return The converted list of TranslationClientModels
     */
    public List<TranslationClientModel> toTranslationClientModelList(List<TranslationClient> translationClients) {
        List<TranslationClientModel> translationClientModels = new ArrayList<>();

        for (TranslationClient translationClient : translationClients) {
            translationClientModels.add(toTranslationClientModel(translationClient));
        }

        return translationClientModels;
    }

    /**
     * Converts a provided {@link PaymentRecord} into a {@link PaymentRecordModel} representation.
     *
     * @param paymentRecord the payment record to convert
     * @return the converted payment record
     */
    public PaymentRecordModel toPaymentRecordModel(PaymentRecord paymentRecord) {

        return PaymentRecordModel.builder()
                .withCustomerId(paymentRecord.getCustomerId())
                .withTranslationCaseId(paymentRecord.getTranslationCaseId())
                .withTranslationClientId(paymentRecord.getTranslationClientId())
                .withCasePaid(paymentRecord.getCasePaid())
                .withPaymentDate(paymentRecord.getPaymentDate())
                .withOnTime(paymentRecord.getOnTime())
                .withGrossPayment(paymentRecord.getGrossPayment())
                .withTaxRate(paymentRecord.getTaxRate())
                .withPayRate(paymentRecord.getPayRate())
                .withPayRateUnit(paymentRecord.getPayRateUnit())
                .withWordCount(paymentRecord.getWordCount())
                .withWordCountUnit(paymentRecord.getWordCountUnit())
                .build();
    }
}
