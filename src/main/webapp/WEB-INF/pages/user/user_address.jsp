<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8" />
<title>用户中心--地址修改和新增</title>
<link href="${ctx}/static/pro/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/pro/js/public.js"></script>
<script type="text/javascript" src="${ctx}/static/pro/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/pro/js/jqpublic.js"></script>
</head>
<body>
<jsp:include page="../base/head.jsp"></jsp:include>
<!--Start content-->
<section class="Psection MT20">
<nav class="U-nav Font14 FontW">
  <ul>
   <li><i></i><a href="userCenter" target="_self">用户中心首页</a></li>
   <li><i></i><a href="userOrderList" target="_self">我的订单</a></li>
   <li><i></i><a href="userAddress" target="_self">收货地址</a></li>
   <li><i></i><a href="userAccount" target="_self">账户管理</a></li>
   <li><i></i><a href="userLogout" target="_self">安全退出</a></li>
  </ul>
 </nav>
 <article class="U-article Overflow">
  <!--user Address-->
  <section class="Myaddress Overflow">
   <span class="MDtitle Font14 FontW Block Lineheight35">收货人信息</span>
   <form action="updateAddress" method="post">
   <table>
    <tr><input type="hidden" name="userId" value="${userInfos.userId}"/></tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">收货地址：</td>
     <td><input type="text" name="userInfo.address" value="${userInfos.userInfo.address}" ></td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">收件人姓名：</td>
     <td><input type="text" name="userInfo.realName" required value="${userInfos.userInfo.realName}" class="input_name"></td>
    </tr>
   <%-- <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">街道地址：</td>
     <td><input type="text" required size="50" value="丈八路144号" class="input_addr"></td>
    </tr>--%>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">邮政编码：</td>
     <td><input type="text" name="userInfo.zip" required size="10" pattern="[0-9]{6}" value="${userInfos.userInfo.zip}" class="input_zipcode"></td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">手机号码：</td>
     <td><input type="text" name="userInfo.telephone" required pattern="[0-9]{11}" value="${userInfos.userInfo.telephone}" class="input_tel"></td>
    </tr>
    <tr>
     <td align="right"  width="30%" class="Font14 FontW Lineheight35"></td>
     <td class="Lineheight35"><input name="" type="submit" value="确认修改" class="Submit">
     <%-- <input name="" type="submit" value="删除" class="Submit"></td>--%>
    </tr>
   </table>
   </form>
  <%-- <!--add new address-->
   <form action="#">
    <table style="margin-top:10px;">
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">收货地址：</td>
   &lt;%&ndash;  <td>
     <select name="" class="select_ssq">
      <option>陕西省</option>
     </select>
     <select name="" class="select_ssq">
     <option>西安市</option>
     </select>
     <select name="" class="select_ssq">
     <option>莲湖区</option>
     </select>&ndash;%&gt;
     </td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">收件人姓名：</td>
     <td><input type="text" name="" required  class="input_name"></td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">街道地址：</td>
     <td><input type="text" required  class="input_addr"></td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">邮政编码：</td>
     <td><input type="text" required pattern="[0-9]{6}"  class="input_zipcode"></td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right">手机号码：</td>
     <td><input type="text" name="" required pattern="[0-9]{11}" class="input_tel"></td>
    </tr>
    <tr>
     <td width="30%" class="Font14 FontW Lineheight35" align="right"></td>
     <td class="Font14 Font Lineheight35"><input name="" type="submit" value="新增收货地址"  class="Submit"></td>
    </tr>
   </table>
   </form>--%>
  </section>
 </article>
</section>
<!--End content-->
<jsp:include page="../base/foot.jsp"></jsp:include>
</body>
</html>
