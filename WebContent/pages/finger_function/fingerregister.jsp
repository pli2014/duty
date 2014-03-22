<%--
  User: peter
  Date: 14-3-22
  Time: 上午11:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
    </title>
</head>
<body>

<object classid="clsid:CA69969C-2F27-41D3-954D-A48B941C3BA7" name="zkf" id="ZKFPEngX1" codebase="">
    <param name="EnrollCount" value="2"/>
    <param name="SensorIndex" value="0"/>
    <param name="Threshold" value="10"/>
    <param name="VerTplFileName" value=""/>
    <param name="RegTplFileName" value=""/>
    <param name="OneToOneThreshold" value="10"/>
    <param name="Active" value="false"/>
    <param name="IsRegister" value="false"/>
    <param name="EnrollIndex" value="0"/>
    <param name="SensorSN" value=""/>
    <param name="FPEngineVersion" value="9"/>
    <param name="ImageWidth" value="40"/>
    <param name="ImageHeight" value="40"/>
    <param name="SensorCount" value="0"/>
    <param name="TemplateLen" value="800"/>
    <param name="EngineValid" value="true"/>
    <param name="ForceSecondMatch" value="true"/>
</object>

<script language="javascript" type="text/jscript">
 function initialize()
 {
   window.localFingerPath = "D:\\data\\img\\";
   window.remoteServerPath = "person/img/";
   window.fingerEng = document.getElementById("ZKFPEngX1");
   var i = fingerEng.InitEngine();
      if (i == 0)
    {
       printMessage('初始化成功');
    }
    else
    {
       printMessage('初始化失败');
    }
 }

 initialize();

</script>
<script for="ZKFPEngX1" language="JavaScript" type="text/javascript" event="OnImageReceived(result)">
    callImgReceived(result);
</script>
<script for="ZKFPEngX1" language="JavaScript" type="text/javascript" event="OnEnroll(result,ATemplate)">
    callMyEnroll(result, ATemplate);
</script>

<script type="text/javascript">

    function callImgReceived(result)
    {
        //注册操作
        if(fingerEng.IsRegister){
            if(result){
                var counter = fingerEng.EnrollIndex;
                if(counter-1>0)
                    printMessage("还有"+(counter-1)+"次数，请刷指纹");
            }else{
                printMessage("指纹质量不好");
            }
        }else{   //验证阶段
            if(result){

            }else{
                printMessage("指纹质量不好");
            }
        }
    }


    function callMyEnroll(result,ATemplate)
    {
        //注册操作
        if(true){
            //产生指纹的特征ID
            var localpath = window.localFingerPath + (window.figureNumber)+".jpg";
            var remotepath = window.remoteServerPath + (window.figureNumber)+".jpg";
            var localFingerPath = window.localFingerPath + (window.figureNumber)+".tpl";
            fingerEng.SaveJPG(localpath);
            //var template = fingerEng.GetTemplateAsString();
            //添加指纹特征模板到高速缓存里
            //fingerEng.AddRegTemplateStrToFPCacheDB(fpcHandle,figureNumber,template);
            //保存特征模板的base64.
            fingerEng.SaveTemplate(localFingerPath,ATemplate);
            document.getElementById("fingerjpg").src=remotepath;
            document.getElementById("fingerpath").value=remotepath;
            printMessage("指纹有效");
        }else{
            printMessage("指纹采集失败，请重新点击指纹录入");
        }
    }

    function beginRegister()
    {
        fingerEng.BeginEnroll();
        printMessage("开始登记");
    }
</script>
</body>
</html>
