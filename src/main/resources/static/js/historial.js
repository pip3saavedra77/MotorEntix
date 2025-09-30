// js/historial.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-historial");
  const lista = document.getElementById("lista-historial");

  // Datos simulados (normalmente vendrían de una base de datos)
  const historialServicios = [
    {
      cliente: "Juan Pérez",
      vehiculo: "ABC123",
      fecha: "2025-06-10",
      servicio: "Cambio de aceite",
      estado: "Completado",
    },
    {
      cliente: "María López",
      vehiculo: "XYZ789",
      fecha: "2025-06-12",
      servicio: "Alineación y balanceo",
      estado: "En proceso",
    },
    {
      cliente: "Juan Pérez",
      vehiculo: "ABC123",
      fecha: "2025-05-22",
      servicio: "Revisión general",
      estado: "Completado",
    },
  ];

  // Manejar envío del formulario
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const criterio = document.getElementById("cliente").value.trim().toLowerCase();

    if (!criterio) {
      alert("Por favor escribe un nombre o vehículo.");
      return;
    }

    // Filtrar coincidencias
    const resultados = historialServicios.filter((item) =>
      item.cliente.toLowerCase().includes(criterio) ||
      item.vehiculo.toLowerCase().includes(criterio)
    );

    // Mostrar resultados
    lista.innerHTML = ""; // Limpiar anteriores

    if (resultados.length === 0) {
      lista.innerHTML = "<li>No se encontraron resultados.</li>";
      return;
    }

    resultados.forEach((item) => {
      const li = document.createElement("li");
      li.innerHTML = `
        <strong>${item.fecha}</strong> - ${item.servicio}<br/>
        Cliente: ${item.cliente} | Vehículo: ${item.vehiculo}<br/>
        Estado: <em>${item.estado}</em>
      `;
      lista.appendChild(li);
    });

    form.reset();
  });
});
