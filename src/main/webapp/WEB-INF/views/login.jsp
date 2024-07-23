<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
  <title>Document</title>

  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>"/>
</head>
<body>
<header class="header--main-page" id="mainPage">
  <tags:header/>
  <section class="login-page">
    <h2>Zaloguj się</h2>
    <form:form method="post" action="/login" modelAttribute="user">
      <div class="form-group">
        <form:input path="email" placeholder="Email" />
        <form:errors path="email" class="error"/>
      </div>
      <div class="form-group">
        <form:password path="password" placeholder="Password" />
        <form:errors path="password" class="error"/>
        <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
      </div>
      <div class="form-group form-group--buttons">
        <a href="/register" class="btn btn--without-border">Załóż konto</a>
        <button class="btn" type="submit">Zaloguj się</button>
      </div>
    </form:form>
    <div>
      <c:if test="${param.error}">
        <p class="error">Invalid email or password.</p>
      </c:if>
    </div>
  </section>
  <tags:footer/>
  <script src="<c:url value='/resources/js/app.js'/>"></script>
</header>
</body>
</html>
