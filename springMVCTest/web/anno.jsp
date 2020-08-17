<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用注解</title>
</head>
<body>
<div align="center">
    <a href="anno/testRequestParam?username=哈哈">RequestParam</a>
</div>
<hr/>
<div align="center">
    RequestBody注解测试 获取请求体
    <form action="anno/testRequestBody" method="post">
        用户姓名：
        <label>
            <input type="text" name="username"/>
        </label><br/>
        用户年龄：
        <label>
            <input type="text" name="age"/>
        </label><br/>
        <input type="submit" value="提交"/>
    </form>
</div>

<hr/>

<div align="center">
    <a href="anno/testPathVariable/10">testPathVariable</a>
</div>

<hr/>

<div align="center">
    <a href="anno/testRequestHeader">testRequestHeader</a>
</div>

<hr/>

<div align="center">
    <a href="anno/testCookieValue">testCookieValue</a>
</div>

<hr/>
<div align="center">
    ModelAttribute注解测试 获取请求体
    <form action="anno/testModelAttribute" method="post">
        用户姓名：
        <label>
            <input type="text" name="uname"/>
        </label><br/>
        用户年龄：
        <label>
            <input type="text" name="age"/>
        </label><br/>
        <input type="submit" value="提交"/>
    </form>
</div>

<hr/>

<div align="center">
    <a href="anno/testSessionAttributes">testSessionAttributes</a>
</div>
<div align="center">
    <a href="anno/getSessionAttributes">getSessionAttributes</a>
</div>
<div align="center">
    <a href="anno/removeSessionAttributes">removeSessionAttributes</a>
</div>
</body>
</html>
