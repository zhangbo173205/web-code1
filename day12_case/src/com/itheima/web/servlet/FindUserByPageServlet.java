package com.itheima.web.servlet;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.imp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("Utf-8");
        //获取请求中的currentPage和rows的值
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");  //每页条数

        //当请求数据中没有currentPage和rows的值,初始化currentPage和rows的值
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }

        if (rows==null||"".equals(rows)){
            rows="5";
        }

        //获取条件查询提交数据的map集合
        Map<String, String[]> condition = request.getParameterMap();

        //调用service查询
        UserService userService=new UserServiceImp();

        //将currentPage和rows,条件查询的参数的map集合放入Service中的方法中执行findUserByPage,等到我们封装的Pagebean对象
        PageBean<User> pb=userService.findUserByPage(currentPage,rows,condition);
        System.out.println(pb);
        //将PageBean存入request共享域中
        request.setAttribute("pb",pb);

        //另外将参数Map集合存入request共享域,进行回显
        request.setAttribute("condition",condition);
        //转发到list.jsp中,在页面上显示出来
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
