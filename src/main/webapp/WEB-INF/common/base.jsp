<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<link rel="shortcut icon" href="<%=path%>/favicon.ico">
<link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/static/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=path%>/static/css/animate.css" rel="stylesheet">
<link href="<%=path%>/static/css/style.css" rel="stylesheet">

<!-- 全局js -->
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=path%>/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=path%>/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.min.js"></script>
<!-- 自定义js -->
<script src="<%=path%>/static/js/contabs.js"></script>
<script src="<%=path%>/static/js/content.js"></script>
<script src="<%=path%>/static/js/hplus.js"></script>
<script src="<%=path%>/static/js/pace.min.js"></script>