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
    /* For testing*/
    <meta name="success-message" content="${success != null ? success : ''}"/>
    <meta name="error-message" content="${error != null ? error : ''}"/>
    <meta name="donation-message" content="${donationSuccessfullyAdded != null ? donationSuccessfullyAdded : ""} "/>
  </head>
  <body>
    <header class="header--form-page">
      <tags:header/>
      <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
        <div class="alert alert-success">${donationSucessfullyAdded.toString()}</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
      </c:if>
      <c:if test="${not empty donationSucessfullyAdded}">
        <div class="alert alert-error">${donationSucessfullyAdded.toString()}</div>
      </c:if>
      <div class="slogan container container--90">
        <div class="slogan--item">
          <h1>
            Oddaj rzeczy, których już nie chcesz<br />
            <span class="uppercase">potrzebującym</span>
          </h1>

          <div class="slogan--steps">
            <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
            <ul class="slogan--steps-boxes">
              <li>
                <div><em>1</em><span>Wybierz rzeczy</span></div>
              </li>
              <li>
                <div><em>2</em><span>Spakuj je w worki</span></div>
              </li>
              <li>
                <div><em>3</em><span>Wybierz fundację</span></div>
              </li>
              <li>
                <div><em>4</em><span>Zamów kuriera</span></div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </header>

    <section class="form--steps" >
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="2">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="3">
           Wybierz jedną, do
            której trafi Twoja przesyłka.
          </p>
          <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form action="/donation" method="post" modelAttribute="donation">
          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>
            <c:forEach var="category" items="${categories}">
              <div class="form-group form-group--checkbox">
                <label>
                  <input
                          type="checkbox"
                          name="categories"
                          value="${category.id}"
                  />
                  <span class="checkbox"></span>
                  <span class="description"
                  >${category.name}</span
                  >
                </label>
              </div>

            </c:forEach>

           <div class="form-group form-group--buttons">
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 2 -->
        <div data-step="3">
          <h3>Wybierz organizacje, której chcesz pomóc:</h3>
          <c:forEach var="institution" items="${institutions}">


          <div class="form-group form-group--checkbox">
            <label>
              <input type="radio" name="institution" value="${institution.id}" />
              <span class="checkbox radio"></span>
              <span class="description">
                  <div class="title">${institution.name}”</div>
                  <div class="subtitle">
                    ${institution.description}
                  </div>
                </span>
            </label>
          </div>
          </c:forEach>


          <div class="form-group form-group--buttons">
            <button type="button" class="btn prev-step">Wstecz</button>
            <button type="button" class="btn next-step">Dalej</button>
          </div>
        </div>



          <!-- STEP 4 -->
          <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
              <label>
                Liczba 60l worków:
                <input type="number" name="quantity" step="1" min="1" />
              </label>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 5 -->
          <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

            <div class="form-section form-section--columns">
              <div class="form-section--column">
                <h4>Adres odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Ulica <input type="text" name="street" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Miasto <input type="text" name="city" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Kod pocztowy <input type="text" name="zipCode" />
                  </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Numer telefonu <input type="phone" name="phone" />
                  </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data <input type="date" name="pickUpDate" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label> Godzina <input type="time" name="pickUpTime" /> </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Uwagi dla kuriera
                    <textarea name="pickUpComment" rows="5"></textarea>
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 6 -->
          <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
              <div class="form-section">
                <h4>Oddajesz:</h4>
                <ul>
                  <li>
                    <span class="icon icon-bag"></span>
                    <span class="summary--text"
                      >4 worki ubrań w dobrym stanie dla dzieci</span
                    >
                  </li>

                  <li>
                    <span class="icon icon-hand"></span>
                    <span class="summary--text"
                      >Dla fundacji "Mam marzenie" w Warszawie</span
                    >
                  </li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li>Prosta 51</li>
                    <li>Warszawa</li>
                    <li>99-098</li>
                    <li>123 456 789</li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li>13/12/2018</li>
                    <li>15:40</li>
                    <li>Brak uwag</li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="submit" class="btn">Potwierdzam</button>
            </div>

          </div>

        </form:form>
      </div>
    </section>

    <tags:footer/>

    <script src="<c:url value="/resources/js/app.js"/>"></script>
    <div id="popupOverlay" class="popup-overlay">
      <div class="popup-content">
        <h2 id="popupTitle" class="popup-title"></h2>
        <p id="popupMessage" class="popup-message"></p>
        <button id="popupButton" class="popup-close-button">Close</button>
      </div>
    </div>


    <script src="<c:url value="resources/js/app.js"/>"></script>
    <script>
      document.addEventListener('DOMContentLoaded', function() {
        const popupOverlay = document.getElementById('popupOverlay');
        const popupButton = document.getElementById('popupButton');
        const popupTitle = document.getElementById('popupTitle');
        const popupMessage = document.getElementById('popupMessage');

        const success = '${success}';
        const error = '${error}';
        const donation = ${donationSuccessfullyAdded ? '${donationSuccessfullyAdded}' : 'null'};

        console.log('Success:', success);
        console.log('Error:', error);
        console.log('Donation:', donation);

        if (success) {
          popupTitle.textContent = 'Sukces!';
          popupMessage.textContent = success;
          popupOverlay.style.display = 'flex';
        }
        else if (error) {
          popupTitle.textContent = 'Błąd!';
          popupMessage.textContent = error;
          popupOverlay.style.display = 'flex';
        }

    <%--    if (success && donation) {--%>
    <%--      const summary = `--%>
    <%--  <h3>Podsumowanie Twojej darowizny:</h3>--%>
    <%--  <ul>--%>
    <%--    <li><strong>Oddajesz:</strong> ${donation.categories}</li>--%>
    <%--    <li><strong>Liczba 60l worków:</strong> ${donation.quantity}</li>--%>
    <%--    <li><strong>Fundacja:</strong> ${donation.institution}</li>--%>
    <%--    <li><strong>Adres odbioru:</strong> ${donation.street}, ${donation.city}, ${donation.zipCode}</li>--%>
    <%--    <li><strong>Data odbioru:</strong> ${donation.pickUpDate}</li>--%>
    <%--    <li><strong>Godzina odbioru:</strong> ${donation.pickUpTime}</li>--%>
    <%--    <li><strong>Uwagi:</strong> ${donation.pickUpComment}</li>--%>
    <%--  </ul>--%>
    <%--`;--%>
    <%--      document.getElementById('popupSummary').innerHTML = summary;--%>
    <%--    }--%>

        popupButton.addEventListener('click', function() {
          popupOverlay.style.display = 'none';
        });
      });

    </script>
  </body>
</html>
