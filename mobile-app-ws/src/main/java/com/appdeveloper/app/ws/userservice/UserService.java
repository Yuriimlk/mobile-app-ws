package com.appdeveloper.app.ws.userservice;

import com.appdeveloper.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdeveloper.app.ws.ui.model.response.UserRest;

public interface UserService {

    UserRest create(UserDetailsRequestModel userDetails);

}
