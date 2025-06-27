import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function LoginForm() {
  const [correo, setCorreo] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [mensaje, setMensaje] = useState('');
  const [tipoMensaje, setTipoMensaje] = useState(''); // 'exito' | 'error'
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!correo || !contrasena) {
      setTipoMensaje('error');
      setMensaje('⚠️ Todos los campos son obligatorios.');
      return;
    }

    fetch('http://localhost:8080/api/usuarios/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ correo, contrasena })
    })
      .then(async res => {
        if (!res.ok) {
          let mensajeError = 'Credenciales incorrectas.';
          const texto = await res.text();
          try {
            const data = JSON.parse(texto);
            mensajeError = data?.mensaje || mensajeError;
          } catch {
            // No es JSON, mantener mensaje por defecto
          }
          throw new Error(mensajeError);
        }

        return res.json();
      })
      .then(usuario => {
        localStorage.setItem('usuarioLogueado', JSON.stringify(usuario));
        setTipoMensaje('exito');
        setMensaje('✅ Inicio de sesión exitoso. Redirigiendo...');
        setTimeout(() => navigate('/catalogo'), 1500);
      })
      .catch(err => {
        setTipoMensaje('error');
        setMensaje(`❌ Credenciales incorrectas: ${err.message}`);
      });
  };

  const estiloMensaje = {
    marginTop: '10px',
    padding: '10px',
    borderRadius: '5px',
    backgroundColor: tipoMensaje === 'exito' ? 'green' : 'crimson',
    color: 'white'
  };

  return (
    <main style={{ maxWidth: '400px', margin: 'auto', padding: '20px' }}>
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="correo">Correo electrónico:</label>
        <input
          id="correo"
          type="email"
          value={correo}
          onChange={(e) => setCorreo(e.target.value)}
          required
        />

        <label htmlFor="contrasena">Contraseña:</label>
        <input
          id="contrasena"
          type="password"
          value={contrasena}
          onChange={(e) => setContrasena(e.target.value)}
          required
        />

        <button type="submit" style={{ marginTop: '10px' }}>Entrar</button>
      </form>

      {mensaje && <div style={estiloMensaje}>{mensaje}</div>}
    </main>
  );
}

export default LoginForm;
