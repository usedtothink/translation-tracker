package com.nashss.se.translationtracker.converters;

import com.nashss.se.translationtracker.dynamodb.models.ProgressUpdate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ProgressLogConverter implements DynamoDBTypeConverter<String, List<ProgressUpdate>> {

    private static final Gson GSON = new Gson();

    @Override
    public String convert(List<ProgressUpdate> object) {
        return GSON.toJson(object);
    }

    @Override
    public List<ProgressUpdate> unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<List<ProgressUpdate>>() { } .getType());
    }





}
