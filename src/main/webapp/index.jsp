<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <a href="/api/goAMMHomeUI" target="_blank">XXX农机管理信息系统</a>
    <a href="/api/goLogin">立即注册</a>
    <a href="/api/goUserInfo">框架页面</a>
    <a href="/api/goBootStrapUI">bootStrap测试页面</a>
    <a href="/api/goEasyUI">easyUI测试页面</a>
    <a href="/api/goHomeUI" target="_blank">首页框架</a>
  </body>
</html>
