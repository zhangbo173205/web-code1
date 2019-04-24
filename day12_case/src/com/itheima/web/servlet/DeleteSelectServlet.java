package com.itheima.web.servlet;

import com.itheima.service.UserService;
import com.itheima.service.imp.UserServiceImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectServlet")
public class DeleteSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的编码格式
        request.setCharacterEncoding("utf-8");
        //获取请求中的id值,并将它们放入数组ids
        String[] ids = request.getParameterValues("id");
        //获取请求中的当前页码
        String currentPage = request.getParameter("page");
        UserService userService=new UserServiceImp();
        //将得到的id值数组 传入到Service中的deleteSelect(ids)方法执行删除操作
        userService.deleteSelect(ids);
        //重定向到查询Servlet中,到list.jsp中展示指定页码;
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?currentPage="+currentPage);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
