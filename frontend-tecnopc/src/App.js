// App.js
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

  useEffect(() => {
    const guardado = localStorage.getItem('usuarioLogueado');
    if (guardado) {
      setUsuario(JSON.parse(guardado));
    }
  }, []);

  const cerrarSesion = () => {
    localStorage.removeItem('usuarioLogueado');
    setUsuario(null);
    window.location.href = '/';
    alert('Sesión cerrada exitosamente');
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
                  <li><a href="#" onClick={cerrarSesion}>Cerrar sesión</a></li>
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
                {[{
                  id: 99,
                  nombre: 'PC Gamer Ryzen 7',
                  precio: 4500000,
                  imagen: 'img/pcgamer.jpg'
                }, {
                  id: 100,
                  nombre: 'Combo Oficina',
                  precio: 1800000,
                  imagen: 'img/combo.jpg'
                }].map(prod => (
                  <ProductCard key={prod.id} producto={prod} onAddToCart={(p) => {
                    const carrito = JSON.parse(localStorage.getItem('lista-carrito')) || [];
                    carrito.push(p);
                    localStorage.setItem('lista-carrito', JSON.stringify(carrito));
                    alert(`${p.nombre} agregado al carrito`);
                  }} />
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
