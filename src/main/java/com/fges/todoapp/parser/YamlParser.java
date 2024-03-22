package com.fges.todoapp.parser;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YamlParser {
    public List<Map<String, String>> loadCommandMappings(String yamlPath) {
        try (InputStream inputStream = getClass().getResourceAsStream("/commands.yaml")) {
            if (inputStream == null) {
                throw new IllegalStateException("File not found");
            }

            Yaml yaml = new Yaml();
            Map<String, List<Map<String, String>>> obj = yaml.load(inputStream);
            return obj.get("commands");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load command mappings", e);
        }
    }
}
