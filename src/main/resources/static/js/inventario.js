// js/inventario.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-inventario");
  const lista = document.getElementById("lista-inventario");
  const inputId = document.getElementById("producto-id");
  const inputNombre = document.getElementById("producto");
  const inputCantidad = document.getElementById("cantidad");
  const inputPrecio = document.getElementById("precio");
  const btnCancelarEdicion = document.getElementById("cancelar-edicion");

  let inventario = [];

  // Cargar inventario inicial desde el backend
  async function cargarInventario() {
    try {
      const respuesta = await fetch("/inventarios");
      if (!respuesta.ok) {
        throw new Error("No se pudo cargar el inventario");
      }
      inventario = await respuesta.json();
      actualizarLista();
    } catch (error) {
      console.error(error);
      alert("Ocurrió un error al cargar el inventario.");
    }
  }

  // Crear o actualizar producto
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const nombre = inputNombre.value.trim();
    const cantidad = parseInt(inputCantidad.value);
    const precioUnitario = inputPrecio.value ? String(inputPrecio.value) : null;
    const id = inputId.value ? parseInt(inputId.value) : null;

    if (!nombre || isNaN(cantidad) || cantidad <= 0) {
      alert("Por favor completa correctamente los campos obligatorios.");
      return;
    }

    const payload = { nombre, cantidad, precioUnitario };

    try {
      let respuesta;
      if (id) {
        // Actualizar
        respuesta = await fetch(`/inventarios/${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        });
      } else {
        // Crear
        respuesta = await fetch("/inventarios", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        });
      }

      if (!respuesta.ok) {
        throw new Error("Error al guardar el producto");
      }

      await cargarInventario();
      resetFormulario();
    } catch (error) {
      console.error(error);
      alert("Ocurrió un error al guardar el producto.");
    }
  });

  // Cancelar edición
  btnCancelarEdicion.addEventListener("click", () => {
    resetFormulario();
  });

  // Actualizar lista en pantalla
  function actualizarLista() {
    lista.innerHTML = "";

    inventario.forEach((item) => {
      const li = document.createElement("li");
      li.dataset.id = item.id;
      const precioTexto = item.precioUnitario ? ` - Precio: ${item.precioUnitario}` : "";
      li.innerHTML = `
        <span>${item.nombre} — Cantidad: ${item.cantidad}${precioTexto}</span>
        <div>
          <button type="button" class="btn-editar">Editar</button>
          <button type="button" class="btn-eliminar">Eliminar</button>
        </div>
      `;

      const btnEditar = li.querySelector(".btn-editar");
      const btnEliminar = li.querySelector(".btn-eliminar");

      btnEditar.addEventListener("click", () => prepararEdicion(item));
      btnEliminar.addEventListener("click", () => eliminarProducto(item.id));

      lista.appendChild(li);
    });
  }

  // Preparar formulario para edición
  function prepararEdicion(item) {
    inputId.value = item.id;
    inputNombre.value = item.nombre;
    inputCantidad.value = item.cantidad;
    inputPrecio.value = item.precioUnitario ? item.precioUnitario : "";
    btnCancelarEdicion.style.display = "inline-block";
  }

  // Eliminar producto
  async function eliminarProducto(id) {
    if (!confirm("¿Seguro que deseas eliminar este producto?")) {
      return;
    }
    try {
      const respuesta = await fetch(`/inventarios/${id}`, {
        method: "DELETE",
      });
      if (!respuesta.ok) {
        throw new Error("Error al eliminar el producto");
      }
      await cargarInventario();
      resetFormulario();
    } catch (error) {
      console.error(error);
      alert("Ocurrió un error al eliminar el producto.");
    }
  }

  // Resetear formulario
  function resetFormulario() {
    form.reset();
    inputId.value = "";
    btnCancelarEdicion.style.display = "none";
  }

  // Inicializar
  cargarInventario();
});
