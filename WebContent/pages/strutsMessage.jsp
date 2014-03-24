<%--
  User: peter
  Date: 14-3-16
  Time: 上午10:22
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
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