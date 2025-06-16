package com.proyecto_sena.controller;

import com.proyecto_sena.models.ProductoModel;
import com.proyecto_sena.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoModel> listarProductos() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ProductoModel> obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public ProductoModel crearProducto(@RequestBody ProductoModel producto) {
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public ProductoModel actualizarProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        producto.setId(id);
        return productoService.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}
