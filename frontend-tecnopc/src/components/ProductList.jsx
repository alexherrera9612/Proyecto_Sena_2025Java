// src/components/ProductList.jsx

import React, { useState, useEffect } from 'react';
import ProductCard from './ProductCard';

function ProductList() {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/productos') // Cambia el puerto si tu backend usa otro
      .then(res => {
        if (!res.ok) {
          throw new Error('Error al cargar productos');
        }
        return res.json();
      })
      .then(data => setProductos(data))
      .catch(err => console.error('Error:', err));
  }, []);

  const agregarAlCarrito = (producto) => {
    const carrito = JSON.parse(localStorage.getItem('lista-carrito')) || [];
    carrito.push(producto);
    localStorage.setItem('lista-carrito', JSON.stringify(carrito));
    alert(`${producto.nombre} agregado al carrito`);
  };

  return (
    <div className="producto-grid">
      {productos.map(p => (
        <ProductCard key={p.id} producto={p} onAddToCart={agregarAlCarrito} />
      ))}
    </div>
  );
}

export default ProductList;
// This component fetches the list of products from the backend and displays them using the ProductCard component.
// It also handles adding products to the cart by storing them in localStorage.