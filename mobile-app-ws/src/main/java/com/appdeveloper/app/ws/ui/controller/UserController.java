package com.appdeveloper.app.ws.ui.controller;

import com.appdeveloper.app.ws.shared.Utils;
import com.appdeveloper.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdeveloper.app.ws.ui.model.response.UserRest;
import com.appdeveloper.app.ws.userservice.UserService;
import com.appdeveloper.app.ws.userservice.implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser(@RequestParam(value="page", defaultValue = "1") int page,
                          @RequestParam(value="limit", defaultValue =  "50") int limit,
                          @RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
        return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path="/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            } )
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping (consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            } )
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails) {

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

//        UserRest returnValue = userService.create(userDetails);

        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping (path="/{userId}",
            consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public UserRest updateUser(@PathVariable String userId,
                             @RequestBody UserDetailsRequestModel userDetails) {
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId,storedUserDetails);

        return storedUserDetails;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        users.remove(id);

        return ResponseEntity.noContent().build();
    }

}
