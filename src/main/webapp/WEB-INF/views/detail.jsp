<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<style>
    table#list-table {
        border-collapse: separate;
        border-spacing: 1px;
        text-align: center;
        line-height: 1.5;
        padding: 1.5rem;
        margin: auto;
    }

    table#list-table th {
        padding: 10px;
        font-weight: bold;
        vertical-align: top;
        color: #fff;
        background: dodgerblue;
    }

    table#list-table td {
        padding: 3px;
        vertical-align: top;
        border-bottom: 1px solid #ccc;
        background: #eee;
    }

    table#list-table td:nth-child(2), table#list-table td:nth-child(3), table#list-table td:nth-child(4), table#list-table td:nth-child(5) {
        width: 155px;
    }

    button {
        background: #1AAB8A;
        color: #fff;
        border: none;
        position: relative;
        height: 60px;
        font-size: 1.6em;
        padding: 0 2em;
        cursor: pointer;
        transition: 800ms ease all;
        outline: none;
    }

    button:hover {
        background: #fff;
        color: #1AAB8A;
    }

    button:before, button:after {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        height: 2px;
        width: 0;
        background: #1AAB8A;
        transition: 400ms ease all;
    }

    button:after {
        right: inherit;
        top: inherit;
        left: 0;
        bottom: 0;
    }

    button:hover:before, button:hover:after {
        width: 100%;
        transition: 800ms ease all;
    }

    button#btn-resend {
        background-color: coral;
    }

    button#btn-remove {
        background-color: darkolivegreen;
    }

    #list-button-body {
        text-align: center;
    }
</style>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        let btn_remove = document.querySelector("#btn-remove");
        btn_remove.addEventListener("click", function () {
            var id = btn_remove.getAttribute("data-id");
            document.location.href = "${rootPath}/delete/" + id;
        });
    });
</script>
<section id="list-body">
    <table id="list-table">
        <tr>
            <th>발신자</th>
            <th>수신자</th>
            <th>제목</th>
            <th>내용</th>
            <th>전송일자</th>
            <th>전송시각</th>
        </tr>
        <tr>
            <td>${EMSVO.from_email}</td>
            <td>${EMSVO.to_email}</td>
            <td>${EMSVO.s_subject}</td>
            <td>${EMSVO.s_content}</td>
            <td>${EMSVO.s_date}</td>
            <td>${EMSVO.s_time}</td>
        </tr>
        <tr>
            <td colspan="6">
                <img src="${rootPath}/upload/${EMSVO.s_file1}" width="100px">
                <img src="${rootPath}/upload/${EMSVO.s_file2}" width="100px">
            </td>
        </tr>
    </table>
    <div id="list-button-body">
        <button id="btn-write" type="button" onclick="document.location.href = '${rootPath}/write'">메일 작성</button>
        <button id="btn-resend" type="button">메일 다시보내기</button>
        <button id="btn-remove" type="button" data-id="${EMSVO.id}">기록 삭제</button>
        <button id="btn-list" type="button" onclick="document.location.href = '${rootPath}/'">목록으로</button>
    </div>
</section>