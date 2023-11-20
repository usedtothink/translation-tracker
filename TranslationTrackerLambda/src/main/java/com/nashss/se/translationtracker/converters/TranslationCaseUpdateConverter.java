package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TranslationCaseUpdateConverter implements DynamoDBTypeConverter<String, ProgressUpdate> {

    private static final Gson GSON = new Gson();

    @Override
    public String convert(ProgressUpdate object) {
        return GSON.toJson(object);
    }

    @Override
    public ProgressUpdate unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<ProgressUpdate>() { } .getType());
    }





}
