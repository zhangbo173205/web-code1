package com.itheima.web.servlet;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.imp.UserServiceImp;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/upServlet")
public class UpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的编码格式
        request.setCharacterEncoding("utf-8");
        ////获取提交数据的map集合
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            //利用BeanUtils工具类将Map集合的数据封装到User对象中
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        //获取Session域中的的当前页码
        String currentPage =(String) session.getAttribute("currentPage");
        //清除该Session;防止对后续的干扰
        session.removeAttribute("currentPage");
        UserService userService=new UserServiceImp();
        //将得到的user对象传入Service接口中的update方法,执行数据库的修改操作
        userService.update(user);
        ////重定向到查询Servlet中,并带上当前页码值,到list.jsp上展示
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?currentPage="+currentPage);
        //request.getRequestDispatcher("/findUserByPageServlet?currentPage="+currentPage).forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
