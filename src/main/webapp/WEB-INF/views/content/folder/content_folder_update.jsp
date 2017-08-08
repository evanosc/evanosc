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
                    <h5><c:if test="${empty contentFolder}">创建类目</c:if><c:if test="${!empty contentFolder}">修改类目信息</c:if> <small>  类目信息应当遵循合法、正当、必要的原则，明示目的、方式和范围。</small></h5>
                    <div class="ibox-tools">
                        <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        <a class="close-link"><i class="fa fa-times"></i></a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="form-info" class="form-horizontal">
                        <div class="form-group m-t">
                            <label class="col-sm-2 control-label">类目名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="contentFolder.folderName" value="${contentFolder.folderName }">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">链接：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="contentFolder.path" value="${contentFolder.path }">
                            </div>
                            <label class="col-sm-2 control-label">排序：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="contentFolder.sort" value="${contentFolder.sort }">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="hr-line-dashed"></div>
                        <c:if test="${contentFolder.folderType!=0 }">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否显示：</label>
                                <div class="col-sm-10">
                                    <c:if test="${empty contentFolder}">
                                        <label class="radio-inline add-radio">
                                            <input type="radio" name="contentFolder.status" value="1" checked="checked">  开启</label>
                                    </c:if>
                                    <c:if test="${!empty contentFolder}">
                                        <label class="radio-inline add-radio">
                                            <input type="radio" name="contentFolder.status" value="1" <c:if test="${contentFolder.status==1 }">checked="checked"</c:if> >  开启</label>
                                    </c:if>
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentFolder.status" value="0" <c:if test="${contentFolder.status==0 }">checked="checked"</c:if> >  冻结</label>
                                    <label class="radio-inline add-radio"><strong>状态：</strong> “开启”代表此数据显示，“冻结”代表此数据隐藏</label>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                        </c:if>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注信息：</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="2" name="contentFolder.remarks" placeholder="请输入消息..." style="margin: 0px -17.675px 0px 0px; height: 50px; width: 600px;">${contentFolder.remarks}</textarea>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-4 add-submit">
                                <input type="hidden" class="form-control" name="contentFolder.folderId" value="${contentFolder.folderId}">
                                <c:if test="${empty contentFolder}">
                                    <input type="hidden" class="form-control" name="contentFolder.folderType" value="${empty parentMenu.menuType?1:parentMenu.menuType==1?2:0}">
                                    <input type="hidden" class="form-control" name="contentFolder.parentId" value='${empty parentMenu.menuId?1:parentMenu.menuId}'>
                                </c:if>
                                <input  class="btn btn-primary" type="submit" value="提交"/>
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
                'contentFolder.folderName': {
                    message: '类目名称验证失败',
                    validators: {
                        notEmpty: {
                            message: '类目名称不能为空'
                        }
                    }
                }

            }
        }).on('success.form.bv', function(e) {
            // 阻止默认事件提交
            e.preventDefault();
            save_info();
            console.log("提交成功");
        });
    });

    function save_info() {
            var params = $("form").serialize();
            $.ajax({
                type : 'post',
                url : '<%=path%>/content/folder/save',
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
    }

</script>
</body>
</html>
