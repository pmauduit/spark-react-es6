package com.patrickgrimard;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrickgrimard.HelloWorld.PizzaOrder;

/**
 * Created by Patrick on 2015-06-23.
 */
public class JsonTransformer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object model) throws JsonProcessingException {
        return mapper.writeValueAsString(model);
    }
    
    public static Object fromJson(String input, Class<PizzaOrder> clazz) throws JsonParseException, JsonMappingException, IOException {
       return mapper.readValue(input, clazz);
    }
}
