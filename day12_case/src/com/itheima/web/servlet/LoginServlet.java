package com.itheima.web.servlet;

import com.itheima.domain.Manager;
import com.itheima.service.UserService;
import com.itheima.service.imp.UserServiceImp;
import org.apache.commons.beanutils.BeanUtils;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的编码格式
        request.setCharacterEncoding("utf-8");
        //获取生成的验证码和输入的验证码
        String vcode = request.getParameter("verifycode");
        String ccode =(String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //将生成的验证码和输入的验证码进行比对
        if (ccode==null||!ccode.equalsIgnoreCase(vcode)){
            //当两者不相同,或者生成的验证码为空,转发"验证码错误"到/login.jsp页面中
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //当两者相同,获取输入的用户名和密码的map集合
            Map<String, String[]> map = request.getParameterMap();
            Manager manager=new Manager();
            try {
                ////利用BeanUtils工具类将Map集合的数据封装到manager对象中
                BeanUtils.populate(manager,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            UserService userService=new UserServiceImp();
            //当两者相同,从数据库中查询用户名和密码进行验证,调用Service中的login方法,返回一个Manager对象
            Manager manager1 = userService.login(manager);

            if (manager1!=null){
                //当返回值manager对象存在时,将其放入Session域中,重定向到首页index.jsp中
                request.getSession().setAttribute("manager",manager1);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }else{
                //当返回值manager对象不存在时,转发"用户名和密码错误"到/login.jsp页面中
                request.setAttribute("msg","用户名和密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
