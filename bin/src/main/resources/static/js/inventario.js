// js/inventario.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-inventario");
  const lista = document.getElementById("lista-inventario");

  const inventario = []; // Lista de productos simulada

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const producto = document.getElementById("producto").value.trim();
    const cantidad = parseInt(document.getElementById("cantidad").value);

    if (!producto || isNaN(cantidad) || cantidad <= 0) {
      alert("Por favor completa correctamente los campos.");
      return;
    }

    // Verificar si el producto ya existe
    const existente = inventario.find((item) => item.producto.toLowerCase() === producto.toLowerCase());

    if (existente) {
      existente.cantidad += cantidad;
    } else {
      inventario.push({ producto, cantidad });
    }

    actualizarLista();
    form.reset();
  });

  function actualizarLista() {
    lista.innerHTML = "";

    inventario.forEach((item) => {
      const li = document.createElement("li");
      li.textContent = `${item.producto} — Cantidad: ${item.cantidad}`;
      lista.appendChild(li);
    });
  }
});
