<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="<%=path%>/static/common/icheck/flat/green.css" />
    <link rel="stylesheet" href="<%=path%>/static/common/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5><c:if test="${empty contentArticle}">创建文章</c:if><c:if test="${!empty contentArticle}">修改文章信息</c:if> <small>  文章信息应当遵循合法、正当、必要的原则，明示目的、方式和范围。</small></h5>
                    <div class="ibox-tools">
                        <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        <a class="close-link"><i class="fa fa-times"></i></a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal">
                        <div class="form-group m-t">
                            <label class="col-sm-2 control-label">文章名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="contentArticle.articleName" value="${contentArticle.articleName }">
                            </div>
                            <label class="col-sm-2 control-label">所属类目：</label>
                            <div class="col-sm-4">
                                <select class="form-control m-b" name="contentArticle.folderId">
                                    <c:forEach items="${contentFolders}" var="contentFolder">
                                        <option value="${contentFolder.folderId}">${contentFolder.folderName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">文章内容：</label>
                            <div class="col-sm-10">
                                <textarea id="articleContent" name="contentArticle.articleContent">${contentArticle.articleContent}</textarea>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">发布者：</label>
                            <div class="col-sm-4">
                                <input type="text"  class="form-control" name="contentArticle.publishUser" value="${contentArticle.publishUser }">
                            </div>
                           <%-- <label class="col-sm-2 control-label">发布时间：</label>
                            <div class="col-sm-4">
                                <input type="text"  class="form-control datetimepicker"  name="contentArticle.publishTime" value="${contentArticle.publishTime}">
                            </div>--%>
                        </div>
                        <%--<div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否评论：</label>
                            <div class="col-sm-4">
                                <c:if test="${empty contentArticle}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentArticle.isComment" value="1" checked="checked">  是</label>
                                </c:if>
                                <c:if test="${!empty contentArticle}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentArticle.isComment" value="1" <c:if test="${contentArticle.isComment==1 }">checked="checked"</c:if> >  是</label>
                                </c:if>
                                <label class="radio-inline add-radio">
                                    <input type="radio" name="contentArticle.isComment" value="0" <c:if test="${contentArticle.isComment==0 }">checked="checked"</c:if> >  否</label>
                            </div>
                            <label class="col-sm-2 control-label">是否推荐：</label>
                            <div class="col-sm-4">
                                <c:if test="${empty contentArticle}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentArticle.isRecommend" value="1" checked="checked">  是</label>
                                </c:if>
                                <c:if test="${!empty contentArticle}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentArticle.isRecommend" value="1" <c:if test="${contentArticle.isRecommend==1 }">checked="checked"</c:if> >  是</label>
                                </c:if>
                                <label class="radio-inline add-radio">
                                    <input type="radio" name="contentArticle.isRecommend" value="0" <c:if test="${contentArticle.isRecommend==0 }">checked="checked"</c:if> >  否</label>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开始时间：</label>
                            <div class="col-sm-4">
                                <input type="text"  class="form-control datetimepicker" name="contentArticle.startTime" value="${contentArticle.startTime }">
                            </div>
                            <label class="col-sm-2 control-label">结束时间：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control datetimepicker" name="contentArticle.endTime" value="${contentArticle.endTime }">
                            </div>
                        </div>--%>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片：</label>
                            <div class="col-sm-4">
                                <%--<div class="input-group m-b">
                                    	<span class="input-group-btn">
                                            <button type="button" class="btn btn-primary" onclick="member_show('菜单图标','<%=path%>/system/menu/icon',null,null,'800',null)" title="图标">选择</button>
                                        </span>
                                    <input type="text" class="form-control" name="contentArticle.icon" value="${contentArticle.icon }">
                                </div>--%>
                            </div>
                            <label class="col-sm-2 control-label">排序：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="contentArticle.sort" value="${contentArticle.sort }">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否显示：</label>
                            <div class="col-sm-10">
                                <c:if test="${empty contentArticle}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentArticle.status" value="1" checked="checked">  开启</label>
                                </c:if>
                                <c:if test="${!empty contentArticle}">
                                    <label class="radio-inline add-radio">
                                        <input type="radio" name="contentArticle.status" value="1" <c:if test="${contentArticle.status==1 }">checked="checked"</c:if> >  开启</label>
                                </c:if>
                                <label class="radio-inline add-radio">
                                    <input type="radio" name="contentArticle.status" value="0" <c:if test="${contentArticle.status==0 }">checked="checked"</c:if> >  冻结</label>
                                <label class="radio-inline add-radio"><strong>状态：</strong> “开启”代表此数据显示，“冻结”代表此数据隐藏</label>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注信息：</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="2" name="contentArticle.remarks" placeholder="请输入消息..." style="margin: 0px -17.675px 0px 0px; height: 50px; width: 600px;">${contentArticle.remarks}</textarea>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-4 add-submit">
                                <input type="hidden" class="form-control" name="contentArticle.articleId" value="${contentArticle.articleId}">
                                <button class="btn btn-primary" type="button" id="article-submit">提交</button>
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
<!-- DataTime picker -->
<script src="<%=path%>/static/common/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- ckeditor -->
<script type="text/javascript" src="<%=path%>/static/common/ckeditor/ckeditor.js"></script>
<!-- 自定义js -->
<script type="text/javascript">
    //文本编辑器插件
    CKEDITOR.replace( 'articleContent' );

    //时间选择插件
    $('.datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss'
    });

    // 多选框插件
    $(document).ready(function() {
        $('input').iCheck({
            checkboxClass : 'icheckbox_flat-green',
            radioClass : 'iradio_flat-green'
        });
    });

    //提交表单
    $(function(){
        $("#article-submit").click(function() {
            var params = $("form").serialize();
            alert(params);
            $.ajax({
                type : 'post',
                url : '<%=path%>/content/article/save',
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
