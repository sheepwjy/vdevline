package com.vdevline.controller;

import com.vdevline.model.User;
import com.vdevline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VDLController {

    @Autowired
    UserService userService;

    @RequestMapping("/api/get")
    User getUser(@RequestParam(value="id", defaultValue="1") Integer id) {
        User user = userService.get(id);
        return new User("wangjy", 1);
    }
}
