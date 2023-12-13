package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;
import com.nashss.se.translationtracker.exceptions.DuplicatePaymentRecordException;
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
 * Access data for a translation client using {@link PaymentRecord} to represent the model in DynamoDB.
 */
@Singleton
public class PaymentRecordDao {
    public static final String CUSTOMER_INDEX = "PaymentCustomerIdIndex";
    public static final String CLIENT_INDEX = "PaymentTranslationClientIdIndex";
    private final DynamoDBMapper dynamoDbMapper;
    /**
     * Instantiates a PaymentRecordDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the payment record table.
     */
    @Inject
    public PaymentRecordDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Creates a new payment record client, checks to make sure a payment record with the same
     * translation case id does not already exit.
     *
     * @param translationCaseId The id of the translation case to make a new blank payment record for.
     * @param customerId The customer id to check that the user owns the payment record.
     * @return The PaymentRecord object that was saved.
     * @throws DuplicatePaymentRecordException when a payment record with the same translation case id already exists.
     */
    public PaymentRecord createPaymentRecord(String customerId, String translationCaseId) {
        PaymentRecord existingPaymentRecord = dynamoDbMapper.load(PaymentRecord.class, translationCaseId);
        if (existingPaymentRecord != null) {
            throw new DuplicatePaymentRecordException("A payment record for translation case with id '" +
                    translationCaseId + "' already exists.");
        }
        PaymentRecord newPaymentRecord = new PaymentRecord();
        newPaymentRecord.setCustomerId(customerId);
        newPaymentRecord.setTranslationCaseId(translationCaseId);

        return savePaymentRecord(newPaymentRecord);
    }

    /**
     * Returns the {@link PaymentRecord} corresponding to the specified translation case ID.
     *
     * @param customerId The customer ID.
     * @param translationCaseId The TranslationCase ID.
     * @return The stored PaymentRecord.
     * @throws TranslationCaseNotFoundException if a payment record with the given TranslationCase ID does not exist.
     * @throws SecurityException if the given customerId does not match the customerId in the PaymentRecord object.
     */
    public PaymentRecord getPaymentRecord(String customerId, String translationCaseId) {
        PaymentRecord paymentRecord = this.dynamoDbMapper.load(PaymentRecord.class, translationCaseId);

        if (paymentRecord == null) {
            throw new TranslationCaseNotFoundException("No payment record exists for translation case with ID '" +
                    translationCaseId + "'.");
        }

        if (!paymentRecord.getCustomerId().equals(customerId)) {
            throw new SecurityException("CustomerId does not match, users may only retrieve payment records they own.");
        }

        return paymentRecord;
    }

    /**
     * Returns a list of {@link PaymentRecord} corresponding to the customer id.
     *
     * @param customerId The customer ID.
     * @return A list of stored PaymentRecords, or null if none were found.
     */
    public List<PaymentRecord> getAllPaymentRecordsForCustomerId(String customerId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerId", new AttributeValue().withS(customerId));
        DynamoDBQueryExpression<PaymentRecord> queryExpression = new DynamoDBQueryExpression<PaymentRecord>()
                .withIndexName(CUSTOMER_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerId = :customerId")
                .withExpressionAttributeValues(valueMap);
        List<PaymentRecord> paymentRecordList = dynamoDbMapper.query(PaymentRecord.class, queryExpression);
        if (paymentRecordList.isEmpty()) {
            throw new TranslationCaseNotFoundException("No payment records are associated with id " +
                    customerId);
        }
        return paymentRecordList;
    }

    /**
     * Returns a list of {@link PaymentRecord} corresponding to the translation client id.
     *
     * @param translationClientId The translation client ID.
     * @return A list of stored PaymentRecords, or empty if none were found.
     */
    public List<PaymentRecord> getAllPaymentRecordsForTranslationClientId(String translationClientId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":translationClientId", new AttributeValue().withS(translationClientId));
        DynamoDBQueryExpression<PaymentRecord> queryExpression = new DynamoDBQueryExpression<PaymentRecord>()
                .withIndexName(CLIENT_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("translationClientId = :translationClientId")
                .withExpressionAttributeValues(valueMap);

        return dynamoDbMapper.query(PaymentRecord.class, queryExpression);
    }

    /**
     * Archives the given payment record.
     *
     * @param customerId The id of the customer attempting to archive the case.
     * @param translationCaseId The id of the translation case to archive.
     * @return The TranslationCase object that was archived.
     * @throws TranslationCaseNotFoundException if no translation case with the given id is found.
     * @throws SecurityException if the customerId does not match the customerId of the given case.
     */
    public Boolean archivePaymentRecord(String customerId, String translationCaseId) {
        PaymentRecord paymentRecord = this.dynamoDbMapper.load(PaymentRecord.class, translationCaseId);

        if (paymentRecord == null) {
            throw new TranslationCaseNotFoundException("Could not find payment record for translation case with id" +
                    translationCaseId);
        }

        if (!paymentRecord.getCustomerId().equals(customerId)) {
            throw new SecurityException("CustomerId does not match, users may only archive payment records they own.");
        }

        this.dynamoDbMapper.delete(paymentRecord);

        paymentRecord.setTranslationCaseId("archived - " + translationCaseId);
        savePaymentRecord(paymentRecord);

        return true;
    }

    /**
     * Saves the given payment record.
     *
     * @param paymentRecord The payment record to save.
     * @return The TranslationClient object that was saved.
     */
    public PaymentRecord savePaymentRecord(PaymentRecord paymentRecord) {
        this.dynamoDbMapper.save(paymentRecord);
        return paymentRecord;
    }
}
