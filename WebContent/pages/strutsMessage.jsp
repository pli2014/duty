<%--
  User: peter
  Date: 14-3-16
  Time: 上午10:22
--%>
<!DOCTYPE html>
<%@ include file="commonHeader.jsp"%>
<style type="text/css">
    .actionMessage{
        color: green;
    }
    .errorMessage{
        color: red;
    }
    .fieldMessage{
        color: red;
    }
</style>
<div style="margin:15px">
    <s:actionerror/>
    <s:actionmessage/>
    <s:fielderror/>
</div>