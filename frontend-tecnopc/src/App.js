import React, { useEffect, useState } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from 'react-router-dom';
import ProductList from './components/ProductList';
import Carrito from './components/Carrito';
import LoginForm from './components/LoginForm';
import RegistroForm from './components/RegistroForm';
import ProductCard from './components/ProductCard';
import './style.css';

function App() {
  const [usuario, setUsuario] = useState(null);
  const [destacados, setDestacados] = useState([]);

  useEffect(() => {
    const guardado = localStorage.getItem('usuarioLogueado');
    if (guardado) {
      setUsuario(JSON.parse(guardado));
    }

    // Cargar productos destacados desde la API (solo 4 primeros)
    fetch('http://localhost:8080/api/productos')
      .then(res => res.json())
      .then(data => {
        const primeros = data.slice(0, 4); // los primeros 4
        setDestacados(primeros);
      })
      .catch(err => console.error("Error cargando productos:", err));
  }, []);

  const cerrarSesion = () => {
    localStorage.removeItem('usuarioLogueado');
    setUsuario(null);
    window.location.href = '/';
    alert('Sesión cerrada exitosamente');
  };

  const agregarAlCarrito = (producto) => {
    const carrito = JSON.parse(localStorage.getItem('lista-carrito')) || [];
    carrito.push(producto);
    localStorage.setItem('lista-carrito', JSON.stringify(carrito));
    alert(`${producto.nombre} agregado al carrito`);
  };

  return (
    <Router>
      <div className="App">
        <nav>
          <div className="navbar-container">
            <div className="logo-text">
              <img src="/img/logo_tecnopc.png" alt="Tecnopc Logo" className="logo" />
            </div>
            <ul className="nav-links">
              <li><Link to="/">Inicio</Link></li>
              <li><Link to="/catalogo">Catálogo</Link></li>
              <li><Link to="/carrito">Carrito</Link></li>
              {!usuario && <li><Link to="/login">Login</Link></li>}
              {!usuario && <li><Link to="/registro">Registro</Link></li>}
              {usuario && (
                <>
                  <li><strong>👤 {usuario.nombre}</strong></li>
                  <li><button onClick={cerrarSesion} className="logout-link">Cerrar sesión</button></li>
                </>
              )}
            </ul>
          </div>
        </nav>

        <Routes>
          <Route path="/" element={
            <div>
              <h2>Productos Destacados</h2>
              <div className="producto-grid">
                {destacados.map(prod => (
                  <ProductCard
                    key={prod.id}
                    producto={{
                      ...prod,
                      imagen: prod.imagenUrl // para mantener compatibilidad con ProductCard
                    }}
                    onAddToCart={agregarAlCarrito}
                  />
                ))}
              </div>
              <footer className="footer">
                <div className="footer-container">
                  <p>© {new Date().getFullYear()} TecnoPC.</p>
                  <p>Desarrollado por ALEXANDER RODRIGUEZ</p>
                </div>
              </footer>
            </div>
          } />
          <Route path="/catalogo" element={<ProductList />} />
          <Route path="/carrito" element={<Carrito />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/registro" element={<RegistroForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
// This is the main App component that sets up the routing and navigation for the application.
// It includes a navigation bar, routes for different pages, and displays featured products on the home page.
// It also handles user login/logout and adding products to the cart.
// The featured products are fetched from the backend and displayed using the ProductCard component.
// The application uses React Router for navigation and localStorage for session management and cart storage.