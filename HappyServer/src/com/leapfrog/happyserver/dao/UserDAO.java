/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.happyserver.dao;

import com.leapfrog.happyserver.entity.User;
import java.util.List;

/**
 *
 * @author zak
 */
public interface UserDAO {

    boolean insert(User user);

    List<User> getAll();

    User login(String userName, String password);
}
