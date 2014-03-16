<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<label>姓名</label>
<div class="input-control text" data-role="input-control">
    <input name="volunteer.id" type="hidden" value="${volunteer.id}"/>
    <input type="text" placeholder="请输入姓名" name="volunteer.name" value="${volunteer.name}" autofocus required="required"/>
    <button class="btn-clear" tabindex="-1"></button>
</div>
