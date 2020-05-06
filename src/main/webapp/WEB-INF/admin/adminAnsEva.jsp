<%--
  Created by IntelliJ IDEA.
  User: Steven
  Date: 2018/1/21
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回复评论</title>
</head>
<body>
<table border="1" width="50%">
    <tr>
        <th>订单编号</th>
        <th>商品名称</th>
        <th>评价内容</th>
        <th>评价时间</th>
    </tr>
    <tr>
        <td>${evaluate.orderId}</td>
        <td>${evaluate.productName}</td>
        <td>${evaluate.evaContent}</td>
        <td>${evaluate.evaDate}</td>
    </tr>
    <tr>
        <th colspan="4" align="center">回复留言</th>
    </tr>
    <form action="ansEva?orderId=${evaluate.orderId}&orderStatus=4" method="post">
        <tr>
            <td height="32px" colspan="4">
            <textarea name="ansContent" id="ansContent" cols="100" rows="10">
            </textarea>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="4"><input type="submit" value="确认回复"/></td>
        </tr>
    </form>
</table>

</body>
</html>
