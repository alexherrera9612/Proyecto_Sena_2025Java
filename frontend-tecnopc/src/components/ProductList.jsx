// src/components/ProductList.jsx

import React, { useState, useEffect } from 'react';
import ProductCard from './ProductCard';

function ProductList() {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    // Cargar productos como en main.js
    const productosEjemplo = [
      { id: 1, nombre: 'Laptop Lenovo', precio: 2200000, imagen: 'img/laptop1.jpg' },
      { id: 2, nombre: 'Monitor ASUS', precio: 950000, imagen: 'img/monitor1.jpg' },
      { id: 3, nombre: 'Teclado Mecánico', precio: 250000, imagen: 'img/teclado1.jpg' },
      { id: 4, nombre: 'Mouse Logitech', precio: 180000, imagen: 'img/mouse1.jpg' }
    ];
    setProductos(productosEjemplo);
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
