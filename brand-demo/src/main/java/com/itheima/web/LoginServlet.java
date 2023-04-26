package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = userService.Select(username, password);


        if (user != null) {
            //登陆成功
            String cookieKey = "yeah";
            if(cookieKey.equals(remember)){
                Cookie cookie_username = new Cookie("username",username);
                Cookie cookie_password = new Cookie("password",password);

                cookie_password.setMaxAge(60*60*24*7);
                cookie_username.setMaxAge(60*60*24*7);

                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //虚拟目录
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/selectAllServlet");
        } else {
            //登陆失败
            request.setAttribute("login_msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
