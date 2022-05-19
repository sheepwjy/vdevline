package com.vdevline.service.impl;

import com.vdevline.model.User;
import com.vdevline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User get(Integer id) {
        User user = jdbcTemplate.queryForObject("select * from user where id=?",new Object[]{id},new int[]{Types.INTEGER}, new BeanPropertyRowMapper<User>(User.class));
        return user;
    }

    @Override
    public Map<String, Object> get2(Integer id) {
        Map<String, Object> user = jdbcTemplate.queryForMap("select * from user where id=?", new Object[]{id});
        return user;
    }

    @Override
    public List<Map<String, Object>> getList() {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from user");
        return list;
    }
}
