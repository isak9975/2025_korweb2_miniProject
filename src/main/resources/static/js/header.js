document.addEventListener("DOMContentLoaded", function () {
  fetch("/src/main/resources/templates/header.html")
    .then((response) => response.text())
    .then((data) => {
      document.getElementById("header-placeholder").innerHTML = data;
    });
});
