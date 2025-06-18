package com.proyecto_sena.models;

import jakarta.persistence.*;       // Anotaciones JPA para persistencia
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la tabla 'usuario' en la base de datos.
 * Utiliza JPA para mapeo objeto-relacional (ORM).
 * Utiliza Lombok para generar automáticamente getters, setters y constructor vacío.
 */
@NoArgsConstructor          // Genera constructor sin argumentos
@Getter                     // Genera automáticamente todos los getters
@Setter                     // Genera automáticamente todos los setters
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

    /**
     * Setter explícito para el ID.
     * Aunque Lombok lo genera, se incluye manualmente para garantizar acceso
     * desde controladores (por ejemplo, en actualizaciones PUT).
     */
    public void setId(Long id) {
        this.id = id;
    }
}
