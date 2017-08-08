<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一凡后台管理系统 - 登录</title>


    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h2 class="logo-name">evanosc</h2>
        </div>
        <h3>管理员登录</h3>

        <form class="m-t" role="form" action="index.html" id="loginForm">
            <div class="form-group">
                <input type="text" name="systemUser.loginName" placeholder="账号" class="form-control"/>
            </div>
            <div class="form-group">
                <input type="password" name="systemUser.loginPassword" placeholder="密码" class="form-control" />
            </div>
            <div class="form-group">
                <input type="text" id="J_codetext" name="registerCode" class="form-control" placeholder="验证码">
            </div>
            <div class="form-group">
                <img class="J_codeimg" src="<%=path%>/captcha.jpg" id="kaptchaImage" />
                &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="$(this).prev().click()">点击刷新</a>
            </div>
            <button type="button" class="btn btn-primary block full-width m-b" class="submit_btn" id="login-submit">登 录</button>
        </form>
    </div>
</div>

<!-- 自定义js -->
<script type="text/javascript">

    $(function(){
        $('#kaptchaImage').click(function () {
            $(this).attr('src', '<%=path%>/captcha.jpg?t=' + $.now());
        })

        $("#login-submit").click(function() {
            var loginName=$("input[name='systemUser.loginName']").val();
            if(loginName.length <=0) {
                $("input[name='systemUser.loginName']").attr("placeholder","请输入帐号");
                return false;
            }
            var loginPassword=$("input[name='systemUser.loginPassword']").val();
            if(loginPassword.length <=0) {
                $("input[name='systemUser.loginPassword']").attr("placeholder","请输入密码");
                return false;
            }
            var registerCode=$("input[name='registerCode']").val();
            if(registerCode.length <=0) {
                $("input[name='registerCode']").attr("placeholder","输入验证码");
                return false;
            }
            $.ajax({
                type : 'post',
                url : '<%=path%>/system/login',
                data: {"username":loginName,"password":loginPassword,"captcha":registerCode},
                dataType : 'json',
                success : function(result) {
                    if(result.success==false){
                        if(result.message=="验证码错误"){
                            $("input[name='registerCode']").val("");
                            $("input[name='registerCode']").attr("placeholder","验证码错误");
                        }else if(result.message=="密码错误"){
                            $("input[name='systemUser.loginPassword']").val("");
                            $("input[name='systemUser.loginPassword']").attr("placeholder","密码错误");
                        }else{
                            layer.alert(result.message, {icon: 2});
                        }
                    }else{
                        window.location.href='<%=path%>/system/main';
                    }
                }
            })
        });

        /**
         * 回车登录实现
         */
        $(document).keyup(function(event){
            if(event.keyCode ==13){
                $("#login-submit").trigger("click");
            }
        });
    });

</script>

</body>

</html>
