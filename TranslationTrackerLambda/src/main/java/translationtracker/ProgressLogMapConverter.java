package translationtracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ProgressLogMapConverter implements DynamoDBTypeConverter<String, Map<String, Map<String, String>>> {

    private static final Gson GSON = new Gson();

    @Override
    public String convert(Map<String, Map<String, String>> object) {
        return GSON.toJson(object);
    }

    @Override
    public Map<String, Map<String, String>> unconvert(String dynamoDbRepresentation) {
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<Map<String, Map<String, String>>>() { } .getType());
    }
}
