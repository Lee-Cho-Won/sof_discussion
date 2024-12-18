<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Stack Overflow 게시글</title>
</head>
<body>
    <h1>Stack Overflow 게시글</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>제목</th>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${stackoverflowPosts}" var="post">
                <tr>
                    <td>${post.id}</td>
                    <td>${post.title}</td>
                    <td>${post.content}</td>
                    <td>${post.author}</td>
                    <td>${post.createdDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html> 