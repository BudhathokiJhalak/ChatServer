/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.happyserver.handler;

import com.leapfrog.happyserver.command.ChatCommand;
import com.leapfrog.happyserver.command.ListCommand;
import com.leapfrog.happyserver.command.PublicMessageCommand;
import com.leapfrog.happyserver.dao.UserDAO;
import com.leapfrog.happyserver.dao.impl.UserDAOImpl;
import com.leapfrog.happyserver.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author zak
 */
public class ClientListener extends Thread {

    private ClientHandler handler;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader reader;
    private Client client;
    private UserDAO userDAO = new UserDAOImpl();

    public ClientListener(Socket socket, ClientHandler handler) throws IOException {
        this.socket = socket;
        this.handler = handler;
        ps = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            ps.println("Welcome to Happy Server.");
            while(!doLogin()){
                
            }
            String line = "";
            while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
//                if(line.equalsIgnoreCase("line")){
//                    Process process = new ProcessBuilder(line).start();
//                }
                System.out.println(line);
                ChatCommand cmd;
                if (line.equalsIgnoreCase("list")) {
                    cmd = new ListCommand();
                } else {
                    cmd = new PublicMessageCommand();
                }
                cmd.setHandler(handler);
                cmd.execute(client, line);
            }
            handler.removeClient(client);
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    private boolean doLogin() throws IOException {
        ps.println("Enter your name:");
        String name = reader.readLine();
        ps.println("Enter your password:");
        String password = reader.readLine();
        User user = userDAO.login(name, password);
        if (user == null) {
            ps.println("Invalid username");
            return false;
        } else {
            if (!user.isStatus()) {
                ps.println("Your account is not Active.");
                return false;
            } else {
                ps.println("Hello " + name);
                client = new Client(name, socket);
                handler.addClient(client);
                System.out.println(name);
                return true;
            }
        }

    }

}
