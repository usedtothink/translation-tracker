package com.nashss.se.translationtracker.dynamodb.models;

import com.nashss.se.translationtracker.types.ClientType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.Objects;

@DynamoDBTable(tableName = "translation_clients")
public class TranslationClient {
    private String customerId;
    private String translationClientId;
    private String translationClientName;
    private ClientType translationClientType;

    @DynamoDBAttribute(attributeName =  "customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBHashKey(attributeName = "translationClientId")
    public String getTranslationClientId() {
        return translationClientId;
    }

    public void setTranslationClientId(String translationClientId) {
        this.translationClientId = translationClientId;
    }

    @DynamoDBAttribute(attributeName = "translationClientName")
    public String getTranslationClientName() {
        return translationClientName;
    }

    public void setTranslationClientName(String translationClientName) {
        this.translationClientName = translationClientName;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "translationClientType")
    public ClientType getTranslationClientType() {
        return translationClientType;
    }

    public void setTranslationClientType(ClientType translationClientType) {
        this.translationClientType = translationClientType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranslationClient that = (TranslationClient) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getTranslationClientName(), that.getTranslationClientName()) &&
                getTranslationClientType() == that.getTranslationClientType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getTranslationClientId(), getTranslationClientName(),
                getTranslationClientType());
    }
}
