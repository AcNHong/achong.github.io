package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import com.itheima.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private  BrandService service = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1. 调用BrandService完成查询
        List<Brand> brands = service.selectAll();

        String jsonString = JSON.toJSONString(brands);

        /*User user = JSON.parseObject(jsonString,User.class);*/

        response.setContentType("text/json;charset=utf-8");

        response.getWriter().write(jsonString);

      /*  //2. 存入request域中
        request.setAttribute("brands",brands);

        //3. 转发到brand.jsp
        request.getRequestDispatcher("/brand.jsp").forward(request,response);*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}