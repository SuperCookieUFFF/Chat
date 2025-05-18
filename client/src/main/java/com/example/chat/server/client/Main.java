package com.example.chat.server.client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Configuration config = new Configuration("settings.txt");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String clientName = scanner.nextLine();

            ChatClient client = new ChatClient("localhost", config.getPort(), clientName);
            client.start();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла конфигурации: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат порта в файле конфигурации: " + e.getMessage());
        }
    }
}