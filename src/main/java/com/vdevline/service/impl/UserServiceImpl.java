package com.vdevline.service.impl;

import com.vdevline.model.User;
import com.vdevline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User get(Integer age) {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from user");
        return new User();
    }
}
