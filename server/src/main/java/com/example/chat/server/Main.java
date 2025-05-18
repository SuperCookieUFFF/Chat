package com.example.chat.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Configuration config = new Configuration();
            ChatServer server = new ChatServer(config.getPort());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}