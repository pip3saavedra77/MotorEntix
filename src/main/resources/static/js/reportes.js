// js/reportes.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-reportes");
  const lista = document.getElementById("lista-reportes");

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const fechaInicio = new Date(document.getElementById("fecha-inicio").value);
    const fechaFin = new Date(document.getElementById("fecha-fin").value);

    if (isNaN(fechaInicio) || isNaN(fechaFin)) {
      alert("Debes seleccionar un rango de fechas válido.");
      return;
    }

    if (fechaInicio > fechaFin) {
      alert("La fecha de inicio no puede ser posterior a la fecha final.");
      return;
    }

    // Simulación de generación de reportes
    const dias = Math.ceil((fechaFin - fechaInicio) / (1000 * 60 * 60 * 24)) + 1;

    const ingresos = dias * 100000; // Simulamos $100.000 por día
    const reservas = Math.floor(dias * 1.5); // Simulamos 1.5 reservas por día
    const servicios = Math.floor(dias); // Simulamos 1 servicio por día

    // Mostrar resultados
    lista.innerHTML = `
      <li><strong>Ingresos aproximados:</strong> $${ingresos.toLocaleString()}</li>
      <li><strong>Reservas realizadas:</strong> ${reservas}</li>
      <li><strong>Servicios completados:</strong> ${servicios}</li>
      <li><strong>Días evaluados:</strong> ${dias} día(s)</li>
    `;
  });
});
