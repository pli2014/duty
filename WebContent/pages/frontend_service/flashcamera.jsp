<!DOCTYPE html>
<%@ include file="../metrouiHeader.jsp" %>
<html>

<head>

    <title>志愿者服务</title>
    <style>
        body {
            background: url('img/zhiyuanzhebackgroud.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            position: relative;
            overflow: hidden;
        }
    </style>

    <style type="text/css">

        #webcam, #canvas {
            border: 20px solid #333;
            background: #eee;
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
        }

        #webcam {
            position: relative;
            margin-top: 50px;
            margin-bottom: 50px;
        }

        #webcam > span {
            z-index: 2;
            position: absolute;
            color: #eee;
            font-size: 10px;
            bottom: -16px;
            left: 152px;
        }

        #webcam > img {
            z-index: 1;
            position: absolute;
            border: 0px none;
            padding: 0px;
            bottom: -40px;
            left: 89px;
        }

        #webcam > div {
            border: 5px solid #333;
            position: absolute;
            right: -90px;
            padding: 5px;
            -webkit-border-radius: 8px;
            -moz-border-radius: 8px;
            border-radius: 8px;
            cursor: pointer;
        }

        #webcam a {
            background: #fff;
            font-weight: bold;
        }

        #webcam a > img {
            border: 0px none;
        }

        #canvas {
            border: 20px solid #ccc;
            background: #eee;
        }

        #flash {
            position: absolute;
            top: 0px;
            left: 0px;
            z-index: 5000;
            width: 100%;
            height: 100%;
            background-color: #fff404;
            display: none;
        }

        object {
            display: block; /* HTML5 fix */
            position: relative;
            z-index: 1000;
        }

    </style>
    <script src="jslib/jquery-webcam-master/jquery.webcam.js"></script>
</head>

<body class="metro">
<div id="bar" class="tile-area">
    <div id="webcam" style="display: inline-block">
        <img src="img/antenna.png" alt=""/>
        <div class="icon icon-camera" style="width: 50px;height: 50px"><a href="javascript:webcam.capture();void(0);">志愿者拍照</a></div>
    </div>

    <div style="display: inline-block">
        <canvas id="canvas" height="240" width="320"></canvas>
    </div>
    <div id="flash"></div>

    <script type="text/javascript">

        var pos = 0;
        var ctx = null;
        var image = null;

        var filter_on = false;
        var filter_id = 0;

        function changeFilter() {
            if (filter_on) {
                filter_id = (filter_id + 1) & 7;
            }
        }

        function toggleFilter(obj) {
            if (filter_on = !filter_on) {
                obj.parentNode.style.borderColor = "#c00";
            } else {
                obj.parentNode.style.borderColor = "#333";
            }
        }

        jQuery("#webcam").webcam({
            width: 320,
            height: 240,
            mode: "callback",
            swffile: "jslib/jquery-webcam-master/jscam_canvas_only.swf",

            onTick: function (remain) {

                if (0 == remain) {
                    jQuery("#status").text("Cheese!");
                } else {
                    jQuery("#status").text(remain + " seconds remaining...");
                }
            },

            onSave: function (data) {

                var col = data.split(";");
                var img = image;

                if (false == filter_on) {

                    for (var i = 0; i < 320; i++) {
                        var tmp = parseInt(col[i]);
                        img.data[pos + 0] = (tmp >> 16) & 0xff;
                        img.data[pos + 1] = (tmp >> 8) & 0xff;
                        img.data[pos + 2] = tmp & 0xff;
                        img.data[pos + 3] = 0xff;
                        pos += 4;
                    }

                } else {

                    var id = filter_id;
                    var r, g, b;
                    var r1 = Math.floor(Math.random() * 255);
                    var r2 = Math.floor(Math.random() * 255);
                    var r3 = Math.floor(Math.random() * 255);

                    for (var i = 0; i < 320; i++) {
                        var tmp = parseInt(col[i]);

                        /* Copied some xcolor methods here to be faster than calling all methods inside of xcolor and to not serve complete library with every req */

                        if (id == 0) {
                            r = (tmp >> 16) & 0xff;
                            g = 0xff;
                            b = 0xff;
                        } else if (id == 1) {
                            r = 0xff;
                            g = (tmp >> 8) & 0xff;
                            b = 0xff;
                        } else if (id == 2) {
                            r = 0xff;
                            g = 0xff;
                            b = tmp & 0xff;
                        } else if (id == 3) {
                            r = 0xff ^ ((tmp >> 16) & 0xff);
                            g = 0xff ^ ((tmp >> 8) & 0xff);
                            b = 0xff ^ (tmp & 0xff);
                        } else if (id == 4) {

                            r = (tmp >> 16) & 0xff;
                            g = (tmp >> 8) & 0xff;
                            b = tmp & 0xff;
                            var v = Math.min(Math.floor(.35 + 13 * (r + g + b) / 60), 255);
                            r = v;
                            g = v;
                            b = v;
                        } else if (id == 5) {
                            r = (tmp >> 16) & 0xff;
                            g = (tmp >> 8) & 0xff;
                            b = tmp & 0xff;
                            if ((r += 32) < 0) r = 0;
                            if ((g += 32) < 0) g = 0;
                            if ((b += 32) < 0) b = 0;
                        } else if (id == 6) {
                            r = (tmp >> 16) & 0xff;
                            g = (tmp >> 8) & 0xff;
                            b = tmp & 0xff;
                            if ((r -= 32) < 0) r = 0;
                            if ((g -= 32) < 0) g = 0;
                            if ((b -= 32) < 0) b = 0;
                        } else if (id == 7) {
                            r = (tmp >> 16) & 0xff;
                            g = (tmp >> 8) & 0xff;
                            b = tmp & 0xff;
                            r = Math.floor(r / 255 * r1);
                            g = Math.floor(g / 255 * r2);
                            b = Math.floor(b / 255 * r3);
                        }

                        img.data[pos + 0] = r;
                        img.data[pos + 1] = g;
                        img.data[pos + 2] = b;
                        img.data[pos + 3] = 0xff;
                        pos += 4;
                    }
                }

                if (pos >= 0x4B000) {
                    ctx.putImageData(img, 0, 0);
                    pos = 0;
                }
            },

            onCapture: function () {

                jQuery("#flash").css("display", "block");
                jQuery("#flash").fadeOut("fast", function () {
                    jQuery("#flash").css("opacity", 1);
                });
                webcam.save();
            },

            debug: function (type, string) {
                jQuery("#status").html(type + ": " + string);
            },

            onLoad: function () {

                var cams = webcam.getCameraList();
                for (var i in cams) {
                    jQuery("#cameraId").append("<li>" + cams[i] + "</li>");
                }
            }
        });

        jQuery(document).ready(function () {

            var canvas = document.getElementById("canvas");

            if (canvas.getContext) {
                ctx = document.getElementById("canvas").getContext("2d");
                ctx.clearRect(0, 0, 320, 240);

                var img = new Image();
                img.src = "/image/logo.gif";
                img.onload = function () {
                    ctx.drawImage(img, 129, 89);
                }
                image = ctx.getImageData(0, 0, 320, 240);
            }

        });

    </script>
</div>
</body>

</html>