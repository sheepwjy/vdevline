package com.vdevline.controller;

import com.vdevline.model.Result;
import com.vdevline.model.User;
import com.vdevline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VDLController {

    @Autowired
    UserService userService;

    @RequestMapping("/api/getpv")
    Result getPageView(@RequestParam(value="page_id") String pageId) {
        return null;
    }


    @RequestMapping("/api/get")
    User getUser(@RequestParam(value="id", defaultValue="1") Integer id) {
        User user = userService.get(id);
        System.out.println("测试123");
        return user;
    }

    @RequestMapping("/api/get2")
    Map<String,Object> getUser2(@RequestParam(value="id", defaultValue="1") Integer id) {
        Map<String,Object> user = userService.get2(id);
        return user;
    }

    @RequestMapping("/api/getlist")
    List<Map<String,Object>> getUser3() {
        List<Map<String, Object>> users = userService.getList();
        return users;
    }
}
