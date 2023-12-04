package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.PaymentRecord;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class PaymentHistoryRecordConverter implements DynamoDBTypeConverter<String, PaymentRecord> {

    private static final Gson GSON = new Gson();

    @Override
    public String convert(PaymentRecord object) {
        return GSON.toJson(object);
    }

    @Override
    public PaymentRecord unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<PaymentRecord>() { } .getType());
    }
}
