<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>添加品牌</title>
</head>
<body>
<h3>添加品牌</h3>

<div id="app">

    <%--加上view-model实现视图模型的一致性 更换数据--%>
    <form action="/brand-demo/addServlet" method="post">
        品牌名称：<input id="brandName" v-model="brand.brandName" name="brandName"><br>
        企业名称：<input id="companyName" v-model="brand.companyName" name="companyName"><br>
        排序：<input id="ordered"  v-model="brand.ordered" name="ordered"><br>
        描述信息：<textarea rows="5" cols="20" id="description" v-model="brand.description" name="description"></textarea><br>
        状态：
        <input type="radio" name="status"  v-model="brand.status" value="0">禁用
        <input type="radio" name="status" v-model="brand.status" value="1">启用<br>

        <input type="button" id="btn" @click="submitForm" value="提交">
    </form>
</div>

<script src="js/axios-0.18.0.js"></script>
<script src="js/vue.js"></script>


<script>
    new Vue({
        el: "#app",
        data() {
            return {
                brand: {}
            }
        },
        methods: {
            submitForm() {
// 发送ajax请求，添加
                var _this = this;
                axios({
                    method: "post",
                    url: "http://localhost:8080/brand-demo/addServlet",
                    data: _this.brand
                }).then(function (resp) {
// 判断响应数据是否为 success
                    if (resp.data == "success") {
                        location.href = "http://localhost:8080/brand-demo/brand.jsp";
                    }
                })
            }
        }


    })


    /* //1. 给按钮绑定单击事件
     document.getElementById("btn").onclick = function () {

         // 将表单数据转为json
         var formData = {
             brandName:"",
             companyName:"",
             ordered:"",
             description:"",
             status:"",
         };*/
    /*// 获取表单数据
            let brandName = document.getElementById("brandName").value;
    // 设置数据
            formData.brandName = brandName;
    // 获取表单数据
            let companyName = document.getElementById("companyName").value;
    // 设置数据
            formData.companyName = companyName;
    // 获取表单数据
            let ordered = document.getElementById("ordered").value;
    // 设置数据
            formData.ordered = ordered;

    // 获取表单数据
            let description = document.getElementById("description").value;
    // 设置数据
            formData.description = description;
            let status = document.getElementsByName("status");
            for (let i = 0; i < status.length; i++) {
                if(status[i].checked){
                    formData.status = status[i].value ;
                }
            }*/

    //2. 发送ajax请求
    axios({
        method: "post",
        url: "http://localhost:8080/brand-demo/addServlet",
        data: formData
    }).then(function (resp) {
// 判断响应数据是否为 success
        if (resp.data == "success") {
            location.href = "http://localhost:8080/brand-demo/brand.jsp";
        }
    })


</script>


</body>
</html>