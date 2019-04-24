package com.itheima.dao;

import com.itheima.domain.Manager;
import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> findAll();
    void add(User user);
    void update(User user);
    void delete(int i);
    Manager login(String username,String password);
    User findById(int id);
    int findTotalCount(Map<String, String[]> condition);
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
