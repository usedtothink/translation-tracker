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
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the playlists table
     */
    @Inject
    public TranslationCaseDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link TranslationCase} corresponding to the specified id.
     *
     * @param translationCaseId The TranslationCase ID.
     * @return The stored TranslationCase, or null if none was found.
     */
    public TranslationCase getTranslationCase(String translationCaseId) {
        TranslationCase translationCase = this.dynamoDbMapper.load(TranslationCase.class, translationCaseId);

        if (translationCase == null) {
            throw new TranslationCaseNotFoundException("Could not find translation case with id" + translationCaseId);
        }
        return translationCase;
    }

    /**
     * Returns a list of {@link TranslationCase} corresponding to the customer id.
     *
     * @param customerId The customer ID.
     * @return A list of stored TranslationCases, or null if none were found.
     */
    public List<TranslationCase> getAllTranslationCases(String customerId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerId", new AttributeValue().withS(customerId));
        DynamoDBQueryExpression<TranslationCase> queryExpression = new DynamoDBQueryExpression<TranslationCase>()
                .withIndexName(CUSTOMER_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerId = :customerId")
                .withExpressionAttributeValues(valueMap);
        return dynamoDbMapper.query(TranslationCase.class, queryExpression);
    }

    /**
     * Saves the given translation case.
     *
     * @param translationCase The translation case to save.
     * @return The TranslationCase object that was saved.
     */
    public TranslationCase saveTranslationCase(TranslationCase translationCase) {
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
        if (dynamoDbMapper.load(TranslationCase.class, translationCase.getTranslationCaseId()) != null) {
            throw new DuplicateCaseException("The case nickname " + translationCase.getCaseNickname() +
                                                " already exists! Please choose a unique case nickname.");
        }
        this.dynamoDbMapper.save(translationCase);
        return translationCase;
    }
}
