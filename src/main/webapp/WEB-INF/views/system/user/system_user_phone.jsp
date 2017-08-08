<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>通讯录</title>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-4">
            <div class="contact-box">
                <a href="profile.html">
                    <div class="col-sm-4">
                        <div class="text-center">
                            <img alt="image" class="img-circle m-t-xs img-responsive" src="<%=path%>/static/img/a1.jpg">
                            <div class="m-t-xs font-bold">技术中心</div>
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <h3><strong>赖旭</strong></h3>
                        <p><i class="fa fa-map-marker"></i> 中国·北京</p>
                        <address>
                            E-mail:evanosc@163.com<br>
                            github:<a href="">https://github.com/evanosc</a><br>
                            <abbr title="Phone">Tel:</abbr><strong>13716069612</strong>
                        </address>
                    </div>
                    <div class="clearfix"></div>
                </a>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="contact-box">
                <a href="profile.html">
                    <div class="col-sm-4">
                        <div class="text-center">
                            <img alt="image" class="img-circle m-t-xs img-responsive" src="<%=path%>/static/img/a3.jpg">
                            <div class="m-t-xs font-bold">技术中心</div>
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <h3><strong>张一凡</strong></h3>
                        <p><i class="fa fa-map-marker"></i> 湖北·武汉</p>
                        <address>
                            E-mail:evanosc@163.com<br>
                            github:<a href="">https://github.com/evanosc</a><br>
                            <abbr title="Phone">Tel:</abbr><strong>13716069612</strong>
                        </address>
                    </div>
                    <div class="clearfix"></div>
                </a>
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
