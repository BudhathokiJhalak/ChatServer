/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.happyserver.command;

import com.leapfrog.happyserver.handler.Client;
import com.leapfrog.happyserver.handler.ClientHandler;
import java.io.IOException;

/**
 *
 * @author zak
 */
public abstract class ChatCommand {
    protected ClientHandler handler;
    
    public abstract void execute(Client client, String msg) throws IOException;

    public ClientHandler getHandler() {
        return handler;
    }

    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }
    
    
}
