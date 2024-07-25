document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }

  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }
      /**
       * All events that are happening in form
       */
    events() {
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });
          // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      if (this.currentStep === 5) {
        this.updateSummary();
      }
    }

    updateSummary() {
      const summary = this.$form.querySelector('.summary');

      // Categories
      const selectedCategories = Array.from(this.$form.querySelectorAll('input[name="categories"]:checked'))
          .map(checkbox => checkbox.nextElementSibling.nextElementSibling.textContent.trim())
          .join(', ');

      summary.querySelector('.summary--text').textContent = `${selectedCategories || 'Brak danych'} worki ubrań w dobrym stanie`;

      // Institution
      const selectedInstitution = this.$form.querySelector('input[name="institution"]:checked');
      const institutionName = selectedInstitution ? selectedInstitution.nextElementSibling.nextElementSibling.querySelector('.title').textContent : 'Brak danych';
      summary.querySelectorAll('.summary ul li')[1].textContent = `Dla fundacji "${institutionName}"`;

      // Address and Pickup Details
      const address = this.$form.querySelector('input[name="street"]').value;
      const city = this.$form.querySelector('input[name="city"]').value;
      const postcode = this.$form.querySelector('input[name="zipCode"]').value;
      const phone = this.$form.querySelector('input[name="phone"]').value;
      const pickupDate = this.$form.querySelector('input[name="pickUpDate"]').value;
      const pickupTime = this.$form.querySelector('input[name="pickUpTime"]').value;
      const pickupComment = this.$form.querySelector('textarea[name="pickUpComment"]').value;

      const addressList = summary.querySelectorAll('.form-section--column')[0].querySelector('ul');
      addressList.innerHTML = `
      <li>${address || 'Brak danych'}</li>
      <li>${city || 'Brak danych'}</li>
      <li>${postcode || 'Brak danych'}</li>
      <li>${phone || 'Brak danych'}</li>
    `;

      const pickupList = summary.querySelectorAll('.form-section--column')[1].querySelector('ul');
      pickupList.innerHTML = `
      <li>${pickupDate || 'Brak danych'}</li>
      <li>${pickupTime || 'Brak danych'}</li>
      <li>${pickupComment || 'Brak uwag'}</li>
    `;
    }

    /**
     * Handle form submission
     */
    submit(e) {
      e.preventDefault(); // Prevent the default form submission

      // Perform any validation or custom logic here
      // ...

      // After validation/custom logic, submit the form
      this.$form.querySelector("form").submit();
    }

    /**
     * Reset the form and go back to the first step
     */
    resetForm() {
      this.currentStep = 1; // Reset to the first step
      this.updateForm(); // Update the form to show the first step
      this.$form.querySelector("form").reset(); // Reset form fields
    }
  }


  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
  // Function to show the popup
  function showPopup(title, message) {
    const overlay = document.getElementById('popupOverlay');
    const popupTitle = document.getElementById('popupTitle');
    const popupMessage = document.getElementById('popupMessage');
    const popupButton = document.getElementById('popupButton');

    popupTitle.innerText = title;
    popupMessage.innerText = message;

    // Show the popup
    overlay.style.display = 'flex';

    // Add event listener to the button to hide the popup when clicked
    popupButton.addEventListener('click', function() {
      overlay.style.display = 'none';
    });
  }

  // Show popup if there is a success or error message
  const successMessage = document.getElementById('popupSuccessMessage');
  const errorMessage = document.getElementById('popupErrorMessage');

  if (successMessage) {
    showPopup('Success', successMessage.innerText);
  }

  if (errorMessage) {
    showPopup('Error', errorMessage.innerText);
  }


});
$(document).ready(function() {
  function sortDonations(criteria) {
    const donationsList = $('#donations-list');
    const donations = donationsList.children('.donation-item').get();

    donations.sort(function(a, b) {
      const valA = $(a).data(criteria);
      const valB = $(b).data(criteria);
      if (criteria === 'date') {
        return new Date(valA) - new Date(valB);
      } else {
        return valA.localeCompare(valB);
      }
    });

    $.each(donations, function(index, item) {
      donationsList.append(item);
    });
  }

  $('.sort-options button').on('click', function() {
    const criteria = $(this).attr('data-sort');
    sortDonations(criteria);
  });
});


// function showPopup(title, message) {
//   const overlay = document.getElementById('popupOverlay');
//   const popupTitle = document.getElementById('popupTitle');
//   const popupMessage = document.getElementById('popupMessage');
//   const popupButton = document.getElementById('popupButton');
//
//   popupTitle.innerText = title;
//   popupMessage.innerText = message;
//
//   // Show the popup
//   overlay.style.display = 'flex';
//
//   // Add event listener to the button to hide the popup when clicked
//   popupButton.addEventListener('click', function() {
//     overlay.style.display = 'none';
//   });
// }
