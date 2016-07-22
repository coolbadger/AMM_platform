<%--
  Created by IntelliJ IDEA.
  User: Badger
  Date: 16/7/22
  Time: 下午1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="taglib.jsp"></jsp:include>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>test</title>
    <!-- Bootstrap框架 -->
    <link rel="stylesheet" type="text/css" href="../static/frameworks/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../static/frameworks/bootstrap/css/bootstrap-table.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入）-->
    <link rel="stylesheet" type="text/css" href="../static/frameworks/bootstrap/css/bootstrap-theme.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../static/frameworks/bootstrap/js/jquery.min.js" type="text/javascript"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../static/frameworks/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- BootStrap-table文件，不可少-->
    <script src="../static/frameworks/bootstrap/js/bootstrap-table.js" type="text/javascript"></script>
    <!-- 国际化，表格汉化 -->
    <script src="../static/frameworks/bootstrap/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
    <script src="../static/frameworks/bootstrap/js/bootstrap-table-editable.js" type="text/javascript"></script>
</head>
<body>
<tmpl:overwrite name="body"></tmpl:overwrite>
</body>
</html>
