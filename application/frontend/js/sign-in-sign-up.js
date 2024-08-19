const container = document.getElementById("container");
const overlayCon = document.getElementById("overlayCon");
const overlayBtn = document.getElementById("overlayBtn");

overlayBtn.addEventListener("click", () => {
  container.classList.toggle("right-panel-active");
  overlayBtn.classList.remove("btnScaled");
  window.requestAnimationFrame(() => {
    overlayBtn.classList.add("btnScaled");
  });
});

function auth(event) {
  event.preventDefault();

  let email = document.getElementById("email-in").value;
  let password = document.getElementById("password-in").value;

  let user = JSON.parse(localStorage.getItem(email));

  if (
    (email === "projectManager@hsbc.co.in" && password === "projectManager@123") ||
    (user.jobRole === "Project Manager" && user.password === password)
  ) {
    window.location.replace("/index.html"); // Project Manager route
  } else if (
    (email === "developer@hsbc.co.in" && password === "developer@456") ||
    (user.jobRole === "Developer" && user.password === password)
  ) {
    window.location.replace("/index.html"); // Developer route
  } else if (
    (email === "tester@hsbc.co.in" && password === "tester@789") ||
    (user.jobRole === "Tester" && user.password === password)
  ) {
    window.location.replace("/index.html"); // Tester route
  } else {
    alert("Invalid information");
    return;
  }
}

function saveSignUpDetails(event) {
  event.preventDefault();

  let jobRole = document.getElementById("jobRole-up").value;
  let email = document.getElementById("email-up").value;
  let password = document.getElementById("password-up").value;

  const signUp = { jobRole: jobRole, email: email, password: password };
  const jsonString = JSON.stringify(signUp);

  localStorage.setItem(email, jsonString);

  alert("Sign Up Successfully!!");
  window.location.replace("/sign-in-sign-up.html");
}
