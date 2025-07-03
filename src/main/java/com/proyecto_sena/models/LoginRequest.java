// Paquete al que pertenece esta clase
package com.proyecto_sena.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


// Clase auxiliar que representa la solicitud de inicio de sesión (login)
public class LoginRequest {

    // Campo para almacenar el correo del usuario
    private String correo;

    // Campo para almacenar la contraseña del usuario
    private String contrasena;

}
// Esta clase LoginRequest se utiliza para encapsular los datos necesarios para iniciar sesión en la aplicación.
// Contiene dos campos: correo y contrasena, junto con sus respectivos getters y setters.
// Esto permite que el controlador reciba una solicitud de inicio de sesión de manera estructurada.