package com.example.chat.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final String FILE_PATH = "settings.txt";

    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            int port = configuration.getPort();
            System.out.println("Port: " + port);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла конфигурации: " + e.getMessage());
        }
    }

    public int getPort() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream(FILE_PATH)) {
            if (input == null) {
                throw new IOException("Файл не найден: " + FILE_PATH);
            }
            properties.load(input);
            return Integer.parseInt(properties.getProperty("port"));
        }
    }
}