/**package com.application.rest.RestTecnoPc.controller;

import com.application.rest.RestTecnoPc.entities.Product;
import com.application.rest.RestTecnoPc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.application.rest.RestTecnoPc.controller.dto.LoginDTO;
import com.application.rest.RestTecnoPc.controller.dto.ProductDTO;
import com.application.rest.RestTecnoPc.controller.dto.UserDTO;
import com.application.rest.RestTecnoPc.service.IProductService;
import com.application.rest.RestTecnoPc.service.IUserService;


@RestController
public class HomeController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;


    /*@GetMapping("/")
    public String home() {
        return "Welcome to the Home Page!";
    }*/
    /**
    @GetMapping("/findAll")
        public ResponseEntity<?> findAll() {    
            // Endpoint to find all products
            List<ProductDTO> productList = productService.findAll()
                    .stream()
                    .map(product -> ProductDTO.builder()
                            .id(product.getId())
                            .nombre(product.getNombre())
                            .descripcion(product.getDescripcion())
                            .stock(product.getStock())
                            .imagenUrl(product.getImagenUrl())
                            .price(product.getPrice())
                            .subcategoryId(product.getSubcategoria().getId())
                            .build())
                    .toList();
            return ResponseEntity.ok(productList);
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
            String correo = loginDTO.getEmail();
            String contrasena = loginDTO.getContrasena();
            if (correo == null || contrasena == null) {
                return ResponseEntity.badRequest().body(Map.of("mensaje", "El email y la contraseña son obligatorios"));
            }
            Optional<User> userOptional = userService.findByEmailAndContrasena(correo, contrasena);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return ResponseEntity.ok().body(Map.of("mensaje", "Inicio de sesión exitoso"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
        }

         @PostMapping("/save") // Endpoint to save a user
        public ResponseEntity<?> save(@RequestBody UserDTO userDTO){
            if (userDTO.getNombre() == null || userDTO.getApellido() == null ||
            userDTO.getEmail() == null || userDTO.getTelefono() == null || userDTO.getContrasena() == null) {
                return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
            }
            if (!userService.findByEmail(userDTO.getEmail()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El email ya está registrado");
            }
            if (!userService.findByTelefono(userDTO.getTelefono()).isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El teléfono ya está registrado");
            }
            userService.save(User.builder()
                    .nombre(userDTO.getNombre())
                    .apellido(userDTO.getApellido())
                    .email(userDTO.getEmail())
                    .telefono(userDTO.getTelefono())
                    .contrasena(userDTO.getContrasena())
                    .build());
                return ResponseEntity.created(URI.create("/api/user/save/" + userDTO.getEmail()))
                         .body(Map.of("mensaje", "Usuario registrado correctamente: " + userDTO.getEmail()));
    }
    
    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        // Endpoint to find a product by ID
        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .nombre(product.getNombre())
                    .descripcion(product.getDescripcion())
                    .stock(product.getStock())
                    .imagenUrl(product.getImagenUrl())
                    .price(product.getPrice())
                    .subcategoryId(product.getSubcategoria().getId())
                    .build();
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.notFound().build();
    }

}*/