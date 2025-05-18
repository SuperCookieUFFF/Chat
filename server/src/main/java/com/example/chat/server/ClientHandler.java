package com.example.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ChatServer server;
    private String clientName;

    public ClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            clientName = reader.readLine(); // Первое сообщение — имя клиента
            server.broadcastMessage("[" + clientName + "] joined the chat.");

            String message;
            while ((message = reader.readLine()) != null) {
                server.broadcastMessage("[" + clientName + "]: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.broadcastMessage("[" + clientName + "] left the chat.");
            closeConnections();
        }
    }

    public void sendMessage(String message) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnections() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}