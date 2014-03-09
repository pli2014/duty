<html>

    <head>

        <%@ page trimDirectiveWhitespaces="true" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%-- Listing of all the taglibs that we reference in this application. --%>
        <%@ taglib uri="/struts-tags" prefix="s" %>
        <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
        <%-- Force the pages to not be cached, so that they are always reloaded. --%>
        <%  response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        %>
        <base href="<%=basePath%>">

        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
        <meta content="Wit Peter Dong" name="author">

        <link rel="stylesheet" href="jslib/flatlab/assets/metro-ui/css/metro-bootstrap.css">
        <link rel="stylesheet" href="jslib/flatlab/assets/metro-ui/css/metro-bootstrap-responsive.css">
        <%--<link rel="stylesheet" href="jslib/flatlab/assets/metro-ui/css/iconFont.css">--%>

        <%--<link rel="stylesheet" href="jslib/flatlab/assets/chart-master/docs/prettify.css">--%>
        <%--<link rel="stylesheet" href="html/css/iconFont.css">--%>

        <!-- Load JavaScript Libraries -->
        <script src="jslib/flatlab/assets/jcrop/js/jquery.min.js"></script>
        <%--<script src="jslib/flatlab/assets/chart-master/docs/prettify.js"></script>--%>



        <script src="jslib/flatlab/js/jquery.js"></script>
        <script src="html/js/jquery.widget.min.js"></script>
        <script src="jslib/flatlab/assets/metro-ui/min/metro.min.js"></script>

        <title>志愿者管理系统</title>

    </head>
    <body class="metro" style="background-color: #efeae3">
        <div class="">
            <div style="height: 400px;">

                <div class="container">
                    <div class="grid fluid">
                        <div class="row">
                            <div class="span12">
                                <div class="carousel" id="carousel1">
                                    <div class="slide" style="background-color: #FF00A0;">
                                        <img src="" class="cover1" />
                                    </div>

                                    <div class="slide" style="background-color: #A0A0A0;">
                                        <img src="" class="cover1" />
                                    </div>

                                    <div class="slide" style="background-color: green;">
                                        <img src="" class="cover1"/>
                                    </div>

                                    <a class="controls left"><i class="icon-arrow-left-3"></i></a>
                                    <a class="controls right"><i class="icon-arrow-right-3"></i></a>
                                </div>
                                <script>
                                    $(function(){
                                        $("#carousel1").carousel({
                                            height: 300,
                                            effect: 'fade',
                                            markers: {
                                                show: true,
                                                type: 'square',
                                                position: 'bottom-right'
                                            }
                                        });
                                    })
                                </script>
                            </div>
                        </div>
                        <div class="row">
                            <div class="span4 bg-green padding20 text-center">
                                <h2 class="fg-white">我要登录</h2>
                            </div>
                            <div class="span4 bg-red padding20 text-center">
                                <h2 class="fg-white">我要注册</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>