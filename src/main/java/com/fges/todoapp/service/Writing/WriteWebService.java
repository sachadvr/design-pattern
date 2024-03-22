package com.fges.todoapp.service.Writing;

import com.fges.todoapp.model.TodoList;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Path;

public class WriteWebService implements WriteService {

    public void write(Path filePath, TodoList nodes) throws IOException {
        JSONObject json = new JSONObject();
        json.put("name", nodes.list().get(0).getName());
        json.put("done", nodes.list().get(0).isDone());
        HttpURLConnection connection = (HttpURLConnection) URI.create("http://localhost:8081/todos").toURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.getOutputStream().write(json.toString().getBytes());
        connection.connect();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
        }
        connection.disconnect();


    }
}
