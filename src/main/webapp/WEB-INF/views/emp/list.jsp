<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>员工列表数据</title>
</head>
<body>
    <c:if test="${page.totalPages == 0 || page.content == null}" >
        <span style="color: #db060f;">没有相应的数据</span>
    </c:if>
    <c:if test="${page.totalPages > 0 && page.content != null}">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>id</th>
                <th>lastName</th>
                <th>email</th>
                <th>birth</th>
                <th>createDate</th>
                <th>department</th>
                <th>edit</th>
                <th>delete</th>
            </tr>
            <c:forEach items="${page.content}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td><fmt:formatDate value="${emp.birthDay}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${emp.createDate}" pattern="yyyy-mm-dd hh:mm:ss"/></td>
                    <td>${emp.department.deptName}</td>
                    <td><a href="">edit</a></td>
                    <td><a href="">delete</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="8">
                    共 ${page.totalElements } 条记录
                    共 ${page.totalPages } 页
                    当前 ${page.number + 1 } 页
                    <a href="?pageNo=${page.number + 1 - 1 }">上一页</a>
                    <a href="?pageNo=${page.number + 1 + 1 }">下一页</a>
                </td>
            </tr>
        </table>
    </c:if>
</body>
</html>
