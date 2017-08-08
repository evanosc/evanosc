<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>${systemUser.loginName}</title>
    <link rel="stylesheet" href="<%=path%>/static/common/bootstrap-table/bootstrap-table.css" />
    <link rel="stylesheet" href="<%=path%>/static/common/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" href="<%=path%>/static/common/icheck/flat/green.css" />
  </head>
  
  <body class="system-message gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight system-message">
        <div class="row">
            <div class="col-sm-5">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>管理员信息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            <a class="close-link"><i class="fa fa-times"></i></a>
                        </div>
                    </div>
                    <div class="ibox-content form-horizontal" id="system-info">
                    	<form id="form-info">
                           <div class="form-group">
                                <label class="col-sm-3 control-label">昵称：</label>
                                <div class="col-sm-6">
                                    <input name="systemUser.userName" type="text" class="form-control form-disabled" disabled="disabled" value="${systemUser.userName}">
                                </div>
                                <div class="col-sm-3"><button type="button" class="btn btn-warning pull-right" onclick="change_Password();">修改密码</button></div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-6">
                                    <input name="systemUser.realName" type="text" class="form-control form-disabled" disabled="disabled" value="${systemUser.realName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-6">
                                    <input id="sex"  type="text" class="form-control" disabled="disabled" value="<c:if test="${systemUser.sex==1 }">男</c:if><c:if test="${systemUser.sex==2 }">女</c:if><c:if test="${systemUser.sex==0 }">保密</c:if>">
                                    <label class="radio-inline add-radio" >
                                        <input type="radio"  name="systemUser.sex" value="1" <c:if test="${systemUser.sex==1 }">checked="checked"</c:if> >  男</label>
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="systemUser.sex" value="2" <c:if test="${systemUser.sex==2 }">checked="checked"</c:if> >  女</label>
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="systemUser.sex" value="0" <c:if test="${systemUser.sex==0 }">checked="checked"</c:if> >  保密</label>
                                </div> 
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">年龄：</label>
                                <div class="col-sm-6">
                                    <input name="systemUser.age" type="text" class="form-control form-disabled" disabled="disabled" value="${systemUser.age}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">移动电话：</label>
                                <div class="col-sm-6">
                                     <input name="systemUser.telephone" type="text" class="form-control form-disabled" disabled="disabled" value="${systemUser.telephone}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label ">电子邮箱：</label>
                                <div class="col-sm-6 stm-inp">
                                     <input name="systemUser.email" type="text" class="form-control form-disabled" disabled="disabled" value="${systemUser.email}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">权限：</label>
                                <div class="col-sm-6">
                                     <input type="text" class="form-control disabled-form-control" disabled="disabled" value="${userRole }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">注册时间：</label>
                                <div class="col-sm-6">
                                     <input type="text" class="form-control disabled-form-control" disabled="disabled" value="<fmt:formatDate value="${systemUser.createTime}" pattern="yyyy/MM/dd HH:mm" />">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                	<button type="button" class="btn btn-primary pull-left m-r-md" onclick="modify();">修改信息</button>
                                    <button type="submit" class="btn btn-success" onclick="save_info();">保存修改</button>
                                </div>
                            </div>
                    	</form>
                    </div>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>管理员登陆记录</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            <a class="close-link"><i class="fa fa-times"></i></a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row row-lg">
                            <div class="col-sm-12">
                                <div class="example-wrap">
                                    <div class="example">
                                        <table id="table"
                                           data-toggle="table"
                                           data-height="450"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-toggle="true"
                                           data-show-export="true"
                                           data-show-pagination-switch="true"
                                           data-striped="true"
                                           data-pagination="true"
                                           data-sort-name="stargazers_count"
                                           data-sort-order="desc">
                                        <thead>
                                        <tr>
                                            <th data-halign="center" data-align="center" data-sortable="true">登录时间</th>
                                            <th data-halign="center" data-align="center" data-sortable="true">登录IP</th>
                                            <th data-halign="center" data-align="center" data-sortable="true">操作系统</th>
                                            <th data-halign="center" data-align="center" data-sortable="true">游览器</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${systemUserLoginLogList}" var="systemUserLoginLogList">
                                        <tr>
                                            <td><fmt:formatDate value="${systemUserLoginLogList.loginTime}" pattern="yyyy/MM/dd HH:mm" /></td>
                                            <td>${systemUserLoginLogList.userIp}</td>
                                            <td>${systemUserLoginLogList.operatingSystem}</td>
                                            <td>${systemUserLoginLogList.browser}</td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>			
    </div>
    <div class="ibox-content form-horizontal change_Pass_style" id="change_Pass">
        <div class="form-group">
            <label class="col-sm-4 control-label">原密码：</label>
            <div class="col-sm-6">
                <input name="nowPassword" type="password" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">新密码：</label>
            <div class="col-sm-6">
                <input name="newPassword" type="password" class="form-control" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">确认密码：</label>
            <div class="col-sm-6">
                 <input name="confirmPwd" type="password" class="form-control">
            </div>
        </div>
    </div>
    <!-- bootstrapvalidator-master前端验证框架 -->
	<script src="<%=path%>/static/common/bootstrap-validator/js/bootstrapValidator.min.js"></script>
    <!-- Bootstrap table -->
    <script src="<%=path%>/static/common/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="<%=path%>/static/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
    <script src="<%=path%>/static/common/bootstrap-table/tableExport.js"></script>
    <script src="<%=path%>/static/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- Data picker -->
    <script src="<%=path%>/static/common/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <!-- iCheck -->
	<script src="<%=path%>/static/common/icheck/icheck.min.js"></script>
    <!-- 自定义js -->
    <script type="text/javascript">
        /*多选按钮插件*/
        $(document).ready(function(){
            $('input').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });
        });

        //表单验证
        $(function(){
            $('#form-info').bootstrapValidator({
                container: 'tooltip',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    'systemUser.userName': {
                        message: '用户名验证失败',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    'systemUser.realName': {
                        message: '真实姓名验证失败',
                        validators: {
                            notEmpty: {
                                message: '真实姓名不能为空'
                            }
                        }
                    },
                    'systemUser.telephone': {
                        validators: {
                            notEmpty: {
                                message: '移动电话不能为空'
                            },
                            regexp: {
                                regexp: /^1[3|4|5|7|8]\d{9}$/,
                                message: '手机号码格式不正确'
                            }
                        }
                    },
                    'systemUser.email': {
                        validators: {
                            notEmpty: {
                                message: '电子邮箱不能为空'
                            },
                            regexp: {
                                regexp: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
                                message: '电子邮箱格式不正确'
                            }
                        }
                    },
                    'systemUser.sex': {
                        validators: {
                            notEmpty: {
                                message: '年龄不能为空'
                            },
                            regexp: {
                                regexp: /^(?:[1-9][0-9]?|1[01][0-9]|120)$/,
                                message: '年龄格式不正确'
                            }
                        }
                    }
                }
            })
        })

        //按钮点击事件
        /*修改信息*/
        function modify(){
            $('.form-disabled').attr("disabled", false);
            $('#sex').hide();
            $('.add-radio').show();
            $('.form-group').find('.btn-success').css({'display':'block'});
        };
        /*保存信息*/
        function save_info(){
            var params='';
            $("#system-info input").each(function(){
                params+=$(this).serialize()+"&";
            });
            $.ajax({
                data:params,
                dataType:"json",
                type:"post",
                url: '<%=path%>/system/user/info/edit',
                success:function(result){
                    if(result.success == true){
                        $('.form-disabled').attr("disabled", true);
                        $('#sex').show();
                        $('.add-radio').hide();
                        $('.form-group').find('.btn-success').css({'display':'none'});
                    }else{
                        layer.alert(result.message,{
                            title: '提示框',
                            icon:0,
                        });
                        return false;
                    }
                }
            })
        };

        /*修改密码*/
        function change_Password(){
            layer.open({
                type: 1,
                title:'修改密码',
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '295px'], //宽高
                content: $('#change_Pass'),
                btn:['确认修改'],
                yes:function(index, layero){
                    if ($("input[name='nowPassword']").val()==""){
                        layer.alert('原密码不能为空!',{
                            title: '提示框',
                            icon:0,
                        });
                        return false;
                    }
                    if ($("input[name='newPassword']").val()==""){
                        layer.alert('新密码不能为空!',{
                            title: '提示框',
                            icon:0,
                        });
                        return false;
                    }
                    if ($("input[name='confirmPwd']").val()==""){
                        layer.alert('确认新密码不能为空!',{
                            title: '提示框',
                            icon:0,
                        });
                        return false;
                    }
                    if($("input[name='confirmPwd']").val() != $("input[name='newPassword']").val()){
                        layer.alert('密码不一致!',{
                            title: '提示框',
                            icon:0,
                        });
                        return false;
                    }
                    var params='';
                    $("#change_Pass input").each(function(){
                        params+=$(this).serialize()+"&";
                    });
                    $.ajax({
                        data:params,
                        dataType:"json",
                        type:"post",
                        url: '<%=path%>/system/user/info/edit/password',
                        success:function(result){
                            if(result.success == true){
                                layer.alert(result.message,{
                                    title: '提示框',
                                    icon:1,
                                });
                                layer.close(index);
                                $("input[name='confirmPwd']").val("");
                                $("input[name='newPassword']").val("");
                                $("input[name='nowPassword']").val("");
                            }else{
                                layer.alert(result.message,{
                                    title: '提示框',
                                    icon:0,
                                });
                                return false;
                            }
                        }
                    })
                }
            });
        }
    </script>
  </body>
</html>
