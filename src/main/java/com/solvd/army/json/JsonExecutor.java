package com.solvd.army.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonExecutor {
    private static final Logger logger = LogManager.getLogger(JsonExecutor.class);

    public static void main(String[] args) throws IOException {

        // to .json
        ObjectMapper mapper = new ObjectMapper();
        Phone phone = new Phone("Samsung", 2015, "Gleb", "Black");
        File file = new File("src/main/java/com/solvd/army/json/result.json");
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, phone);

        // from .json
        file = new File("src/main/java/com/solvd/army/json/example.json");
        Phone phoneFromJson = mapper.readValue(file, Phone.class);
        logger.info(phoneFromJson);

    }
}
