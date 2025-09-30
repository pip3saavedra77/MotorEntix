// js/servicios.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-servicio");
  const lista = document.getElementById("lista-servicios");

  const servicios = []; // Lista temporal de servicios registrados

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    // Capturar valores del formulario
    const cliente = document.getElementById("cliente").value.trim();
    const tipo = document.getElementById("tipo").value.trim();
    const observaciones = document.getElementById("observaciones").value.trim();
    const estado = document.getElementById("estado").value;

    if (!cliente || !tipo || !estado) {
      alert("Por favor completa todos los campos obligatorios.");
      return;
    }

    // Crear objeto servicio
    const nuevoServicio = {
      cliente,
      tipo,
      observaciones,
      estado,
      fecha: new Date().toLocaleDateString()
    };

    // Guardar servicio
    servicios.push(nuevoServicio);

    // Mostrar servicios en la lista
    actualizarLista();

    // Limpiar formulario
    form.reset();
  });

  function actualizarLista() {
    lista.innerHTML = "";

    servicios.forEach((s) => {
      const item = document.createElement("li");
      item.innerHTML = `
        <strong>${s.fecha}</strong> - ${s.tipo}<br>
        Cliente/Vehículo: ${s.cliente}<br>
        Observaciones: ${s.observaciones || "Ninguna"}<br>
        Estado: <em>${s.estado}</em>
      `;
      lista.appendChild(item);
    });
  }
});
