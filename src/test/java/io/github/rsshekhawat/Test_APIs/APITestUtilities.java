package io.github.rsshekhawat.Test_APIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import java.util.List;
import java.util.Map;

public class APITestUtilities {

    public String serializeObject(Object obj)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getJsonString(String json, String expression){
        return JsonPath.parse(json).read(expression);
    }
    public Map<String, Object> getJsonObject(String json, String expression){
        return JsonPath.parse(json).read(expression);
    }
    public List<Map<String, Object>> getJsonArray(String json, String expression){
        return JsonPath.parse(json).read(expression);
    }

}
