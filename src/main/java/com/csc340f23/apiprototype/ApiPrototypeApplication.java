package com.csc340f23.apiprototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPrototypeApplication.class, args);
        getData();
    }

    public static void getData() {
        try {
            String apiString = "https://api.animality.xyz/fact/dog";

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonString = restTemplate.getForObject(apiString, String.class);
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            String animal = jsonNode.findValue("animal").asText();
            String fact = jsonNode.findValue("fact").asText();

            System.out.printf("\nRandom %s fact:\n", animal);
            System.out.println(fact);
            System.out.println("\nRefresh for more facts!");
        }
        catch (JsonProcessingException processingException) {
            System.out.println("Error: Could not process JSON");
        }
    }

}
