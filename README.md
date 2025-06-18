# 🖥️ Tecnopc - Plataforma Web

Proyecto desarrollado como parte del programa de formación del SENA - Ficha GA7-220501096.  
Tecnopc es una tienda virtual de productos tecnológicos con funcionalidades como registro de usuarios, login, catálogo de productos y carrito de compras.

## 🚀 Tecnologías Utilizadas

- **Backend:** Java 17 + Spring Boot
- **Base de Datos:** PostgreSQL
- **Frontend:** HTML, CSS, JavaScript
- **Herramientas:** Git, GitHub, Postman, Spring Initializr

## 📂 Estructura del Proyecto

```
/src
 ├── controller/
 ├── models/
 ├── repositories/
 ├── services/
/static
 ├── css/style.css
 ├── js/main.js
 ├── img/
/templates
 ├── index.html
 ├── login.html
 ├── registro.html
 ├── carrito.html
 ├── catalogo.html
```

## 📌 Funcionalidades

- ✔ Registro de usuarios
- ✔ Inicio de sesión
- ✔ Visualización de productos destacados
- ✔ Agregar productos al carrito (localStorage)
- ✔ Eliminación de usuarios (admin)
- ✔ Menú dinámico con usuario activo

## ⚙️ Configuración y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/alexherrera9612/Proyecto_Sena_2025Java.git
   cd Proyecto_Sena_2025Java
   ```

2. Configurar la base de datos PostgreSQL:
   - Crear una base de datos `tecnopc`
   - Configurar `application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/tecnopc
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Ejecutar el proyecto:
   - Desde un IDE como IntelliJ o VSCode
   - O desde terminal:
     ```bash
     ./mvnw spring-boot:run
     ```

4. Acceder a la app:
   ```
   http://localhost:8080/
   ```

## 📸 Capturas (agregar en el futuro)

- Registro funcional ✅  
- Inicio de sesión con nombre en menú ✅  
- Carrito con productos agregados ✅  
- CRUD de usuarios desde Postman ✅

## 🧠 Autor

- **Alex Herrera Rodríguez**  
- Ficha SENA: GA7-220501096  
- Proyecto académico para evidencia AA3

---

## 🛠️ En desarrollo

- [ ] Encriptación de contraseñas
- [ ] Carrito con persistencia en backend
- [ ] Panel de administración completo
- [ ] Vista de compras realizadas

---

## 🌐 Enlace

Repositorio oficial:  
🔗 [github.com/alexherrera9612/Proyecto_Sena_2025Java](https://github.com/alexherrera9612/Proyecto_Sena_2025Java)