/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.happyserver.dao.impl;

import com.leapfrog.happyserver.dao.UserDAO;
import com.leapfrog.happyserver.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zak
 */
public class UserDAOImpl implements UserDAO {

    private List<User> userList = new ArrayList<>();

    @Override
    public boolean insert(User user) {
        return userList.add(user);
    }

    @Override
    public List<User> getAll() {
        userList.add(new User(1, "ram", "ram", false));
        userList.add(new User(2, "john", "john", true));
        userList.add(new User(3, "alyssa", "alyssa", true));
        return userList;
    }

    @Override
    public User login(String userName, String password) {
        for (User u : userList) {
            if (u.getUserName().equalsIgnoreCase(userName) && u.getPassword().equalsIgnoreCase(password)) {
                return u;
            }
        }
        return null;
    }

}
