<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>제목없음!</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        html, body {
            width: 100%;
            height: 100%;
        }

        header {
            text-align: center;
            border: 1px solid #ce4869;
            background-color: #ce4869;
            color: white;
            padding: 1.2rem;
        }

        nav {
            background-color: #ce4869;
        }

        nav ul {
            list-style: none;
            background-color: #ce4869;
            color: white;
            display: flex;
        }

        nav ul li {
            display: inline-block;
            margin: 0px 10px;
            padding: 6px 12px;
            cursor: pointer;
            border-bottom: 5px solid #ce4869;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            document.querySelector("header").addEventListener("click", function () {
                document.location.href = "${rootPath}/";
            });
        });
    </script>
</head>
<body>
<header>
    <h1>EMS</h1>
</header>
<nav>
    <ul>
        <%-- 이메일 전송 목록 --%>
        <li>Home</li>
    </ul>
</nav>
<c:choose>
    <c:when test="${BODY == 'EMS-WRITE'}">
        <%@ include file="/WEB-INF/views/write.jsp" %>
    </c:when>
    <c:when test="${BODY == 'EMS-DETAIL'}">
        <%@ include file="/WEB-INF/views/detail.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/WEB-INF/views/list.jsp" %>
    </c:otherwise>
</c:choose>
</body>
</html>