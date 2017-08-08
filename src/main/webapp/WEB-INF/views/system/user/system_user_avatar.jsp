<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>修改头像</title>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>富头像上传编辑器</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="form_editors.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="form_editors.html#">选项1</a>
                            </li>
                            <li><a href="form_editors.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <ul class="nav nav-tabs" id="avatar-tab">
                        <li class="active" id="upload"><a href="javascript:;">本地上传</a>
                        </li>
                        <li id="webcam"><a href="javascript:;">视频拍照</a>
                        </li>
                        <li id="albums"><a href="javascript:;">相册选取</a>
                        </li>
                    </ul>
                    <div class="m-t m-b">
                        <div id="flash1">
                            <p id="swf1">本组件需要安装Flash Player后才可使用，请从<a href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。</p>
                        </div>
                        <div id="editorPanelButtons" style="display:none">
                            <p class="m-t">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="src_upload">是否上传原图片？</label>
                            </p>
                            <p>
                                <a href="javascript:;" class="btn btn-w-m btn-primary button_upload"><i class="fa fa-upload"></i> 上传</a>
                                <a href="javascript:;" class="btn btn-w-m btn-white button_cancel">取消</a>
                            </p>
                        </div>
                        <p id="webcamPanelButton" style="display:none">
                            <a href="javascript:;" class="btn btn-w-m btn-info button_shutter"><i class="fa fa-camera"></i> 拍照</a>
                        </p>
                        <div id="userAlbums" style="display:none">
                            <a href="img/a1.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a1.jpg" alt="示例图片1" />
                            </a>
                            <a href="img/a2.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a2.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a3.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a3.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a4.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a4.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a5.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a5.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a6.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a6.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a7.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a7.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a8.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a8.jpg" alt="示例图片2" />
                            </a>
                            <a href="img/a9.jpg" class="fancybox" title="选取该照片">
                                <img src="img/a9.jpg" alt="示例图片2" />
                            </a>>
                        </div>
                    </div>
                    <div class="alert alert-warning alert-dismissable m-t">
                        - 测试 <b>视频拍照</b> 功能时，请注意允许<code>flash</code>和<code>浏览器</code>使用摄像头，否则可能会无法拍照。
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- fullavatareditor -->
<script src="<%=path%>/static/common/fullavatareditor/scripts/swfobject.js"></script>
<script src="<%=path%>/static/common/fullavatareditor/scripts/fullAvatarEditor.js"></script>
<script src="<%=path%>/static/common/fullavatareditor/scripts/jQuery.Cookie.js"></script>
<script src="<%=path%>/static/common/fullavatareditor/scripts/test.js"></script>

</body>
</html>
