// js/reservas.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-reserva");
  const lista = document.getElementById("lista-reservas");

  // Arreglo donde se almacenarán temporalmente las reservas
  const reservas = [];

  // Evento al enviar el formulario
  form.addEventListener("submit", (e) => {
    e.preventDefault();

    // Obtener datos del formulario
    const nombre = document.getElementById("nombre").value.trim();
    const vehiculo = document.getElementById("vehiculo").value.trim();
    const fecha = document.getElementById("fecha").value;
    const hora = document.getElementById("hora").value;

    if (!nombre || !vehiculo || !fecha || !hora) {
      alert("Todos los campos son obligatorios.");
      return;
    }

    // Crear objeto reserva
    const nuevaReserva = {
      nombre,
      vehiculo,
      fecha,
      hora,
    };

    // Agregar reserva al array
    reservas.push(nuevaReserva);

    // Mostrar en pantalla
    actualizarLista();

    // Limpiar formulario
    form.reset();
  });

  // Función para mostrar las reservas
  function actualizarLista() {
    lista.innerHTML = ""; // Limpia la lista antes de volver a cargarla

    reservas.forEach((reserva, index) => {
      const item = document.createElement("li");
      item.textContent = `${reserva.fecha} ${reserva.hora} - ${reserva.nombre} (${reserva.vehiculo})`;
      lista.appendChild(item);
    });
  }
});
