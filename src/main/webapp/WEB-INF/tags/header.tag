<%@ tag description="Header Tag" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>





<nav class="container container--70" id="start">
        <ul class="nav--actions">
            <li><a href="" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href=".." class="btn btn--without-border active">Start</a></li>
            <li><a href="/index#stats" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/index#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="/index#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="donation" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>