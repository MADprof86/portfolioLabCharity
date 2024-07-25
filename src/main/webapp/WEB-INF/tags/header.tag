<%@ tag description="Header Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="container container--70" id="start">
    <ul class="nav--actions">
        <sec:authorize access="isAuthenticated()">
            <li><a href="/logout" class="btn btn--small btn--without-border">Wyloguj</a></li>
            <li><a href="/profile" class="btn btn--small btn--highlighted">Profil</a></li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </sec:authorize>
    </ul>

    <ul>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="/#stats" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
        <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="/donation" class="btn btn--without-border">Przekaż dary</a></li>
        <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
