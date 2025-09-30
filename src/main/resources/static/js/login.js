// js/login.js
document.getElementById("form-login").addEventListener("submit", function (e) {
  e.preventDefault();

  const usuario = document.getElementById("usuario").value;
  const contrasena = document.getElementById("contrasena").value;
  const rol = document.getElementById("rol").value;

  if (!usuario || !contrasena || !rol) {
    alert("Todos los campos son obligatorios.");
    return;
  }

  // Redirige según el rol seleccionado
  if (rol === "cliente") {
    window.location.href = "panel.cliente.html";
  } else if (rol === "administrador") {
    window.location.href = "panel.admin.html";
  } else if (rol === "dueno") {
    window.location.href = "panel.dueno.html";
  }
});
