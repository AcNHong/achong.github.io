package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String REGISTER_MSG = "register_msg";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //密码格式
        String checkCode = request.getParameter("checkCode");

        HttpSession session = request.getSession();
        String checkCodeImg = (String) session.getAttribute("checkCodeImg");

        if(!checkCodeImg.equals(checkCode)){

            request.setAttribute(REGISTER_MSG,"验证码错误！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

       if(password.length()<6){
           request.setAttribute(REGISTER_MSG,"密码格式有误，长度大于等于6");
           request.getRequestDispatcher("/register.jsp").forward(request,response);
           return ;
       }


        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Boolean isEmpty = userService.add(user);



        if(isEmpty){
            //跳转登录界面 提示注册成功
            request.setAttribute(REGISTER_MSG,"注册成功，请登录！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //注册失败
            request.setAttribute(REGISTER_MSG,"用户名已存在，请重新输入！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
