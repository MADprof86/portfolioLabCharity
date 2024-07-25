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
    <title>Profil Użytkownika</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>"/>
</head>
<body>
<header class="header--main-page" id="mainPage">
    <tags:header/>
    <section class="profile-page">
        <h2>Profil Użytkownika</h2>
        <div class="profile-info">
            <div class="form-group">
                <label>Email:</label>
                <span class="form-control readonly">${user.email}</span>
            </div>
            <div class="form-group">
                <label>Role:</label>
                <span class="form-control readonly">
                <c:forEach var="role" items="${user.roles}">
                    ${role.name}
                </c:forEach>
            </span>
            </div>
            <div class="form-group">
                <label>Donations Count:</label>
                <span class="form-control readonly">${donationsCount}</span>
            </div>
            <div class="block-list">
                <h3>Twoje przekazane dary</h3>
                <c:forEach var="donation" items="${donations}">
                    <div class="donation-item">
                        <p><strong>Fundacja:</strong> ${donation.institution.name}</p>
                        <p><strong>Data wysłania:</strong> ${donation.pickUpDate}</p>
                        <p><strong>Miasto:</strong> ${donation.city}</p>
                        <a href="donation/details/${donation.id}" class="btn btn--small btn--highlighted">Szczegóły</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <tags:footer/>
    <script src="<c:url value='/resources/js/app.js'/>"></script>
</header>
</body>
</html>
