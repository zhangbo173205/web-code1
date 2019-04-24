package com.itheima.web.servlet;

import com.itheima.service.UserService;
import com.itheima.service.imp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的编码格式
        request.setCharacterEncoding("utf-8");
        //获取请求中的id值
        String id = request.getParameter("id2");
        //获取请求中的当前页码
        String page = request.getParameter("page");
        //将String类型的id转换为id类型
        int i ;
        try {
            i = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i=0;
        }
        UserService userService=new UserServiceImp();
        //将得到的id值 传入到Service中的delete(id)方法执行删除操作
        userService.delete(i);
        //重定向到查询Servlet中,到list.jsp中展示指定页码;
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?currentPage="+page);
       /* request.getRequestDispatcher("/findUserByPageServlet?currentPage="+page).forward(request,response);*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
