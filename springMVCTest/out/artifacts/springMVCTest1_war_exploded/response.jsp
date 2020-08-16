<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>response</title>

    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                // alert("Hello btn");
                $.ajax({
                    // 编写json格式
                    url: "user/testAjax",
                    contentType: "application/json;charset=UTF-8",
                    data: '{"username":"妹妹","password":"lalal123","age":18}',
                    dataType:"json",
                    type:"POST",
                    success:function (data) {
                        // data 服务器端响应的json数据
                        alert(data);
                        alert(data.username)
                        alert(data.password)
                        alert(data.age)
                    }
                });
            });
        });
    </script>
</head>
<body>
<div align="center">
    <a href="user/testString">testString</a>
</div>
<div align="center">
    <a href="user/testVoid">testVoid</a>
</div>
<div align="center">
    <a href="user/testModelAndView">testModelAndView</a>
</div>
<div align="center">
    <a href="user/testForwardOrRedirect">testForwardOrRedirect</a>
</div>
<div align="center">
    <button id="btn">发送Ajax的请求</button>
</div>


</body>
</html>
