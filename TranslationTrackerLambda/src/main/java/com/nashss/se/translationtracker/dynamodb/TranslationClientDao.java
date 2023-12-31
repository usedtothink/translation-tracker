package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.TranslationClient;
import com.nashss.se.translationtracker.exceptions.DuplicateTranslationClientException;
import com.nashss.se.translationtracker.exceptions.TranslationClientNotFoundException;
import com.nashss.se.translationtracker.metrics.MetricsConstants;
import com.nashss.se.translationtracker.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Access data for a translation client using {@link TranslationClient} to represent the model in DynamoDB.
 */
@Singleton
public class TranslationClientDao {
    public static final String CUSTOMER_INDEX = "ClientCustomerIdIndex";
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    /**
     * Instantiates a TranslationClientDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the translation client table.
     * @param metricsPublisher the MetricsPublisher.
     */
    @Inject
    public TranslationClientDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Creates a new translation client, checks to make sure the client name is not a duplicate.
     *
     * @param translationClient The translation client to save.
     * @return The TranslationClient object that was saved.
     * @throws DuplicateTranslationClientException when the client name already exists.
     */
    public TranslationClient createTranslationClient(TranslationClient translationClient) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerId", new AttributeValue().withS(translationClient.getCustomerId()));
        DynamoDBQueryExpression<TranslationClient> queryExpression = new DynamoDBQueryExpression<TranslationClient>()
                .withIndexName(CUSTOMER_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerId = :customerId")
                .withExpressionAttributeValues(valueMap);
        List<TranslationClient> translationClientList = dynamoDbMapper.query(TranslationClient.class, queryExpression);
        List<TranslationClient> filteredList = translationClientList.stream()
                .filter(translation -> translation.getTranslationClientName()
                        .equals(translationClient.getTranslationClientName()) &&
                        translation.getClientType() == translationClient.getClientType())
                .collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            metricsPublisher.addCount(MetricsConstants.CREATETRANSLATIONCLIENT_DUPLICATETRANSLATIONCLIENT_COUNT, 1);
            throw new DuplicateTranslationClientException("A translation client with name '" +
                    translationClient.getTranslationClientName() + "' already exists. ");
        }
        metricsPublisher.addCount(MetricsConstants.CREATETRANSLATIONCLIENT_DUPLICATETRANSLATIONCLIENT_COUNT, 0);
        return saveTranslationClient(translationClient);
    }

    /**
     * Returns the {@link TranslationClient} corresponding to the specified id.
     *
     * @param customerId The customer ID.
     * @param translationClientId The TranslationClient ID.
     * @return The stored TranslationClient.
     * @throws TranslationClientNotFoundException if a translation client with the given id does not exist.
     * @throws SecurityException if the given customerId does not match the customerId in the TranslationClient object.
     */
    public TranslationClient getTranslationClient(String customerId, String translationClientId) {
        TranslationClient translationClient = this.dynamoDbMapper.load(TranslationClient.class, translationClientId);

        if (translationClient == null) {
            metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCLIENT_TRANSLATIONCLIENTNOTFOUND_COUNT, 1);
            throw new TranslationClientNotFoundException("Could not find translation client with id" +
                    translationClientId);
        }
        metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCLIENT_TRANSLATIONCLIENTNOTFOUND_COUNT, 0);

        if (!translationClient.getCustomerId().equals(customerId)) {
            metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCLIENT_SECURITY_COUNT, 1);
            throw new SecurityException("CustomerId does not match, users may only retrieve clients they own.");
        }
        metricsPublisher.addCount(MetricsConstants.GETTRANSLATIONCLIENT_SECURITY_COUNT, 0);
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
        List<TranslationClient> translationClientList = dynamoDbMapper
                .query(TranslationClient.class, queryExpression);
        return translationClientList.stream()
                .filter(translationClient -> !translationClient.getTranslationClientId().startsWith("archived - "))
                .collect(Collectors.toList());
    }

    /**
     * Archives the given translation client.
     *
     * @param customerId The id of the customer attempting to archive the client.
     * @param translationClientId The id of the translation client to archive.
     * @return The TranslationClient object that was archived.
     * @throws TranslationClientNotFoundException if no translation client with the given id is found.
     * @throws SecurityException if the customerId does not match the customerId of the given case.
     */
    public TranslationClient archiveTranslationClient(String customerId, String translationClientId) {
        TranslationClient translationClient = this.dynamoDbMapper.load(TranslationClient.class, translationClientId);

        if (translationClient == null) {
            metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCLIENT_TRANSLATIONCLIENTNOTFOUND_COUNT, 1);
            throw new TranslationClientNotFoundException("Could not find translation client with id" +
                    translationClientId);
        }
        metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCLIENT_TRANSLATIONCLIENTNOTFOUND_COUNT, 0);

        if (!translationClient.getCustomerId().equals(customerId)) {
            metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCLIENT_SECURITY_COUNT, 1);
            throw new SecurityException("CustomerId does not match, users may only archive clients they own.");
        }
        metricsPublisher.addCount(MetricsConstants.ARCHIVETRANSLATIONCLIENT_SECURITY_COUNT, 0);

        TranslationClient archivedTranslationClient = new TranslationClient();
        archivedTranslationClient.setCustomerId(translationClient.getCustomerId());
        archivedTranslationClient.setTranslationClientId("archived - " + translationClientId);
        archivedTranslationClient.setTranslationClientName("archived - " +
                translationClient.getTranslationClientName());
        archivedTranslationClient.setClientType(translationClient.getClientType());
        saveTranslationClient(archivedTranslationClient);

        this.dynamoDbMapper.delete(translationClient);
        return translationClient;
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
}
