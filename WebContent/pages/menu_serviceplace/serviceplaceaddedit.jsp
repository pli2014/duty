<!DOCTYPE html>
<%@ include file="../commonHeader.jsp"%>
<html lang="en">
<html>
<head>
    <title>服务地点</title>
</head>
<body>
<link href="jslib/flatlab/assets/bootstrap-colorpicker/css/colorpicker.css" rel="stylesheet"/>
<script src="jslib/flatlab/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
        <!--main content start-->
        <section class="panel">
            <header class="panel-heading">
                服务地点管理
            </header>
            <%@include file="../strutsMessage.jsp"%>
            <form role="form" method="post" class="form-horizontal tasi-form" onsubmit="return checkForm()" action="backend/serviceplace/serviceplacesubmit.action">
                <div class="form-group has-success">
                    <label class="col-lg-2 control-label">地点编码</label>
                    <div class="col-lg-10">
                        <input name="servicePlace.code" type="text" class="form-control" value="${servicePlace.code}"/>
                    </div>
                </div>
                <div class="form-group has-success">
                    <label class="col-lg-2 control-label">地点名称</label>
                    <div class="col-lg-10">
                        <input name="servicePlace.id" type="hidden" value="${servicePlace.id}"/>
                        <input name="servicePlace.type" type="hidden" value="${type}"/>
                        <input name="type" type="hidden" value="${type}"/>
                        <input id="servicePlacename" name="servicePlace.name" type="text" class="form-control" value="${servicePlace.name}"/>
                    </div>
                </div>
                <s:if test="#request.type==0">
                <div class="form-group has-success">
                    <label class="col-lg-2 control-label">地点颜色编码</label>
                    <div class="col-lg-3">
                    <input style="background-color:${servicePlace.color}" id="servicePlacecolor" name="servicePlace.color" readonly="true" type="text" class="form-control" value="${servicePlace.color}"/>
                        <script>
                            //jQuery('#servicePlacecode').colorpicker();
                            jQuery('#servicePlacecolor').colorpicker().on('changeColor', function(ev){
                                jQuery(this).css({backgroundColor:ev.color.toHex()});
                            });
                        </script>
                    </div>
                </div>
                </s:if>

                <s:if test="#request.type==1">
                    <div class="form-group has-success">
                        <label class="control-label col-lg-2 col-sm-3">地理坐标</label>
                        <div class="col-lg-10">
                            经度：<s:textfield id="servicePlacelongitude" name="servicePlace.longitude" readonly="true"></s:textfield>
                            纬度：<s:textfield id="servicePlacelatitude" name="servicePlace.latitude" readonly="true"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group has-success">
                        <label class="control-label col-lg-2 col-sm-3">选择地图位置</label>
                        <div class="col-lg-10">
                            <label style="color:green">请在上面地点名称中输入你想要的地理位置，然后点击"找位置"按钮;同样可以在地图上拖动跳动的位置然后点击“坐标更新”</label>
                            <br>
                            <input type="button" class="btn btn-success" name="" value="找位置" onclick="mapPosition(jQuery('#servicePlacename').val(),true, callBackFunction)">
                            <input type="button" class="btn btn-success" name="" value="坐标更新" onclick="refreshPosition(callBackFunction)">
                            <br>
                            <br>
                            <style type="text/css">
                                #allmap {width:80%;height:500px;overflow: hidden;margin:0;}
                            </style>
                            <%@ include file="foundpositionmap.jsp"%>
                            <script type="text/javascript">

                                jQuery(document).ready(function(){
                                    var longitude = jQuery("#servicePlacelongitude").val();
                                    var latitude = jQuery("#servicePlacelatitude").val();
                                    initializePosition(jQuery("#servicePlacename").val(),longitude,latitude);
                                });

                                function callBackFunction(point){
                                    jQuery("#servicePlacelongitude").val(point.lng);
                                    jQuery("#servicePlacelatitude").val(point.lat);
                                }
                                function checkForm(){
                                    var longitude = jQuery("#servicePlacelongitude").val();
                                    var latitude = jQuery("#servicePlacelatitude").val();
                                    if(longitude=="" || latitude==""){
                                        jQuery("#dialog_message").html('<span style="color:red">请确保经度和纬度具备合法的地理坐标。</span>');
                                        jQuery("#dialog_message").dialog();
                                        return false;
                                    }
                                    //自动更新位置坐标
                                    refreshPosition(callBackFunction);
                                    return true;
                                }
                            </script>
                        </div>
                    </div>
                </s:if>

                <div class="form-group has-success">
                    <label class="control-label col-lg-2 col-sm-3">地点描述</label>
                    <div class="col-lg-10">
                        <s:textarea name="servicePlace.description" cols="70" rows="10"></s:textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-danger" type="submit">保存</button>
                        <button class="btn btn-danger" type="button" onclick="window.location.href='backend/serviceplace/index.action'">取消</button>
                    </div>
                </div>
            </form>
        </section>
</body>
</html>
