<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="<%=path%>/static/common/icheck/flat/green.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5><c:if test="${empty systemUser}">创建用户</c:if><c:if test="${!empty systemUser}">修改用户信息</c:if> <small>  菜单信息应当遵循合法、正当、必要的原则，明示目的、方式和范围。</small></h5>
                    <div class="ibox-tools">
                        <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        <a class="close-link"><i class="fa fa-times"></i></a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal">
                        <c:if test="${empty systemUser}">
                        <div class="form-group m-t">
                            <label class="col-sm-2 control-label">帐号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="systemUser.loginName" value="${systemUser.loginName }">
                            </div>
                            <label class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" name="systemUser.loginPassword" maxlength="18">
                            </div>
                        </div>
                        </c:if>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  <c:if test="${!empty systemUser}">m-t</c:if>">
                            <label class="col-sm-2 control-label">昵称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="systemUser.userName" value="${systemUser.userName}">
                            </div>
                            <label class="col-sm-2 control-label">真实姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" maxlength="11" class="form-control" name="systemUser.realName" value="${systemUser.realName}">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">电子邮箱：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="systemUser.email" value="${systemUser.email}">
                            </div>
                            <label class="col-sm-2 control-label">移动电话：</label>
                            <div class="col-sm-4">
                                <input type="text" maxlength="11" class="form-control" name="systemUser.telephone" value="${systemUser.telephone}">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline add-radio">
                                    <input type="radio"  name="systemUser.sex" value="1" <c:if test="${systemUser.sex==1 }">checked="checked"</c:if> >  男</label>
                                <label class="radio-inline add-radio">
                                    <input type="radio" name="systemUser.sex" value="2" <c:if test="${systemUser.sex==2 }">checked="checked"</c:if> >  女</label>
                                <c:if test="${empty systemUser}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="systemUser.sex" value="0" checked="checked">  保密</label>
                                </c:if>
                                <c:if test="${!empty systemUser}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="systemUser.sex" value="0" <c:if test="${systemUser.sex==0 }">checked="checked"</c:if> >  保密</label>
                                </c:if>
                            </div>
                            <label class="col-sm-2 control-label">状态：</label>
                            <div class="col-sm-4">
                                <c:if test="${empty systemUser}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="systemUser.status" value="1" checked="checked">  开启</label>
                                </c:if>
                                <c:if test="${!empty systemUser}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="systemUser.status" value="1" <c:if test="${systemUser.status==1 }">checked="checked"</c:if> >  开启</label>
                                </c:if>
                                <label class="radio-inline add-radio">
                                    <input type="radio" name="systemUser.status" value="0" <c:if test="${systemUser.status==0 }">checked="checked"</c:if> >  关闭</label>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">权限：</label>
                            <div class="col-sm-10">
                                <c:forEach items="${systemRoles }" var="systemRole">
                                    <div class="checkbox col-sm-3">
                                        <label>
                                            <input type="checkbox" name="roleId" value="${systemRole.roleId }" <c:forEach items="${systemRoleList}" var="systemRoleList"> <c:if test="${systemRole.roleId==systemRoleList.roleId }">checked="checked"</c:if></c:forEach>/>&nbsp&nbsp${systemRole.roleName}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-4 add-submit">
                                <input type="hidden" class="form-control" name="systemUser.userId" value="${systemUser.userId}">
                                <button class="btn btn-primary" type="button" id="user-submit">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- iCheck -->
<script src="<%=path%>/static/common/icheck/icheck.min.js"></script>
<!-- bootstrapvalidator前端验证框架 -->
<script src="<%=path%>/static/common/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<!-- 自定义js -->
<script type="text/javascript">
    // 多选框插件
    $(document).ready(function() {
        $('input').iCheck({
            checkboxClass : 'icheckbox_flat-green',
            radioClass : 'iradio_flat-green'
        });
    });

    $(function(){
        $("#user-submit").click(function() {
            var params = $("form").serialize();
            $.ajax({
                type : 'post',
                url : '<%=path%>/system/user/save',
                data : params,
                dataType : 'json',
                success : function(result) {
                    if (result.success == true) {
                        parent.layer.msg(result.message, {
                            shade : 0.3,
                            time : 1500
                        }, function() {
                            window.parent.location.reload(); // 刷新父页面
                        });
                    } else {
                        layer.msg(result.message, {
                            icon : 2,
                            time : 1000
                        });
                    }
                }
            })
        });
    })
</script>
</body>
</html>
