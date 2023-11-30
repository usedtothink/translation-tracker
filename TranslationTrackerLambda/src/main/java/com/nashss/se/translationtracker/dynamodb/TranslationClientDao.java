package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.exceptions.DuplicateTranslationClientException;
import com.nashss.se.translationtracker.exceptions.TranslationClientNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Access data for a translation client using {@link TranslationClient} to represent the model in DynamoDB.
 */
@Singleton
public class TranslationClientDao {
    public static final String CUSTOMER_INDEX = "CustomerIdIndex";
    private final DynamoDBMapper dynamoDbMapper;
    /**
     * Instantiates a TranslationClientDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the translation client table.
     */
    @Inject
    public TranslationClientDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link TranslationClient} corresponding to the specified id.
     *
     * @param customerId The customer ID.
     * @param translationClientId The TranslationClient ID.
     * @return The stored TranslationClient.
     * @throws TranslationClientNotFoundException if a translation client with the given id does not exist.
     */
    public TranslationClient getTranslationClient(String customerId, String translationClientId) {
        TranslationClient translationClient = this.dynamoDbMapper.load(TranslationClient.class, translationClientId);

        if (translationClient == null) {
            throw new TranslationClientNotFoundException("Could not find translation client with id" +
                    translationClientId);
        }

        if (!translationClient.getCustomerId().equals(customerId)) {
            throw new SecurityException("CustomerId does not match, users may only retrieve cases they own.");
        }
        return translationClient;
    }

    /**
     * Returns a list of {@link TranslationClient} corresponding to the customer id.
     *
     * @param customerId The customer ID.
     * @return A list of stored TranslationClients, or null if none were found.
     */
    public List<TranslationClient> getAllTranslationClients(String customerId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerId", new AttributeValue().withS(customerId));
        DynamoDBQueryExpression<TranslationClient> queryExpression = new DynamoDBQueryExpression<TranslationClient>()
                .withIndexName(CUSTOMER_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerId = :customerId")
                .withExpressionAttributeValues(valueMap);
        List<TranslationClient> translationClientList = dynamoDbMapper.query(TranslationClient.class, queryExpression);
        if (translationClientList.isEmpty()) {
            throw new TranslationClientNotFoundException("No translation clients were associated with id " +
                    customerId);
        }
        return translationClientList;
    }

    /**
     * Saves the given translation client.
     *
     * @param translationClient The translation client to save.
     * @return The TranslationClient object that was saved.
     */
    public TranslationClient saveTranslationClient(TranslationClient translationClient) {
        this.dynamoDbMapper.save(translationClient);
        return translationClient;
    }

    /**
     * Creates a new translation client, checks to make sure the client name is not a duplicate.
     *
     * @param translationClient The translation client to save.
     * @return The TranslationClient object that was saved.
     * @throws DuplicateTranslationClientException when the client name already exists.
     */
    public TranslationClient createTranslationClient(TranslationClient translationClient) {
        TranslationClient existingClient = dynamoDbMapper.load(TranslationClient.class,
                translationClient.getTranslationClientId());
        if (existingClient != null) {
            throw new DuplicateTranslationClientException("A translation client with this name already exists!");
        }
        saveTranslationClient(translationClient);
        return translationClient;
    }

    // Implement archiveTranslationClient method
}
