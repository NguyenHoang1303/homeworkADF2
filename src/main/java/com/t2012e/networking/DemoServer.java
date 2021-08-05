package com.t2012e.networking;

import com.t2012e.networking.chat.ChatClientHandler;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DemoServer {
   static List<ChatClientHandler> listChats = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server started! Waiting for client connect...");
            while (true){
                //chỉ gọi khi 1 client thực hiện connect đến server
                Socket socket = serverSocket.accept();
                ChatClientHandler chatClientHandler = new ChatClientHandler(socket);
                //start luông chạy // để có thể chạy tiêp vòng lặp.
                chatClientHandler.start();
                listChats.add(chatClientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(String content) {
        for (ChatClientHandler chatClient: listChats) {
            chatClient.sendMessage(content);
        }
    }
}
