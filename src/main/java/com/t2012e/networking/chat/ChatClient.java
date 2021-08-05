package com.t2012e.networking.chat;

import java.io.*;
import java.net.Socket;

public class ChatClient extends Thread{
    private Socket socket; // kết nối socket máy chủ
    private BufferedWriter sendMessageChannel; // kênh gửi dữ liệu
    private BufferedReader receiveMessageChannel; // kênh nhận dữ liệu

    public ChatClient(){
        try {
            // kêt nối tới máy chủ
            this.socket = new Socket("localhost", 6666);
            System.out.println("kêt nối thành công đến server");
            //tạo kênh gửi dữ liệu
            this.sendMessageChannel = new BufferedWriter( new OutputStreamWriter(this.socket.getOutputStream()));
            System.out.println("thiết lập kênh gửi dữ thành công");
            //tạo kênh nhận dữ liệu
            this.receiveMessageChannel = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            System.out.println("thiết lập kênh nhận dữ liệu thành công");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        receiveMessage();
    }

    public void sendMessage(String mess){
        try {
            this.sendMessageChannel.write(mess);
            this.sendMessageChannel.newLine();
            this.sendMessageChannel.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage(){
        while (true){
            try {
                String result = null;
                if ((result = this.receiveMessageChannel.readLine()) != null){
                    if (result.equalsIgnoreCase("bye")){
                        System.out.println("server ngắt kết nối");
                        break;
                    }
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

