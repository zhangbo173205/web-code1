package com.itheima.web.servlet;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.imp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取请求中的id1的值
        String id1 = request.getParameter("id1");
        //获取请求中的当前页码
        String page = request.getParameter("page");
        UserService userService=new UserServiceImp();
        //将id1传入到findUserByID(id)中;执行指定id的查询操作,返回User对象
        User user = userService.findUserByID(id1);
        //将user对象放入request的共享域中
        request.setAttribute("user",user);
        //将当前页码Page,放入Session域中共享
        request.getSession().setAttribute("currentPage",page);
        //转发到update.jsp页面中,将user对象中的值进行回显
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
