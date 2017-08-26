<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工列表数据</title>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var label = $(this).next(":hidden").val();
                var flag = confirm("确定要删除" + label + "的信息吗?");
                if (flag) {
                    var url = $(this).attr("href");

                    $("#_form").attr("action", url);
                    $("#_method").val("DELETE");
                    $("#_form").submit();
                }

                return false;
            });
        })
    </script>
</head>

<body>
    <form action="" method="POST" id="_form">
        <input type="hidden" id="_method" name="_method"/>
    </form>
    <c:if test="${page.totalPages == 0 || page.content == null}">
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
                <td><fmt:formatDate value="${emp.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td>${emp.department.deptName}</td>
                <td><a href="${pageContext.request.contextPath}/emp/edit/${emp.id}">edit</a></td>
                <td>
                    <a href="${pageContext.request.contextPath }/emp/delete/${emp.id}" class="delete">Delete</a>
                    <input type="hidden" value="${emp.lastName }"/>
                </td>
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
