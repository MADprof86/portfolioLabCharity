<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

  </head>
  <body>
  <header class="header--main-page" id="mainPage">
    <tags:header/>
    <section class="details-page">
      <h2>Szczegóły Darowizny</h2>
      <div class="details-info">
        <div class="form-group">
          <label>Fundacja:</label>
          <span class="form-control readonly">${donation.institution.name}</span>
        </div>
        <div class="form-group">
          <label>Data wysłania:</label>
          <span class="form-control readonly">${donation.pickUpDate}/></span>
        </div>
        <div class="form-group">
          <label>Godzina odbioru:</label>
          <span class="form-control readonly">${donation.pickUpTime}</span>
        </div>
        <div class="form-group">
          <label>Miasto:</label>
          <span class="form-control readonly">${donation.city}</span>
        </div>
        <div class="form-group">
          <label>Ulica:</label>
          <span class="form-control readonly">${donation.street}</span>
        </div>
        <div class="form-group">
          <label>Kod pocztowy:</label>
          <span class="form-control readonly">${donation.zipCode}</span>
        </div>
        <div class="form-group">
          <label>Numer telefonu:</label>
          <span class="form-control readonly">${donation.phone}</span>
        </div>
        <div class="block-list">
          <h3>Uwagi dla kuriera:</h3>
          <span class="form-control readonly">${donation.pickUpComment}</span>
        </div>
        <div class="form-group">
          <label>Przekazane przedmioty:</label>
          <c:forEach var="item" items="${donation.categories}">
            <span class="form-control readonly">${item.name}</span>
          </c:forEach>

        </div>
        <div class="form-group">
          <label>Ilość worków:</label>
          <span class="form-control readonly">${donation.quantity}</span>
        </div>
      </div>
      <div class="btn btn--small btn--highlighted">
        <a href="/profile">Wróć do profilu</a>
      </div>
    </section>
    <tags:footer/>
    <script src="<c:url value='/resources/js/app.js'/>"></script>
  </header>
  </body>
</html>
