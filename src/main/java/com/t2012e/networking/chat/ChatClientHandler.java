package com.t2012e.networking.chat;

import com.t2012e.networking.DemoServer;

import java.io.*;
import java.net.Socket;

public class ChatClientHandler extends Thread {
    private Socket socket;
    private BufferedWriter sendMessageChannel;
    private BufferedReader receiveMessageChannel;

    public ChatClientHandler(Socket socket) {
        try {
            this.socket = socket;
            System.out.printf("Client %s kêt nối thành công đến server\n",this.socket.getInetAddress());
            //tạo kênh gửi dữ liệu
            this.sendMessageChannel = new BufferedWriter( new OutputStreamWriter(this.socket.getOutputStream()));
            //tạo kênh nhận dữ liệu
            this.receiveMessageChannel = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            String result = null;
            try {
                if ((result = this.receiveMessageChannel.readLine()) != null){
                    if (result.equalsIgnoreCase("bye")){
                        break;
                    }
                    String content = String.format("Client %s said: %s\n", this.socket.getInetAddress(),result);
                    System.out.println(content);
                    DemoServer.broadcast(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

}
