<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求参数的绑定</title>
</head>
<body>
<div align="center">
    <a href="param/testParam?username=hehe&password=123">请求参数绑定</a>
</div>

<hr/>

<div align="center">
    把数据封装到Account类中，类中有User类
    <form action="param/saveAccount" method="post">
        姓名：
        <label>
            <input type="text" name="username"/>
        </label><br/>
        密码：
        <label>
            <input type="password" name="password"/>
        </label><br/>
        金额：
        <label>
            <input type="text" name="money"/>
        </label><br/>
        用户姓名：
        <label>
            <input type="text" name="user.uname"/>
        </label><br/>
        用户年龄：
        <label>
            <input type="text" name="user.age"/>
        </label><br/>
        <input type="submit" value="提交"/>
    </form>
</div>

<hr/>

<div align="center">
    把数据封装到Account1类中，类中存在list和map集合
    <form action="param/saveAccount1" method="post">
        姓名：
        <label><input type="text" name="username"/></label><br/>
        密码：
        <label><input type="password" name="password"/></label><br/>
        金额：
        <label><input type="text" name="money"/></label><br/>

        用户姓名：
        <label>
            <input type="text" name="list[0].uname"/>
        </label><br/>
        用户年龄：
        <label>
            <input type="text" name="list[0].age"/>
        </label><br/>

        用户姓名：
        <label>
            <input type="text" name="map['one'].uname"/>
        </label><br/>
        用户年龄：
        <label>
            <input type="text" name="map['one'].age"/>
        </label><br/>
        <input type="submit" value="提交"/>
    </form>
</div>

<hr/>
<div align="center">
    自定义类型转换器
    <form action="param/saveUser" method="post">
        用户姓名：
        <label>
            <input type="text" name="uname"/>
        </label><br/>
        用户年龄：
        <label>
            <input type="text" name="age"/>
        </label><br/>
        用户生日：
        <label>
            <input type="text" name="date"/>
        </label><br/>
        <input type="submit" value="提交"/>
    </form>
</div>
<hr/>

<div align="center">
    <a href="param/testServlet">servlet原生的API</a>
</div>
</body>
</html>
