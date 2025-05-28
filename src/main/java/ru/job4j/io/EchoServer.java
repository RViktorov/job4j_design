package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9001)) {
            boolean serverRunning = true;
            while (serverRunning && !server.isClosed()) {
                Socket socket = server.accept();

                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String request = input.readLine();
                    String responseMessage = null;
                    if (request.contains("msg=Hello")) {
                        responseMessage = "Hello, dear friend.";
                    } else if (request.contains("msg=Exit")) {
                        responseMessage = "Goodbye!";
                        serverRunning = false;
                        System.out.println("Request received msg=Exit, stop the server.");
                    } else {
                        int idx = request.indexOf("msg=");
                        if (idx != -1) {
                            String msgPart = request.substring(idx + 4);
                            int endIdx = msgPart.indexOf(' ');
                            if (endIdx != -1) {
                                msgPart = msgPart.substring(0, endIdx);
                            }
                            responseMessage = msgPart;
                        }
                    }
                    for (String string = input.readLine();
                         string != null && !string.isEmpty();
                         string = input.readLine()) {
                        System.out.println(string);
                    }

                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    output.write(responseMessage != null ? responseMessage.getBytes() : "not a correct request".getBytes());
                    output.flush();
                }
            }
        }
    }
}