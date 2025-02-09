package com.appdeveloper.app.ws.userservice.implementation;

import com.appdeveloper.app.ws.shared.Utils;
import com.appdeveloper.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdeveloper.app.ws.ui.model.response.UserRest;
import com.appdeveloper.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImplementation implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImplementation() {}

    @Autowired
    public UserServiceImplementation(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest create(UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
        returnValue.setEmail(userDetails.getEmail());

        String userId = Utils.generateUserId();
        returnValue.setUserId(userId);

        if (users == null) {
            users = new HashMap<>();
        }
        users.put(userId, returnValue);

        return returnValue;
    }

}
