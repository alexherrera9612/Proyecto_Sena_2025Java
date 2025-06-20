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
      <img src={`/${item.imagen}`} alt={item.nombre} className="carrito-img" />
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

          <button onClick={confirmarCompra}>Confirmar compra</button>
        </>
      )}
    </main>
  );
}

export default Carrito;
