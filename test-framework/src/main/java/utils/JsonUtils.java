package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class JsonUtils {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> getJsonObjectAsMap(String jsonObject) {
        try {
            return objectMapper.readValue(jsonObject, HashMap.class);
        } catch (IOException e) {
            log.warn("String input passed as json object seems to be incorrect, please verify.");
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getJsonObjectAsMap(String jsonObject, String path) {
        try {
            return getJsonObjectAsMap(objectMapper.readTree(jsonObject).at(path).toString());
        } catch (IOException e) {
            log.warn("String input passed as json object seems to be incorrect, please verify.");
            e.printStackTrace();
        }
        return null;
    }
}
