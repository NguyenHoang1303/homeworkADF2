package com.t2012e.networking;

import com.t2012e.networking.chat.ChatClient;

import java.util.Scanner;

public class DemoCLient extends Thread {

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Plesea enter message to send: ");
        while (true){
            String content = scanner.nextLine();
            if(content.equalsIgnoreCase("bye")){
                break;
            }
            chatClient.sendMessage(content);
        }
    }
}
