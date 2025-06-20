// src/components/ProductCard.jsx
import React from 'react';

import './ProductCard.css'; // Si deseas agregarle estilos

function ProductCard({ producto, onAddToCart }) {
  return (
    <div className="producto">
      <img src={producto.imagen} alt={producto.nombre} />
      <h4>{producto.nombre}</h4>
      <p>${producto.precio.toLocaleString()}</p>
      <button onClick={() => onAddToCart(producto)}>Agregar</button>
    </div>
  );
}


export default ProductCard;
