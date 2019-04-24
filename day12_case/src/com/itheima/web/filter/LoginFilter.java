package com.itheima.web.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强制转换
        HttpServletRequest request=(HttpServletRequest) req;

        //2.获取资源请求路径
        String uri = request.getRequestURI();
        //3.判断是否包含登录有关的资源路径,要注意
        if (uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/checkCode")||uri.contains("/css/")
                ||uri.contains("/fonts/")||uri.contains("/js/")){
            chain.doFilter(req, resp);
        }else{
            Object manager = request.getSession().getAttribute("manager");
            if (manager!=null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("msg","你没有登录,请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }

}

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
