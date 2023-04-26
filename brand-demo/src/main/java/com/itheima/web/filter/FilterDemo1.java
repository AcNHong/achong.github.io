package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FilterDemo1 implements Filter {

    /*
     * 登录验证*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        //防止过滤掉登录界面
        String[] arrUrl = {"/login.jsp","/loginServlet","/register.jsp","/registerServlet","/imgs/","/css/","/checkCodeServlet"};
        String url = req.getRequestURL().toString();
        for (String u : arrUrl) {
            if (url.contains(u)) {
               chain.doFilter(req,response);
               return;
            }
        }

        //判断是否登录
        if (user != null) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("login_msg", "您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }



    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


}
