
    package com.application.rest.RestTecnoPc.controller;

    import com.application.rest.RestTecnoPc.controller.dto.UserDTO;
    import com.application.rest.RestTecnoPc.controller.dto.LoginDTO;
    import com.application.rest.RestTecnoPc.entities.User;
    import com.application.rest.RestTecnoPc.service.IUserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.net.URI;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;

    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/api/user")
    public class UserController {

        @Autowired
        private IUserService userService;

        @GetMapping("/find/{id}") // Endpoint to find a user by ID
        public ResponseEntity<?> findById(@PathVariable Long id){
            Optional <User> userOptional = userService.findById(id);

            if(userOptional.isPresent()){
                User user = userOptional.get();
                UserDTO userDTO = UserDTO.builder()
                        .id(user.getId())
                        .nombre(user.getNombre())
                        .apellido(user.getApellido())
                        .email(user.getEmail())
                        .telefono(user.getTelefono())
                        .build();
                return ResponseEntity.ok(userDTO);

            }
            return ResponseEntity.notFound().build();
        }
        @GetMapping("/findAll") // Endpoint to find all users
        public ResponseEntity<?> findAll(){
    
                List<UserDTO> userList = userService.findAll()
                .stream()
                    .map(user -> UserDTO.builder()
                            .id(user.getId())
                            .nombre(user.getNombre())
                            .apellido(user.getApellido())
                            .email(user.getEmail())
                            .telefono(user.getTelefono())
                            .build())
                    .toList();
                return ResponseEntity.ok(userList);
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
    
    @PutMapping("/update/{id}") // Endpoint to update a user
        public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {

            if (userDTO.getNombre() == null || userDTO.getApellido() == null ||
            userDTO.getEmail() == null || userDTO.getTelefono() == null) {
                return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
            }

            Optional<User> userOptional = userService.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setNombre(userDTO.getNombre());
                user.setApellido(userDTO.getApellido());
                user.setEmail(userDTO.getEmail());
                user.setTelefono(userDTO.getTelefono());
                user.setContrasena(userDTO.getContrasena());
                userService.save(user);
                return ResponseEntity.ok().body("Registro actualizado correctamente");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado con ID: " + id);

            }
        @DeleteMapping("/delete/{id}") // Endpoint to delete a user by ID
        public ResponseEntity<?> deleteById(@PathVariable Long id) {
            Optional<User> userOptional = userService.findById(id);
            if (userOptional.isPresent()) {
                userService.deleteById(id);
                return ResponseEntity.ok().body("Usuario eliminado correctamente");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con ID: " + id);
        }

        @DeleteMapping("/deleteAll") // Endpoint to delete all users
        public ResponseEntity<?> deleteAll() {       
            userService.findAll().forEach(user -> userService.deleteById(user.getId()));
            return ResponseEntity.ok().body("Todos los usuarios han sido eliminados");
        }

        @GetMapping("/findByEmail/{email}")
        public ResponseEntity<?> findByEmail(@PathVariable String email) {
        List<User> matchedUsers = userService.findByEmail(email); // solo pasas "an", no "an%"

        List<UserDTO> dtos = matchedUsers.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .nombre(user.getNombre())
                        .apellido(user.getApellido())
                        .email(user.getEmail())
                        .telefono(user.getTelefono())
                        .build())
                .toList();

        if (!dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron usuarios cuyo correo comience con: " + email);
        
        }

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

        @GetMapping("/findByTelefono/{telefono}")
        public ResponseEntity<?> findByTelefono(@PathVariable String telefono) {
            List<User> matchedUsers = userService.findByTelefono(telefono);

                List<UserDTO> dtos = matchedUsers.stream()
                        .map(user -> UserDTO.builder()
                                .id(user.getId())
                        .nombre(user.getNombre())
                        .apellido(user.getApellido())
                        .email(user.getEmail())
                        .telefono(user.getTelefono())
                        .build())
                        .toList();
                        
                 if (!dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron usuarios cuyo teléfono comience con: " + telefono);
        
        }
    }
 }
