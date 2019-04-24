package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
public class SensitiveWordFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

         ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //增强getParameter方法
                //判断是否为getParameter方法
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req, args);
                    if (value!=null){
                        for (String s : list) {
                            if (value.contains(s)) {
                            value=value.replaceAll(s, "***");
                            }
                        }
                    }
                return value;
                }
                //增强getParameters方法
                //判断是否为getParameters方法
                if (method.getName().equals("getParameterMap")){
                    Map<String, String[]> maps = (Map<String, String[]>) method.invoke(req, args);
                    if (maps!=null){
                        for (String key : maps.keySet()) {
                            String[] str = maps.get(key);
                            if (str!=null) {
                                for (int i = 0; i < str.length; i++) {
                                    for (String s : list) {
                                        if (str[i].contains(s)){
                                            str[i]=str[i].replaceAll(s,"***");
                                        }
                                    }

                                }
                            }
                        }
                    }
                    return maps;
                }

                //增强getParameterValues方法
                //判断是否为getParameterValues方法
                if(method.getName().equals("getParameterValues")){
                    String[] strs =(String[]) method.invoke(req, args);
                    if (strs!=null) {
                        for (int i = 0; i < strs.length; i++) {
                            for (String s : list) {
                                if (strs[i].contains(s)){
                                    strs[i]=strs[i].replaceAll(s,"***");
                                }
                            }

                            }
                        }

                    return strs;
                }


               return method.invoke(req,args);
            }
        });
        chain.doFilter(proxy_req, resp);
    }


    private List<String> list = new ArrayList<>();  //定义一个敏感词数组

    public void init(FilterConfig config) throws ServletException {


        try {
            //获取敏感字词汇的真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //将文件中的数据一行一行的写入list中
            String line=null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();

            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {

    }

}
