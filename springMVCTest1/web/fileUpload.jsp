<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传测试</title>
</head>
<body>
<div align="center">
    传统方式
    <form action="file/upload1" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传"/>
    </form>
</div>
<div align="center">
    springMVC方式
    <form action="file/upload2" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传"/>
    </form>
</div>
<div align="center">
    跨服务器方式
    <form action="file/upload3" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传"/>
    </form>
</div>
</body>
</html>
