package com.proyecto_sena.controller;

import com.proyecto_sena.models.ProductoModel;
import com.proyecto_sena.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Listar todos los productos
    @GetMapping
    public List<ProductoModel> listarProductos() {
        return productoService.listarTodos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> obtenerProducto(@PathVariable Long id) {
        Optional<ProductoModel> producto = productoService.obtenerPorId(id);
        return producto.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Crear producto
    @PostMapping
    public ProductoModel crearProducto(@RequestBody ProductoModel producto) {
        return productoService.guardar(producto);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        Optional<ProductoModel> existente = productoService.obtenerPorId(id);
        if (existente.isPresent()) {
            producto.setId(id);
            return ResponseEntity.ok(productoService.guardar(producto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Optional<ProductoModel> producto = productoService.obtenerPorId(id);
        if (producto.isPresent()) {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar productos por nombre
    @GetMapping("/buscar")
    public List<ProductoModel> buscarPorNombre(@RequestParam String nombre) {
        return productoService.buscarPorNombre(nombre);
    }
    @PostMapping("/batch")
    public List<ProductoModel> crearProductos(@RequestBody List<ProductoModel> productos) {
    return productos.stream()
            .map(productoService::guardar)
            .toList();
    }
    @DeleteMapping("/eliminar-todos")
    public ResponseEntity<?> eliminarTodosLosProductos() {
    productoService.eliminarTodos();
    return ResponseEntity.ok("Todos los productos han sido eliminados.");
    }


    // Nuevos endpoints de búsqueda adicionales

    // Buscar productos disponibles (stock > 0)
    @GetMapping("/disponibles")
    public List<ProductoModel> productosDisponibles() {
        return productoService.buscarDisponibles();
    }

    // Buscar productos por categoría
    @GetMapping("/categoria")
    public List<ProductoModel> productosPorCategoria(@RequestParam String categoria) {
        return productoService.buscarPorCategoria(categoria);
    }

    // Buscar por rango de precios
    @GetMapping("/precio")
    public List<ProductoModel> productosPorPrecio(@RequestParam double min, @RequestParam double max) {
        return productoService.buscarPorRangoPrecio(min, max);
    }

    // Buscar productos que tienen imagen
    @GetMapping("/con-imagen")
    public List<ProductoModel> productosConImagen() {
        return productoService.buscarConImagen();
    }
}
// End of file: src/main/java/com/proyecto_sena/controller/ProductoController.java
// This controller provides endpoints for managing products, including listing, retrieving, creating, updating, and deleting products.
// It also includes additional endpoints for searching products by name, availability, category, price range, and those with images.