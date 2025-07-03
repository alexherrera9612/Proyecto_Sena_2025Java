package com.proyecto_sena.models;

import jakarta.persistence.*;       // Anotaciones JPA para persistencia
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la tabla 'usuario' en la base de datos.
 * Utiliza JPA para mapeo objeto-relacional (ORM).
 * Utiliza Lombok para generar automáticamente getters, setters y constructor vacío.
 */
@Data // Genera automáticamente getters y setters para todos los campos
@AllArgsConstructor // Genera constructor con todos los argumentos
@NoArgsConstructor          // Genera constructor sin argumentos
@Entity                     // Indica que esta clase es una entidad JPA
@Table(name = "usuario")   // Define el nombre de la tabla en la base de datos
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID autogenerado por la base de datos (clave primaria)

    private String nombre;  // Nombre completo del usuario

    @Column(unique = true)
    private String usuario; // Nombre de usuario único

    @Column(unique = true)
    private String correo;  // Correo electrónico único

    private String contrasena; // Contraseña del usuario

    private String rol = "CLIENTE"; // Rol por defecto (puede ser CLIENTE, ADMIN, etc.)
}

// Esta clase UsuarioModel representa un usuario en el sistema.
// Utiliza JPA para mapearse a la tabla 'usuario' en la base de datos.
// Incluye campos como id, nombre, usuario, correo, contraseña y rol.
// Lombok se usa para simplificar el código generando automáticamente los métodos getter, setter y un constructor sin argumentos.
// El campo 'rol' tiene un valor por defecto de "CLIENTE", pero puede ser modificado según las necesidades del sistema. 