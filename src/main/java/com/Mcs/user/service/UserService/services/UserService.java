package com.Mcs.user.service.UserService.services;

import com.Mcs.user.service.UserService.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);

}
