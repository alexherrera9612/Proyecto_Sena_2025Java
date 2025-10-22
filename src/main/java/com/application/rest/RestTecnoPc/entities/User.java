package com.application.rest.RestTecnoPc.entities;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "last_name")
    private String apellido;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "password")
    private String contrasena;
    @Column(name = "phone", unique = true)
    private String telefono;


}
