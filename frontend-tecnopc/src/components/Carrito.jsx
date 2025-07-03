// src/components/Carrito.jsx

import React, { useEffect, useState } from 'react';

function Carrito() {
  const [carrito, setCarrito] = useState([]);
  const [total, setTotal] = useState(0);

  useEffect(() => {
    const guardado = localStorage.getItem('lista-carrito');
    if (guardado) {
      const productos = JSON.parse(guardado);
      setCarrito(productos);
      const suma = productos.reduce((acc, item) => acc + item.precio, 0);
      setTotal(suma);
    }
  }, []);

  const confirmarCompra = () => {
    alert("¡Gracias por tu compra!");
    localStorage.removeItem('lista-carrito');
    setCarrito([]);
    setTotal(0);
  };

  const vaciarCarrito = () => {
    if (window.confirm("¿Estás seguro de que deseas vaciar el carrito?")) {
      localStorage.removeItem('lista-carrito');
      setCarrito([]);
      setTotal(0);
    }
  };

  return (
    <main>
      <h2>Tu carrito</h2>

      {carrito.length === 0 ? (
        <p>El carrito está vacío.</p>
      ) : (
        <>
          <ul className="carrito-lista">
            {carrito.map((item, index) => (
              <li key={index} className="carrito-item">
                <img
                  src={item.imagenUrl || 'https://via.placeholder.com/100'}
                  alt={item.nombre}
                  className="carrito-img"
                />
                <div>
                  <strong>{item.nombre}</strong>
                  <p>${item.precio.toLocaleString()}</p>
                </div>
              </li>
            ))}
          </ul>

          <div className="total">
            <strong>Total:</strong> ${total.toLocaleString()}
          </div>

          <div style={{ display: 'flex', gap: '12px', marginTop: '16px' }}>
            <button onClick={confirmarCompra}>Confirmar compra</button>
            <button onClick={vaciarCarrito} style={{ background: '#dc3545' }}>
              Vaciar carrito 🗑️
            </button>
          </div>
        </>
      )}
    </main>
  );
}

export default Carrito;
// This component displays the shopping cart, allowing users to view items, confirm purchases, or empty the cart.
// It retrieves the cart items from local storage, calculates the total price, and provides buttons for
// confirming the purchase or emptying the cart. The cart items are displayed with their images, names, and prices.
// The component uses React hooks for state management and side effects, ensuring the cart is updated correctly
// when items are added or removed. The total price is formatted for better readability.
// The component also includes basic styling for the cart items and buttons, enhancing the user experience.
// The cart is initially empty, and the user can add items to it from other parts of the application.
// The component handles edge cases, such as confirming an empty cart or displaying a placeholder image if
