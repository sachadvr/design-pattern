package com.fges.todoapp.service;

import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.TodoList;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.file.Path;

public class LoadWebService implements LoadService {

    public TodoList getTodos(String fileContent, OptionsParser op, Path filePath) throws Exception {
        // get response from a get json
        HttpURLConnection connection = (HttpURLConnection) URI.create("http://localhost:8081/todo").toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.connect();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return new LoadJsonService().getTodos(response.toString(), op, filePath);
    }
}
