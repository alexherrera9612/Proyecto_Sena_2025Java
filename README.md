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
     # Puerto del servidor
     server.port=8080

     # Configuración de la base de datos PostgreSQL
     spring.datasource.url=jdbc:mysql://localhost:3306/bd_tecnopc?useSSL=false&serverTimezone=UTC
     spring.datasource.username=root
     spring.datasource.password=root
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     
     # Configuración JPA
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
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

- **Alex Rodríguez**  
- Ficha SENA: 290881
- Proyecto académico para Tecnopc

---

## 🛠️ En desarrollo

- [ ] Encriptación de contraseñas
- [ ] Carrito con persistencia en backend
- [ ] Panel de administración completo
- [ ] Vista de compras realizadas

---
# 📦 Frontend Tecnopc - React

Este es el frontend del proyecto Tecnopc, desarrollado con React para consumir los servicios web del backend en Spring Boot.

## 🚀 Requisitos

- Node.js >= 18
- npm >= 9 (viene con Node)
- Backend corriendo en: `http://localhost:8080`

## ⚙️ Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tecnopc-frontend.git
   cd tecnopc-frontend
Instala las dependencias:

bash
Copiar
Editar
npm install
▶️ Ejecución
Para iniciar el frontend en modo desarrollo:

bash
Copiar
Editar
npm start
Esto abrirá la aplicación en tu navegador en http://localhost:3000.

🔗 Conexión con el Backend
Asegúrate de que el backend (Spring Boot) esté corriendo en http://localhost:8080.

Las rutas consumidas están configuradas para ese origen (/api/usuarios, /api/usuarios/login, etc).

## 🌐 Enlace

Repositorio oficial:  
🔗 [github.com/alexherrera9612/Proyecto_Sena_2025Java](https://github.com/alexherrera9612/Proyecto_Sena_2025Java)
