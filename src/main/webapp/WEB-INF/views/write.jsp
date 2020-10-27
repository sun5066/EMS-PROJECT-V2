<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style>
    section#write-body {
        width: 90%;
        border: 1px solid #ddd;
        margin: auto;
    }

    table#write-table {
        width: 100%;
    }

    table#write-table td {
        padding: 5px;
    }

    table#write-table th {
        text-align: center;
    }

    input {
        width: 100%;
    }

    div#write-button-body button {
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

    div#write-button-body button:hover {
        background: #fff;
        color: #1AAB8A;
    }

    div#write-button-body button:before, div#write-button-body button:after {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        height: 2px;
        width: 0;
        background: #1AAB8A;
        transition: 400ms ease all;
    }

    div#write-button-body {
        text-align: center;
    }

    div#write-button-body button:after {
        right: inherit;
        top: inherit;
        left: 0;
        bottom: 0;
    }

    div#write-button-body button:hover:before, div#write-button-body button:hover:after {
        width: 100%;
        transition: 800ms ease all;
    }

    div#write-button-body button#btn-rewrite {
        background-color: coral;
    }

    div#write-button-body button#btn-list {
        background-color: darkolivegreen;
    }
</style>

<script>
    var toolbar = [
        ['style', ['bold', 'italic', 'underline']],
        ['fontsize', ['fontsize']],
        ['font Style', ['fontname']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['height', ['height']],
        ['table', ['table']],
        ['insert', ['link', 'hr', 'picture']],
        ['view', ['fullscreen', 'codeview']]
    ]

    $(document).ready(function () {
        $('#s_content').summernote({
            color: "black",
            lang: "ko-KR",
            width: "100%",
            height: "200px",
            toolbar,
        });

        $('#btn-list').click(function () {
            document.location.href = '${rootPath}/'
        })

        $('#btn-rewrite').click(function () {
            document.location.href = '${rootPath}/write'
        })

        $('#btn-send').click(function () {
            document.querySelector("form").submit();
        })
    });
</script>

<section id="write-body">
    <form action="${rootPath}/write" method="post" enctype="multipart/form-data">
        <table id="write-table">
            <tr>
                <th>*보내는사람</th>
                <td>
                    <input id="from_email" name="from_email" type="text">
                </td>
            </tr>
            <tr>
                <th>*받는사람</th>
                <td>
                    <input id="to_email" name="to_email" type="text">
                </td>
            </tr>
            <tr>
                <th>*제목</th>
                <td>
                    <input id="s_subject" name="s_subject" type="text">
                </td>
            </tr>
            <tr>
                <th>파일첨부</th>
                <td>
                    <input id="s_file1" name="file" type="file" accept="image/*">
                    <input id="s_file2" name="file" type="file" accept="image/*">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <textarea id="s_content" name="s_content" rows="5" cols="20"></textarea>
                </td>
            </tr>
        </table>
        <div id="write-button-body">
            <button id="btn-send" type="button">메일 보내기</button>
            <button id="btn-rewrite" type="button">다시 작성</button>
            <button id="btn-list" type="button">목록으로</button>
        </div>
    </form>
</section>