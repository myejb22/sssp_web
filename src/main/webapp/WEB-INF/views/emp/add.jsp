<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<script type="application/javascript" src="/js/jquery-1.9.1.min.js"/>
<script type="application/javascript">
    $(function(){
        $("#lastName").change(function(){
            var val = $(this).val();
            val = $.trim(val);
            $(this).val(val);

            //若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
            var _oldLastName = $("#_oldLastName").val();
            _oldLastName = $.trim(_oldLastName);
            if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
                alert("lastName 可用!");
                return;
            }

            var url = "${pageContext.request.contextPath }/ajaxValidateLastName";
            var args = {"lastName":val,"date":new Date()};

            $.post(url, args, function(data){
                if(data == "0"){
                    alert("lastName 可用!");
                }else if(data == "1"){
                    alert("lastName 不可用!");
                }else{
                    alert("网络或程序出错. ");
                }
            });
        });
    })
</script>
<body>
<h2>增加员工信息</h2>

<form:form action="${pageContext.request.contextPath}/emp/add" method="post" modelAttribute="employee">
    LastName: <form:input path="lastName" id="lastName"/>
    <br>
    Email: <form:input path="email"/>
    <br>
    Birth: <form:input path="birthDay"/>
    <br>
    Department:
    <form:select path="department.id" items="${departments }"
                 itemLabel="deptName" itemValue="id"></form:select>
    <br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
