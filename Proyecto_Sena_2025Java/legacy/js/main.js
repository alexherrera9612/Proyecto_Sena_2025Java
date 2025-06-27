// js/main.js

document.addEventListener('DOMContentLoaded', function () {
  cargarProductos();            // Carga productos destacados
  setupFormularioRegistro();   // Configura formulario de registro
  setuploginForm();            // Configura formulario de login
  actualizarMenuUsuario();     // Muestra usuario si está logueado

  // Si estamos en carrito.html, cargar el carrito
  if (window.location.pathname.includes('carrito.html')) {
    cargarCarritoDesdeStorage();
    actualizarCarrito();
  }
});

// ------------------------------
// 1. Productos destacados
// ------------------------------
function cargarProductos() {
  const productos = [
    { id: 1, nombre: 'Laptop Lenovo', precio: 2200000, imagen: 'img/laptop1.jpg' },
    { id: 2, nombre: 'Monitor ASUS', precio: 950000, imagen: 'img/monitor1.jpg' },
    { id: 3, nombre: 'Teclado Mecánico', precio: 250000, imagen: 'img/teclado1.jpg' },
    { id: 4, nombre: 'Mouse Logitech', precio: 180000, imagen: 'img/mouse1.jpg' }
  ];

  const contenedor = document.getElementById('productos-destacados');
  if (contenedor) {
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
  }
}

// ------------------------------
// 2. Carrito de compras
// ------------------------------
let carrito = [];

function agregarAlCarrito(id, nombre, precio) {
  carrito.push({ id, nombre, precio });
  localStorage.setItem('lista-carrito', JSON.stringify(carrito));
  alert(`${nombre} agregado al carrito`);
}
function cargarCarritoDesdeStorage() {
  const guardado = localStorage.getItem('lista-carrito');
  if (guardado) {
    carrito = JSON.parse(guardado);
  }
}
function actualizarCarrito() {
  const lista = document.getElementById('lista-carrito');
  const total = document.getElementById('total-carrito');

  if (!lista || !total) return;

  lista.innerHTML = '';
  let totalPrecio = 0;

  carrito.forEach(item => {
    const div = document.createElement('div');
    div.textContent = `${item.nombre} - $${item.precio.toLocaleString()}`;
    lista.appendChild(div);
    totalPrecio += item.precio;
  });

  total.textContent = totalPrecio.toLocaleString();
}

// ------------------------------
// 3. Registro de usuario
// ------------------------------
function setupFormularioRegistro() {
  const registroForm = document.getElementById('registroForm');
  if (registroForm) {
    registroForm.addEventListener('submit', function (e) {
      e.preventDefault();

      const nombre = document.getElementById('nombre').value;
      const usuario = document.getElementById('usuario').value;
      const correo = document.getElementById('correo').value;
      const contrasena = document.getElementById('contrasena').value;

      fetch('/api/usuarios', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ nombre, usuario, correo, contrasena })
      })
        .then(response => {
          if (!response.ok) {
            return response.json().then(data => {
              throw new Error(Object.values(data).join('\n'));
            });
          }
          return response.json();
        })
        .then(data => {
          document.getElementById('mensajeRegistro').innerText = "✅ Usuario registrado exitosamente";
          registroForm.reset();
        })
        .catch(error => {
          document.getElementById('mensajeRegistro').innerText = "❌ Error: " + error.message;
          console.error("Error en registro:", error.message);
        });
    });
  }
}

// ------------------------------
// 4. Login de usuario
// ------------------------------
function setuploginForm() {
  const form = document.getElementById('loginForm');
  if (form) {
    form.addEventListener('submit', function (e) {
      e.preventDefault();

      const correo = document.getElementById('correo').value;
      const contrasena = document.getElementById('contrasena').value;

      fetch('/api/usuarios/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ correo, contrasena })
      })
        .then(response => {
          if (!response.ok) throw new Error("Credenciales incorrectas");
          return response.json();
        })
        .then(usuario => {
          localStorage.setItem('usuarioLogueado', JSON.stringify(usuario));
          alert('Inicio de sesión exitoso');
          window.location.href = 'index.html';
        })
        .catch(error => {
          alert(error.message);
          console.error('Error en login:', error);
        });
    });
  }
}

// ------------------------------
// 5. Actualizar menú usuario logueado
// ------------------------------
function actualizarMenuUsuario() {
  const usuarioGuardado = localStorage.getItem('usuarioLogueado');

  if (usuarioGuardado) {
    const usuario = JSON.parse(usuarioGuardado);
    const navLinks = document.querySelector('.nav-links');

    if (navLinks) {
      navLinks.innerHTML = `
        <li><a href="index.html">Inicio</a></li>
        <li><a href="catalogo.html">Catálogo</a></li>
        <li><a href="carrito.html">Carrito</a></li>
        <li><strong>👤 ${usuario.nombre}</strong></li>
        <li><a href="#" onclick="cerrarSesion()">Cerrar sesión</a></li>
      `;
    }
  }
}

function cerrarSesion() {
  localStorage.removeItem('usuarioLogueado');
  localStorage.removeItem('carrito');
  window.location.href = 'index.html';
}

// ------------------------------
// 6. Gestión de usuarios (admin)
// ------------------------------


function eliminarUsuario(id) {
  if (confirm("¿Seguro que deseas eliminar este usuario?")) {
    fetch(`/api/usuarios/${id}`, {
      method: 'DELETE'
    })
    .then(() => {
      alert("Usuario eliminado");
    })
    .catch(err => console.error("Error al eliminar:", err));
  }
}

