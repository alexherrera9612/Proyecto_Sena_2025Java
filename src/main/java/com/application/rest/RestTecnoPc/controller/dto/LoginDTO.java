package com.application.rest.RestTecnoPc.controller.dto;
import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class LoginDTO {
   
    private String email;
    private String contrasena;
    
}
