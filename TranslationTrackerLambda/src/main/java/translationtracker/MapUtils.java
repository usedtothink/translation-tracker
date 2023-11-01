package translationtracker;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    /**
     * Defensively copies a nested map object.
     * @param original the nested map to be copied
     * @return the new defensively copied map
     */
    public static Map<String, Map<String, String>> defensiveCopyNestedMaps(Map<String, Map<String, String>> original) {
        Map<String, Map<String, String>> outputMap = new HashMap<>();
        for (String key : original.keySet()) {
            for (Map.Entry<String, String> internalEntry : original.get(key).entrySet()) {
                Map<String, String> internalOutputMap = new HashMap<>();
                internalOutputMap.put(internalEntry.getKey(), internalEntry.getValue());
                outputMap.put(key, internalOutputMap);
            }
        }
        return outputMap;
    }
}
