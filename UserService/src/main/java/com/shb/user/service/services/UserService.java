package com.shb.user.service.services;

import com.shb.user.service.entity.User;

import java.util.List;

public interface UserService {

    //User Operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get user by id
    User getUser(String userId);

    //update user by id
    User updateUser(User user);

    //Delete user by id
    void deleteUser(String userId);
}
