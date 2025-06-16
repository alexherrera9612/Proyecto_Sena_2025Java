// js/main.js

document.addEventListener('DOMContentLoaded', function () {
  cargarProductos();
  setupFormularioRegistro();
  setupFormularioLogin();
});

// Simular productos destacados (esto luego se puede reemplazar por un fetch desde el backend)
function cargarProductos() {
  const productos = [
    { id: 1, nombre: 'Laptop Lenovo', precio: 2200000, imagen: 'img/laptop1.jpg' },
    { id: 2, nombre: 'Monitor ASUS', precio: 950000, imagen: 'img/monitor1.jpg' },
    { id: 3, nombre: 'Teclado Mecánico', precio: 250000, imagen: 'img/teclado1.jpg' },
    { id: 4, nombre: 'Mouse Logitech', precio: 180000, imagen: 'img/mouse1.jpg' }
  ];

  const contenedor = document.getElementById('productos-destacados');
  /*
  productos.forEach(producto => {
    const div = document.createElement('div');
    div.classList.add('producto');
    div.innerHTML = `
      <img src="${producto.imagen}" alt="${producto.nombre}">
      <h4>${producto.nombre}</h4>
      <p>$${producto.precio.toLocaleString()}</p>
      <button onclick="agregarAlCarrito(${producto.id}, '${producto.nombre}', ${producto.precio})">Agregar</button>
    `;
    contenedor.appendChild(div);
  });
    /*/*
}

let carrito = [];

function agregarAlCarrito(id, nombre, precio) {
  carrito.push({ id, nombre, precio });
  actualizarCarrito();
}

function actualizarCarrito() {
  const lista = document.getElementById('lista-carrito');
  const total = document.getElementById('total-carrito');
  lista.innerHTML = '';
  let totalPrecio = 0;

  carrito.forEach(item => {
    const li = document.createElement('li');
    li.textContent = `${item.nombre} - $${item.precio.toLocaleString()}`;
    lista.appendChild(li);
    totalPrecio += item.precio;
  });

  total.textContent = `Total: $${totalPrecio.toLocaleString()}`;
}

// Registro
function setupFormularioRegistro() {
  const form = document.getElementById('formulario-registro');
  if (form) {
    form.addEventListener('submit', function (e) {
      e.preventDefault();
      const nombre = document.getElementById('nombre').value;
      const correo = document.getElementById('correo').value;
      const contrasena = document.getElementById('contrasena').value;

      fetch('/api/registro', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nombre, correo, contrasena })
      })
        .then(response => {
          if (response.ok) {
            alert('Registro exitoso');
            form.reset();
            window.location.href = 'login.html';
          } else {
            alert('Error en el registro');
          }
        })
        .catch(error => console.error('Error:', error));
    });
  }
}

// Login
function setupFormularioLogin() {
  const form = document.getElementById('formulario-login');
  if (form) {
    form.addEventListener('submit', function (e) {
      e.preventDefault();
      const correo = document.getElementById('correo').value;
      const contrasena = document.getElementById('contrasena').value;

      fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ correo, contrasena })
      })
        .then(response => {
          if (response.ok) {
            alert('Inicio de sesión exitoso');
            window.location.href = 'index.html';
          } else {
            alert('Credenciales incorrectas');
          }
        })
        .catch(error => console.error('Error:', error));
    });
  }
}
