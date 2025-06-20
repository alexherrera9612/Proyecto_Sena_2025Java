import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function LoginForm() {
  const [correo, setCorreo] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [mensaje, setMensaje] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/api/usuarios/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ correo, contrasena })
    })
      .then(res => {
        if (!res.ok) throw new Error('Credenciales incorrectas');
        return res.json();
      })
      .then(usuario => {
        localStorage.setItem('usuarioLogueado', JSON.stringify(usuario));
        window.location.href = "/catalogo";
        setMensaje('✅ Inicio de sesión exitoso');
        setTimeout(() => navigate('/catalogo'), 1500);
      })
      .catch(err => {
        setMensaje(`❌ ${err.message}`);
      });
  };

  return (
    <main>
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <label>Correo electrónico:</label>
        <input
          type="email"
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
          required
        />

        <label>Contraseña:</label>
        <input
          type="password"
          value={contrasena}
          onChange={(e) => setContrasena(e.target.value)}
          required
        />

        <button type="submit">Entrar</button>
      </form>

      {mensaje && <p style={{ marginTop: '10px' }}>{mensaje}</p>}
    </main>
  );
}

export default LoginForm;
