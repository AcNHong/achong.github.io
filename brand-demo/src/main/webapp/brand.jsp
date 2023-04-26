<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<H1>${user.username},欢迎你！</H1>

<div id="app">

    <input type="button" value="新增" id="add"><br>
    <hr>
    <table id="brandTable" border="1" cellspacing="0" width="80%">
        <tr>
            <th>序号</th>
            <th>品牌名称</th>
            <th>企业名称</th>
            <th>排序</th>
            <th>品牌介绍</th>
            <th>状态</th>
            <th>操作</th>
        </tr>


        <tr v-for="(brand,i) in brands" align="center">
            <td>{{i+1}}</td>
            <td>{{brand.brandName}}</td>
            <td>{{brand.companyName}}</td>
            <td>{{brand.ordered}}</td>
            <td>{{brand.description}}</td>
            <td>{{brand.statusStr}}</td>
            <td><a>修改</a> <a href="#">删除</a></td>
        </tr>
    </table>
</div>



<%--<c:forEach items="${brands}" var="brand" varStatus="status">
<tr align="center">
        &lt;%&ndash;<td>${brand.id}</td>&ndash;%&gt;
    <td>${status.count}</td>
    <td>${brand.brandName}</td>
    <td>${brand.companyName}</td>
    <td>${brand.ordered}</td>
    <td>${brand.description}</td>
    <c:if test="${brand.status == 1}">
        <td>启用</td>
    </c:if>
    <c:if test="${brand.status != 1}">
        <td>禁用</td>
    </c:if>

    <td><a href="/brand-demo/selectByIdServlet?id=${brand.id}">修改</a> <a href="#">删除</a></td>
</tr>


 </c:forEach>--%>


<script src="js/axios-0.18.0.js"></script>
<script src="js/vue.js"></script>


<script>
    new Vue({
        el: "#app",
        data() {
            return {
                brands:[]
            }
        },
        mounted() {
            var _this = this;
            axios({
                method: "get",
                url: "http://localhost:8080/brand-demo/selectAllServlet"
            }).then(function (resp) {
                /*这里函数是data 不是data()*/
                _this.brands = resp.data;
            })
        }
    })

</script>
</body>
</html>