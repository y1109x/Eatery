<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 2018/1/20
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户评价</title>
</head>
<body>
<jsp:include page="../base/head.jsp"></jsp:include>
<form action="/saveEva" method="post">
    <input type="hidden" name="orderId" value="${o}"/>
    <input type="hidden" name="productName" value="${p}"/>
    <input type="hidden" name="status" value="${status}">
    <table border="1" width="60%" align="center">
        <tr>
            <th>订单编号</th>
            <th>${o}</th>
        </tr>
        <tr>
            <th>商品名称</th>
            <th>${p}</th>
        </tr>
        <tr>
            <td colspan="2" align="center">评价内容</td>
        </tr>
        <tr>
            <td colspan="2" align="center" height="30">
                <textarea id="evaContent" cols="80" rows="10" name="content">
                    请输入评价内容
                </textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交评论"></td>
        </tr>
    </table>
</form>
</body>
</html>
