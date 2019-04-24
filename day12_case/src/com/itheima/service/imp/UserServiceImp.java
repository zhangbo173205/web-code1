package com.itheima.service.imp;

import com.itheima.dao.UserDao;
import com.itheima.dao.imp.UserDaoImp;
import com.itheima.domain.Manager;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author zb
 * @description
 * @date 2019/4/20
 */
public class UserServiceImp implements UserService {
    public UserDao userDao=new UserDaoImp();



    //查询所有
    @Override
    public List<User> findAll() {
        return userDao.findAll();
}
    //添加数据
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    //更新数据
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    //删除删除指定id
    @Override
    public void delete(int i) {
        userDao.delete(i);
    }

    //登录验证
    @Override
    public Manager login(Manager m) {

        return userDao.login(m.getUsername(),m.getPassword());
    }

    //查找指定id
    @Override
    public User findUserByID(String id) {
        return userDao.findById(Integer.parseInt(id));
    }



    //删除指定id数组的数据
    @Override
    public void deleteSelect(String[] str) {
        if (str!=null&&str.length>0) {
            //遍历id数值,调用userDao的delete()方法依次删除
            for (int i = 0; i < str.length; i++) {
                userDao.delete(Integer.parseInt(str[i]));
            }
        }
    }


    //按指定页码,每页条数,条件查询进行查询返回PageBean对象
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        //创建空的pageBean对象
        PageBean<User> pb=new PageBean<>();
        //验证是否有恶意输入,捕获异常
        int currentPage ;
        int rows ;
        try {
            currentPage = Integer.parseInt(_currentPage);
            rows = Integer.parseInt(_rows);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            currentPage=1;
            rows=5;
        }
        //调用到查询总记录数
        int totalCount=userDao.findTotalCount(condition);
        //判断当前页码小于1时
        if (currentPage<=0){
            currentPage=1;
        }
        //得到总页码数
        int totalPage=(totalCount%rows)==0?(totalCount/rows):(totalCount/rows+1);
        //判断当前页码大于总页码时
        if (currentPage>totalPage){
            currentPage=totalPage;
        }
        //调用dao查询List集合
        int start=(currentPage-1)*rows;
        List<User> list=userDao.findByPage(start,rows,condition);
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalPage(totalPage);
        pb.setList(list);
        pb.setTotalCount(totalCount);
        return pb;
    }

}
