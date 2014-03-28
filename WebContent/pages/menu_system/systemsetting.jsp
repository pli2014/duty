<!DOCTYPE html>
<%@ include file="../commonHeader.jsp"%>
<%--
  User: peter
  Date: 14-3-18
  Time: 下午11:02
--%>
<html>
<head>
    <title>系统参数设定</title>
    <script type="text/javascript" src="jslib/flatlab/assets/ckeditor/ckeditor.js"></script>
    <script>
        jQuery(document).ready(function()
        {
            CKEDITOR.replace( 'systemSetting.introduction',{
                language:'zh-cn',//简体中文
                width : "100%", //宽度
                height:400,  //高度
                toolbar ://工具栏设置
                        [
                            ['Maximize','-','Save','NewPage','Preview','-','Templates'],
                            ['Cut','Copy','Paste','PasteText','PasteFromWord'],
                            ['Undo','Redo','-','Find','Replace','-',,'Table','HorizontalRule','-','SelectAll','RemoveFormat'],
                            ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
                            ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
                            ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
                            ['Link','Unlink','Anchor'],
                            ['Image','Flash','Smiley','SpecialChar','PageBreak'],
                            ['Styles','Format','Font','FontSize'],
                            ['TextColor','BGColor']
                        ]
            });
        });
    </script>
</head>
<body>
<!--main content start-->
<section class="panel">
    <header class="panel-heading">
        系统参数设定
    </header>
    <form role="form" method="post" class="form-horizontal tasi-form" action="backend/systemsettingsave.action">
        <%@ include file="../strutsMessage.jsp"%>
        <input type="hidden" name="systemSetting.id" value="${systemSetting.id}"/>
        <div class="form-group has-success">
            <label class="col-lg-2 control-label">百度地图序列号</label>
            <div class="col-lg-10">
                <input name="systemSetting.maptoken" type="text" class="form-control" value="${systemSetting.maptoken}"/>
            </div>
        </div>

        <div class="form-group has-success">
            <label class="control-label col-lg-2 col-sm-3">默认城市</label>
            <div class="col-lg-10">
                <input name="systemSetting.city" type="text" class="form-control" value="${systemSetting.city}"/>
            </div>
        </div>

        <div class="form-group has-success">
            <label class="col-sm-2 control-label col-sm-2">服务介绍</label>
            <div class="col-sm-10">
                <textarea class="form-control ckeditor" name="systemSetting.introduction" rows="20">${systemSetting.introduction}</textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button class="btn btn-danger" type="submit">保存</button>
            </div>
        </div>
    </form>
</section>
</body>
</html>
