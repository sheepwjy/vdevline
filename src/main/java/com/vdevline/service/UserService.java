package com.vdevline.service;

import com.vdevline.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User get(Integer id);
    public Map<String,Object> get2(Integer id);

    public List<Map<String, Object>> getList();
}
