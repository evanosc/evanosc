<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>文章管理</title>
    <link rel="stylesheet" href="<%=path%>/static/common/bootstrap-table/bootstrap-table.css" />
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>文章列表</h5>
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
                            <div id="toolbar" class="btn-group m-t-sm">
                                <button type="button" class="btn btn-default"  title="创建文章" onclick="member_show('创建文章','<%=path%>/content/article/add',null,null,'1000',null)">
                                    <i class="glyphicon glyphicon-plus"></i>
                                </button>
                            </div>
                            <table id="table"
                                   data-toggle="table"
                                   data-height="500"
                                   data-search="true"
                                   data-show-refresh="true"
                                   data-show-toggle="true"
                                   data-show-pagination-switch="true"
                                   data-striped="true"
                                   data-pagination="true"
                                   data-sort-name="stargazers_count"
                                   data-sort-order="desc"
                                   data-toolbar="#toolbar">
                                <thead>
                                <tr>
                                    <th data-halign="center" data-align="center" data-sortable="true">所属类目</th>
                                    <th data-halign="center" data-align="center" data-sortable="true">文章名称</th>
                                    <%--<th data-halign="center" data-align="center" data-sortable="true">浏览数</th>
                                    <th data-halign="center" data-align="center" data-sortable="true">评论数</th>--%>
                                    <th data-halign="center" data-align="center" data-sortable="true">排序</th>
                                    <th data-halign="center" data-align="center">状态</th>
                                    <th data-halign="center" data-align="center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${contentArticles}" var="contentArticle">
                                    <tr>
                                        <td>
                                            <a href="javascript:void(0)" >${contentArticle.folderId}</a>
                                        </td>
                                        <td>${contentArticle.articleName}</td>
                                        <%--<td>${contentArticle.countView}</td>--%>
                                        <%--<td>${contentArticle.countComment}</td>--%>
                                        <td>${contentArticle.sort}</td>
                                        <td class="td-status">
                                            <c:if test="${contentArticle.status==1}"><span class="label label-primary">正常</span></c:if>
                                            <c:if test="${contentArticle.status==0}"><span class="label label-danger">冻结</span></c:if>
                                        </td>
                                        <td class="td-manage">
                                            <c:if test="${contentArticle.status==1}"><a class="like text-info" href="javascript:void(0)" onClick="member_stop(this,'<%=path%>/content/article/${contentArticle.id}/audit')" title="冻结"><i class="glyphicon glyphicon-pause"></i></a></c:if>
                                            <c:if test="${contentArticle.status==0}"><a class="like text-info" href="javascript:void(0)" onClick="member_start(this,'<%=path%>/content/article/${contentArticle.id}/audit')" title="启用"><i class="glyphicon glyphicon-play"></i></a></c:if>
                                            <a class="edit m-l-sm text-warning" href="javascript:void(0)" onclick="member_show('${appArticle.title}','<%=path%>/content/article/${contentArticle.id}/edit',null,null,'1000',null)" title="编辑">
                                                <i class="glyphicon glyphicon-edit"></i>
                                            </a>
                                            <a class="remove m-l-sm text-danger" href="javascript:void(0)" onclick="member_delete(this,'<%=path%>/content/article/${contentArticle.id}/delete','确认要删除该文章吗?')" title="删除">
                                                <i class="glyphicon glyphicon-remove"></i>
                                            </a>
                                        </td>
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


<!-- jquery-ui-->
<script src="<%=path%>/static/common/jquery/jquery-ui.min.js"></script>
<!-- Bootstrap table -->
<script src="<%=path%>/static/common/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/static/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
<script src="<%=path%>/static/common/bootstrap-table/tableExport.js"></script>
<script src="<%=path%>/static/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

</body>
</html>
