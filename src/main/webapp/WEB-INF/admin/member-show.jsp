<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="${pageContext.request.contextPath}/back/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/style.css"/>
    <link href="${pageContext.request.contextPath}/back/assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="${pageContext.request.contextPath}/back/assets/js/jquery.min.js"></script>
    <title>用户查看</title>
</head>
<body>
<div class="member_show" >
    <div class="member_jbxx clearfix" >
        <img class="img" src="${pageContext.request.contextPath}/back/images/user.png">
        <dl  class="right_xxln">
            <dt><span class="">用户名：${user.username}</span></dt>
            <dd class="" style="margin-left:0">这家伙很懒，什么也没有留下</dd>
        </dl>
    </div>
    <div class="member_content">
        <ul>
            <li><label class="label_name">真实姓名：</label><span class="name">${user.userInfo.realName}</span></li>
            <li><label class="label_name">性别：</label><span class="name">男</span></li>
            <li><label class="label_name">手机：</label><span class="name">${user.userInfo.telephone}</span></li>
            <li><label class="label_name">邮箱：</label><span class="name">${user.userInfo.email}</span></li>
            <li><label class="label_name">地址：</label><span class="name">${user.userInfo.address}</span></li>
            <li><label class="label_name">注册时间：</label><span class="name">2014.12.20</span></li>
            <li><label class="label_name">积分：</label><span class="name">${user.userInfo.score}</span></li>
            <li><label class="label_name">等级：</label><span class="name">普通用户</span></li>
<%--            <c:if test="${ 0< user.userInfo.score < 100 }">
                <li><label class="label_name">等级：</label><span class="name">普通用户</span></li>
            </c:if>
            <c:if test="${ 100< user.userInfo.score < 200 }">
                <li><label class="label_name">等级：</label><span class="name">铁牌用户</span></li>
            </c:if>
            <c:if test="${ 200< user.userInfo.score < 300 }">
                <li><label class="label_name">等级：</label><span class="name">铜牌用户</span></li>
            </c:if>
            <c:if test="${ 300< user.userInfo.score < 400 }">
                <li><label class="label_name">等级：</label><span class="name">金牌用户</span></li>
            </c:if>--%>


        </ul>
        <hr/>

    </div>
</div>
</body>
</html>