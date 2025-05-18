package com.example.chat.server.client;

public class ChatClient {
    private String host;
    private int port;
    private String clientName;

    public ChatClient(String host, int port, String clientName) {
        this.host = host;
        this.port = port;
        this.clientName = clientName;
    }

    public void start() {
        // Логика запуска клиента чата
        System.out.println("Запуск клиента чата: " + clientName + " на " + host + ":" + port);
    }
}