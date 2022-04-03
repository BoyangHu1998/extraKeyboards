package com.ek.extrakeyboards.controller;

import com.ek.extrakeyboards.entity.User;
import com.ek.extrakeyboards.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value="/getUserProfile", method = RequestMethod.GET)
    public ResponseEntity<User> getUserProfile(@RequestParam(value = "user_id") String userId) {
        User user = userService.getUser(userId);
        return user == null ? new ResponseEntity<User>((User) null, HttpStatus.BAD_REQUEST): new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
