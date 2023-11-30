package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.exceptions.DuplicateCaseException;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a translation case using {@link TranslationCase} to represent the model in DynamoDB.
 */
@Singleton
public class TranslationCaseDao {
    public static final String CUSTOMER_INDEX = "CustomerIdIndex";
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a TranslationCaseDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the translation case table.
     */
    @Inject
    public TranslationCaseDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
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
            throw new TranslationCaseNotFoundException("Could not find translation case with id " + translationCaseId);
        }

        if (!translationCase.getCustomerId().equals(customerId)) {
            throw new SecurityException("CustomerId does not match, users may only retrieve cases they own.");
        }

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
        if (translationCaseList.isEmpty()) {
            throw new TranslationCaseNotFoundException("No translation cases were associated with id " + customerId);
        }
        return translationCaseList;
    }

    /**
     * Saves the given translation case.
     *
     * @param translationCase The translation case to save.
     * @return The TranslationCase object that was saved.
     * @throws SecurityException if the customerId on the existing translation case does not match the update.
     */
    public TranslationCase saveTranslationCase(TranslationCase translationCase) {
        TranslationCase existingCase = dynamoDbMapper.load(TranslationCase.class,
                translationCase.getTranslationCaseId());
        if (existingCase != null && !existingCase.getCustomerId().equals(translationCase.getCustomerId())) {
            throw new SecurityException("CustomerId does not match, users may only update cases they own.");
        }
        this.dynamoDbMapper.save(translationCase);
        return translationCase;
    }

    /**
     * Creates a new translation case, checks to make sure the case nickname is not a duplicate.
     *
     * @param translationCase The translation case to save.
     * @return The TranslationCase object that was saved.
     * @throws DuplicateCaseException when the case nickname already exists.
     */
    public TranslationCase createTranslationCase(TranslationCase translationCase) {
        // Update this to use a GSI to check for an existing translation case nickname
        saveTranslationCase(translationCase);
        return translationCase;
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
            throw new TranslationCaseNotFoundException("Could not find translation case with id" + translationCaseId);
        }

        if (!translationCase.getCustomerId().equals(customerId)) {
            throw new SecurityException("CustomerId does not match, users may only archive cases they own.");
        }

        translationCase.setTranslationCaseId("archived" + translationCaseId);
        saveTranslationCase(translationCase);

        translationCase.setTranslationCaseId(translationCaseId);
        this.dynamoDbMapper.delete(translationCase);
        return translationCase;
    }
}
