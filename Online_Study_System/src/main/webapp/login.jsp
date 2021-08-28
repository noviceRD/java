<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>登录界面</title>
    <link rel="stylesheet" href="css/login_style.css" />
</head>
<body>
<div class="login_frame"></div>
<div class="LoginWindow">
    <div style="position: absolute;color: #fff;left: 84px;top: 15px;font-size: 18px;font-weight: bold;">大学计算机基础在线学习系统</div>
    <div>
        <form method="post" action="${ctx}/login" onsubmit="return user_input()" class="login">
            <p>
                <label for="name">帐号:</label>
                <input type="text" name="name" id="name" value="">
            </p>

            <p>
                <label for="password">密码:</label>
                <input type="password" name="password" id="password" value="">
            </p>

            <p class="login-submit">
                <button type="submit" class="login-button">登录</button>
            </p>

        </form>
    </div>
</div>
</body>
<script>
    function $(id){return document.getElementById(id)}

    function user_input(){
        var name = $("name").value;
        var password = $("password").value;
        if(name==""){
            alert("用户名不能为空！！");
            return false;
        }else if(password==""){
            alert("密码不能为空！！")
            return false;
        }else {
            return true;
        }
    }
</script>
</html>