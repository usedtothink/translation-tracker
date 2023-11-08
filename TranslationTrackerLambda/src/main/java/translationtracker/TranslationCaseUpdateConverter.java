package translationtracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class TranslationCaseUpdateConverter implements DynamoDBTypeConverter<String, TranslationCaseUpdate> {

    private static final Gson GSON = new Gson();

    @Override
    public String convert(TranslationCaseUpdate object) {
        return GSON.toJson(object);
    }

    @Override
    public TranslationCaseUpdate unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<TranslationCaseUpdate>() { } .getType());
    }





}
