// src/components/ProductCard.jsx
import React from 'react';
import './ProductCard.css'; // Opcional si usas estilos separados

function ProductCard({ producto, onAddToCart }) {
  return (
    <div className="producto">
      <img
        src={producto.imagenUrl || 'https://via.placeholder.com/150'}
        alt={producto.nombre}
      />
      <h4>{producto.nombre}</h4>
      <p>${producto.precio.toLocaleString()}</p>
      <button onClick={() => onAddToCart(producto)}>Agregar</button>
    </div>
  );
}

export default ProductCard;
// This component represents a single product card.
// It displays the product's image, name, price, and an "Add to Cart" button.   