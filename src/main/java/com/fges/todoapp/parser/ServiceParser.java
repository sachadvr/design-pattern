package com.fges.todoapp.parser;

import com.fges.todoapp.ErrorHandling;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.options.Option;
import com.fges.todoapp.service.Loading.LoadService;
import com.fges.todoapp.service.Loading.LoadServiceInterface;
import com.fges.todoapp.service.Writing.WriteService;
import com.fges.todoapp.service.Writing.WriteServiceInterface;
import com.fges.todoapp.tools.OptionManager;
import org.apache.commons.cli.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceParser {

    public WriteService findWriteService(String name) {
        List<Map<String, String>> serviceMapping = new YamlParser().loadCommandMappings("/services.yaml", "write-services");
        int index = serviceMapping.indexOf(
                serviceMapping.stream()
                        .filter(mapping -> name.endsWith(mapping.get("extension")))
                        .findFirst()
                        .orElse(null)
        );
        if (index != -1) {
            try {
                Class<? extends WriteService> writeServiceClass = (Class<? extends WriteService>) Class.forName(serviceMapping.get(index).get("class"));
                WriteService writeService = writeServiceClass.getDeclaredConstructor().newInstance();
                return (WriteService) writeService;
            } catch (Exception e) {
                ErrorHandling.printError("Failed to load write service", e);
            }
        }
        return null;
    }


    public LoadService findLoadService(String name) {
        List<Map<String, String>> serviceMapping = new YamlParser().loadCommandMappings("/services.yaml", "load-services");
        int index = serviceMapping.indexOf(
                serviceMapping.stream()
                        .filter(mapping -> name.endsWith(mapping.get("extension")))
                        .findFirst()
                        .orElse(null)
        );
        if (index != -1) {
            try {
                Class<? extends LoadService> loadServiceClass = (Class<? extends LoadService>) Class.forName(serviceMapping.get(index).get("class"));
                LoadService loadService = loadServiceClass.getDeclaredConstructor().newInstance();
                return (LoadService) loadService;
            } catch (Exception e) {
                ErrorHandling.printError("Failed to load load service", e);
            }

        }
        return null;
    }



}
