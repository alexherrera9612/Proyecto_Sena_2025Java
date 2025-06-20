import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function RegistroForm() {
  const [formulario, setFormulario] = useState({
    nombre: '',
    usuario: '',
    correo: '',
    contrasena: ''
  });

  const [mensaje, setMensaje] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormulario({ ...formulario, [e.target.id]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/api/usuarios', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formulario)
    })
      .then(res => {
        if (!res.ok) return res.json().then(err => { throw new Error(Object.values(err).join(', ')); });
        return res.json();
      })
      .then(() => {
        setMensaje('✅ Usuario registrado exitosamente');
        setFormulario({ nombre: '', usuario: '', correo: '', contrasena: '' });
        setTimeout(() => navigate('/login'), 1500);
      })
      .catch(err => {
        setMensaje('❌ Error: ' + err.message);
      });
  };

  return (
    <main>
      <h2>Crear cuenta</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="nombre">Nombre completo:</label>
        <input id="nombre" type="text" value={formulario.nombre} onChange={handleChange} required />

        <label htmlFor="correo">Correo electrónico:</label>
        <input id="correo" type="email" value={formulario.correo} onChange={handleChange} required />

        <label htmlFor="usuario">Usuario:</label>
        <input id="usuario" type="text" value={formulario.usuario} onChange={handleChange} required />

        <label htmlFor="contrasena">Contraseña:</label>
        <input id="contrasena" type="password" value={formulario.contrasena} onChange={handleChange} required />

        <button type="submit">Registrarse</button>
      </form>

      {mensaje && <p>{mensaje}</p>}
    </main>
  );
}

export default RegistroForm;
