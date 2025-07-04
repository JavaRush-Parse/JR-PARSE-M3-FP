<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <title>Ласкаво просимо</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(120deg, #2c3e50, #4ca1af);
            color: #cccccc;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        .welcome-box {
            background-color: rgba(46, 46, 46, 0.85);
            display: inline-block;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            text-align: center;
        }

        .welcome-title {
            font-size: 1.8em;
            margin-bottom: 20px;
            text-shadow: 1px 1px 4px rgba(0,0,0,0.5);
        }

        .welcome-text {
            font-size: 1.1em;
            margin-bottom: 30px;
        }

        .menu-btn {
            padding: 12px 24px;
            font-size: 1.2em;
            border: none;
            border-radius: 8px;
            background-color: #2e2e2e;
            color: #fff;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .menu-btn:hover {
            background-color: #3a3a3a;
        }
    </style>
</head>
<body>

<div class="welcome-box">
    <div class="welcome-title">Вітаємо у нашому текстовому квесті!</div>
    <div class="welcome-text">Тут ви можете пережити унікальні пригоди у вигаданих світах.</div>

    <c:if test="${not empty user_wins}">
        <div class="welcome-text">Ваші перемоги: ${user_wins}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/welcome" method="post">
        <button type="submit" class="menu-btn">Почати грати</button>
    </form>
</div>


</body>
</html>
