package com.example.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private int port;
    private Set<ClientHandler> clientHandlers = new HashSet<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected");
            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            clientHandlers.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    public void broadcastMessage(String message) {
        for (ClientHandler handler : clientHandlers) {
            handler.sendMessage(message);
        }
        Logger.logMessage(message); // Логирование сообщения
    }

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