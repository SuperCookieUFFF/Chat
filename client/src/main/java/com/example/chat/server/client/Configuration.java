package com.example.chat.server.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties properties;

    public Configuration(String filePath) throws IOException {
        properties = new Properties();
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IOException("Файл конфигурации не найден: " + filePath);
            }
            properties.load(input);
        }
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }
}