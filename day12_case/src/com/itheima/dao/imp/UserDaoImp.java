package com.itheima.dao.imp;


import com.itheima.dao.UserDao;
import com.itheima.domain.Manager;
import com.itheima.domain.User;
import com.itheima.utils.JDBCUtils;
import org.omg.CORBA.Current;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author zb
 * @description
 * @date 2019/4/20
 */
public class UserDaoImp implements UserDao {
    private JdbcTemplate template= new JdbcTemplate(JDBCUtils.getDs());
    @Override
    public List<User> findAll() {
        String sql="select * from user";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public void add(User user) {
        String sql="insert into user values (null,?,?,?,?,?,?)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void update(User user) {
        String sql="update user set gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public void delete(int i) {
        String sql="delete from user where id=?";
        template.update(sql,i);
    }

    @Override
    public Manager login(String username, String password) {
        try {
            String sql="select * from manager where username=? and password=?";
            Manager manager = template.queryForObject(sql, new BeanPropertyRowMapper<Manager>(Manager.class), username, password);
            return manager;
        }catch (DataAccessException e){
            System.out.println("数据有误");
            return null;
        }

    }

    @Override
    public User findById(int id) {
        try {
            String sql = "select * from user where id=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
            return user;
        } catch (DataAccessException e) {
            System.out.println("数据有误");
            return null;
        }
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql 利用where 1=1
        String sql="select count(id) from user WHERE 1=1";
        StringBuilder sb=new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //3.定义参数的集合
        List<Object> params= new ArrayList<>();
        for (String key : keySet) {
            //排除分页条件查询参数
            if ("currentPage".equals(key.trim())||"rows".equals(key.trim())||"page".equals(key.trim())){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        Integer n = template.queryForObject(sb.toString(), Integer.class,params.toArray());
        return n;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List<Object> params=new ArrayList<>();
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {

            if ("currentPage".equals(key.trim())|| "rows".equals(key.trim())|| "page".equals(key.trim())){
                continue;
            }
            String value = condition.get(key)[0];
            if (value!=null&&!"".equals(value)){
                sb.append(" and "+ key +" like ?");
                params.add("%"+value+"%");
            }
        }
        //在sql语句的最后拼上分页条件
        sb.append(" limit ?,?");
        //将分页条件的参数添加到参数集合中
        params.add(start);
        params.add(rows);
        System.out.println(sb.toString());
        System.out.println(params);
        List<User> users = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class),params.toArray());
        return users;
    }

}
