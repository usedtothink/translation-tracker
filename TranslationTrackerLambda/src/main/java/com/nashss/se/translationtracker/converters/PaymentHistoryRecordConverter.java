package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.PaymentHistoryRecord;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class PaymentHistoryRecordConverter implements DynamoDBTypeConverter<String, PaymentHistoryRecord> {

    private static final Gson GSON = new Gson();

    @Override
    public String convert(PaymentHistoryRecord object) {
        return GSON.toJson(object);
    }

    @Override
    public PaymentHistoryRecord unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<PaymentHistoryRecord>() { } .getType());
    }
}
