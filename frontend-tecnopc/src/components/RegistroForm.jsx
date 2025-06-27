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
  const [tipoMensaje, setTipoMensaje] = useState(''); // 'exito' | 'error'
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormulario({ ...formulario, [e.target.id]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validación básica en frontend
    const { nombre, usuario, correo, contrasena } = formulario;
    if (!nombre || !usuario || !correo || !contrasena) {
      setTipoMensaje('error');
      setMensaje('⚠️ Todos los campos son obligatorios.');
      return;
    }

    if (contrasena.length < 6) {
      setTipoMensaje('error');
      setMensaje('⚠️ La contraseña debe tener al menos 6 caracteres.');
      return;
    }

    // Enviar al backend
    fetch('http://localhost:8080/api/usuarios', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formulario)
    })
      .then(res => {
        if (!res.ok) {
          return res.json().then(err => {
            throw new Error(Object.values(err).join(', '));
          });
        }
        return res.json();
      })
      .then(() => {
        setTipoMensaje('exito');
        setMensaje('✅ Usuario registrado exitosamente. Redirigiendo...');
        setFormulario({ nombre: '', usuario: '', correo: '', contrasena: '' });
        setTimeout(() => navigate('/login'), 1500);
      })
      .catch(err => {
        setTipoMensaje('error');
        setMensaje(err.message);
      });
  };

  const estiloMensaje = {
    padding: '10px',
    borderRadius: '5px',
    marginTop: '10px',
    color: 'white',
    backgroundColor: tipoMensaje === 'exito' ? 'green' : 'crimson'
  };

  return (
    <main style={{ maxWidth: '400px', margin: 'auto', padding: '20px' }}>
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

        <button type="submit" style={{ marginTop: '10px' }}>Registrarse</button>
      </form>

      {mensaje && <div style={estiloMensaje}>{mensaje}</div>}
    </main>
  );
}

export default RegistroForm;
