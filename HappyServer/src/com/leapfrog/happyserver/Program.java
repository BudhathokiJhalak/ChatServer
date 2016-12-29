/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.happyserver;

import com.leapfrog.happyserver.handler.Client;
import com.leapfrog.happyserver.handler.ClientHandler;
import com.leapfrog.happyserver.handler.ClientListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zak
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 9000;
        try {
            ServerSocket server = new ServerSocket(port);
            ClientHandler handler = new ClientHandler();
            System.out.println("Server is running at " + port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("Got connection from " + socket.getInetAddress().getHostName());
                ClientListener listener = new ClientListener(socket, handler);
                listener.start();
            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

}
