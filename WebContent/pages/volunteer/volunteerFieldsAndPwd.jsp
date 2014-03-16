<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="volunteerField1.jsp"%>

<label>密码</label>
<div class="input-control password" data-role="input-control">
    <input type="password" placeholder="请输入密码" id="password" name="volunteer.password" autofocus required="required"/>
    <button class="btn-reveal" tabindex="-1"></button>
</div>
<div class="input-control password" data-role="input-control">
    <input type="password" placeholder="请再次输入密码" name="confirm_password" autofocus required="required"/>
    <button class="btn-reveal" tabindex="-1"></button>
</div>

<%@ include file="volunteerField2.jsp"%>