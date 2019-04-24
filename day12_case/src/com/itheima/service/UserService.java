package com.itheima.service;

import com.itheima.domain.Manager;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();
    public void add(User user);
    void update(User user);
    void delete(int i);
    Manager login(Manager m);
    User findUserByID(String id);
    void deleteSelect(String[] str);
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

}
