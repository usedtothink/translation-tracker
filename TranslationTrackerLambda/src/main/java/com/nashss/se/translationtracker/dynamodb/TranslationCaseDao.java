package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;
import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.exceptions.DuplicateProgressUpdateException;
import com.nashss.se.translationtracker.exceptions.DuplicateTranslationCaseException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;
import com.nashss.se.translationtracker.metrics.MetricsConstants;
import com.nashss.se.translationtracker.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a translation case using {@link TranslationCase} to represent the model in DynamoDB.
 */
@Singleton
public class TranslationCaseDao {
    public static final String CUSTOMER_INDEX = "CaseCustomerIdIndex";
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a TranslationCaseDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the translation case table.
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */
    @Inject
    public TranslationCaseDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Creates a new translation case, checks to make sure the case nickname is not a duplicate.
     *
     * @param translationCase The translation case to save.
     * @return The TranslationCase object that was saved.
     * @throws DuplicateTranslationCaseException when the case nickname already exists.
     */
    public TranslationCase createTranslationCase(TranslationCase translationCase) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerId", new AttributeValue().withS(translationCase.getCustomerId()));
        DynamoDBQueryExpression<TranslationCase> queryExpression = new DynamoDBQueryExpression<TranslationCase>()
                .withIndexName(CUSTOMER_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerId = :customerId")
                .withExpressionAttributeValues(valueMap);
        List<TranslationCase> translationCaseList = dynamoDbMapper.query(TranslationCase.class, queryExpression);
        List<TranslationCase> filteredList = translationCaseList.stream()
                .filter(translation -> translation.getCaseNickname()
                        .equals(translationCase.getCaseNickname()))
                .collect(Collectors.toList());
        if (!filteredList.isEmpty()) {
            metricsPublisher.addCount(MetricsConstants.CREATETRANSLATIONCASE_DUPLICATETRANSLATIONCASE_COUNT, 1);
            throw new DuplicateTranslationCaseException("A translation case with nickname '" +
                    translationCase.getCaseNickname() +
                    "' already exists.");
        }
        metricsPublisher.addCount(MetricsConstants.CREATETRANSLATIONCASE_DUPLICATETRANSLATIONCASE_COUNT, 0);
        return saveTranslationCase(translationCase);
    }

    /**
     * Returns the {@link TranslationCase} corresponding to the specified id.
     *
     * @param customerId The customer ID.
     * @param translationCaseId The TranslationCase ID.
     * @return The stored TranslationCase.
     * @throws TranslationCaseNotFoundException if a translation case with the given id does not exist.
     */
    public TranslationCase getTranslationCase(String customerId, String translationCaseId) {
        TranslationCase translationCase = this.dynamoDbMapper.load(TranslationCase.class, translationCaseId);

        if (translationCase == null) {
            metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCASE_TRANSLATIONCASENOTFOUND_COUNT, 1);
            throw new TranslationCaseNotFoundException("Could not find translation case with id " + translationCaseId);
        }
        metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCASE_TRANSLATIONCASENOTFOUND_COUNT, 0);
        if (!translationCase.getCustomerId().equals(customerId)) {
            metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCASE_SECURITY_COUNT, 1);
            throw new SecurityException("CustomerId does not match, users may only access cases they own.");
        }
        metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCASE_SECURITY_COUNT, 0);

        return translationCase;
    }

    /**
     * Returns a list of {@link TranslationCase} corresponding to the customer id.
     *
     * @param customerId The customer ID.
     * @return A list of stored TranslationCases, or null if none were found.
     * @throws TranslationCaseNotFoundException if no translation cases are associated with the customer id.
     */
    public List<TranslationCase> getAllTranslationCases(String customerId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerId", new AttributeValue().withS(customerId));
        DynamoDBQueryExpression<TranslationCase> queryExpression = new DynamoDBQueryExpression<TranslationCase>()
                .withIndexName(CUSTOMER_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerId = :customerId")
                .withExpressionAttributeValues(valueMap);
        List<TranslationCase> translationCaseList = dynamoDbMapper.query(TranslationCase.class, queryExpression);
        return translationCaseList.stream()
                .filter(translationCase -> !translationCase.getTranslationCaseId().startsWith("archived - "))
                .collect(Collectors.toList());
    }

    /**
     * Archives the given translation case.
     *
     * @param customerId The id of the customer attempting to archive the case.
     * @param translationCaseId The id of the translation case to archive.
     * @return The TranslationCase object that was archived.
     * @throws TranslationCaseNotFoundException if no translation case with the given id is found.
     * @throws SecurityException if the customerId does not match the customerId of the given case.
     */
    public TranslationCase archiveTranslationCase(String customerId, String translationCaseId) {
        TranslationCase translationCase = this.dynamoDbMapper.load(TranslationCase.class, translationCaseId);

        if (translationCase == null) {
            metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCASE_TRANSLATIONCASENOTFOUND_COUNT, 1);
            throw new TranslationCaseNotFoundException("Could not find translation case with id" + translationCaseId);
        }
        metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCASE_TRANSLATIONCASENOTFOUND_COUNT, 0);

        if (!translationCase.getCustomerId().equals(customerId)) {
            metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCASE_SECURITY_COUNT, 1);
            throw new SecurityException("CustomerId does not match, users may only archive cases they own.");
        }
        metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCASE_SECURITY_COUNT, 0);

        TranslationCase archivedTranslationCase = new TranslationCase();
        archivedTranslationCase.setCustomerId(translationCase.getCustomerId());
        archivedTranslationCase.setTranslationCaseId("archived - " + translationCase.getTranslationCaseId());
        archivedTranslationCase.setCaseNickname("archived - " + translationCase.getCaseNickname());
        archivedTranslationCase.setProjectType(translationCase.getProjectType());
        archivedTranslationCase.setTranslationClientId(translationCase.getTranslationClientId());
        archivedTranslationCase.setSourceTextTitle(translationCase.getSourceTextTitle());
        archivedTranslationCase.setSourceTextAuthor(translationCase.getSourceTextAuthor());
        archivedTranslationCase.setTranslatedTitle(translationCase.getTranslatedTitle());
        archivedTranslationCase.setDueDate(translationCase.getDueDate());
        archivedTranslationCase.setStartDate(translationCase.getStartDate());
        archivedTranslationCase.setEndDate(translationCase.getEndDate());
        archivedTranslationCase.setOpenCase(translationCase.getOpenCase());
        archivedTranslationCase.setRushJob(translationCase.getRushJob());
        archivedTranslationCase.setProgressLog(translationCase.getProgressLog());
        archivedTranslationCase.setTotalWorkingHours(translationCase.getTotalWorkingHours());
        archivedTranslationCase.setWordsPerHour(translationCase.getWordsPerHour());

        saveTranslationCase(archivedTranslationCase);

        this.dynamoDbMapper.delete(translationCase);
        return translationCase;
    }

    /**
     * Adds the given progress update.
     *
     * @param progressUpdate The progress update to be added to the progress log.
     * @return The updated TranslationCase object.
     * @throws TranslationCaseNotFoundException if no translation case with the given id is found.
     * @throws SecurityException if the customerId does not match the customerId of the given case.
     * @throws DuplicateProgressUpdateException if the translation case already contains an identical progress update.
     */
    public TranslationCase addProgressUpdate(ProgressUpdate progressUpdate) {
        TranslationCase translationCase = getTranslationCase(progressUpdate.getCustomerId(),
                progressUpdate.getTranslationCaseId());

        if (translationCase == null) {
            metricsPublisher.addCount(MetricsConstants.ADDPROGRESSUPDATE_TRANSLATIONCASENOTFOUND_COUNT, 1);
            throw new TranslationCaseNotFoundException("Could not find translation case with id" +
                    progressUpdate.getTranslationCaseId());
        }
        metricsPublisher.addCount(MetricsConstants.ADDPROGRESSUPDATE_TRANSLATIONCASENOTFOUND_COUNT, 0);

        if (!translationCase.getCustomerId().equals(progressUpdate.getCustomerId())) {
            metricsPublisher.addCount(MetricsConstants.ADDPROGRESSUPDATE_SECURITY_COUNT, 1);
            throw new SecurityException("CustomerId does not match, " +
                    "users may only progress updates to cases they own.");
        }
        metricsPublisher.addCount(MetricsConstants.ADDPROGRESSUPDATE_SECURITY_COUNT, 0);

        List<ProgressUpdate> progressLog = translationCase.getProgressLog();
        if (progressLog != null && progressLog.contains(progressUpdate)) {
            metricsPublisher.addCount(MetricsConstants.ADDPROGRESSUPDATE_DUPLICATEPROGRESSUPDATE_COUNT, 1);
            throw new DuplicateProgressUpdateException("This progress update is already in the progress log " +
                    progressUpdate);
        }
        metricsPublisher.addCount(MetricsConstants.ADDPROGRESSUPDATE_DUPLICATEPROGRESSUPDATE_COUNT, 0);

        if (progressLog == null) {
            progressLog = new ArrayList<>();
        }

        progressLog.add(progressUpdate);
        translationCase.setProgressLog(progressLog);
        dynamoDbMapper.save(translationCase);

        return translationCase;
    }

    /**
     * Saves the given translation case.
     *
     * @param translationCase The translation case to save.
     * @return The TranslationCase object that was saved.
     * @throws SecurityException if the customerId on the existing translation case does not match the update.
     */
    public TranslationCase saveTranslationCase(TranslationCase translationCase) {
        this.dynamoDbMapper.save(translationCase);
        return translationCase;
    }
}
