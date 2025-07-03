package com.proyecto_sena.services;

import com.proyecto_sena.models.ProductoModel;
import com.proyecto_sena.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoModel> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<ProductoModel> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoModel guardar(ProductoModel producto) {
        return productoRepository.save(producto);
    }
    public ProductoModel obtenerProductoExistente(Long id) {
    return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto con ID " + id + " no encontrado"));
    }
    public List<ProductoModel> buscarDisponibles() {
    return productoRepository.findByStockGreaterThan(0);
    }

    public List<ProductoModel> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

    public List<ProductoModel> buscarPorRangoPrecio(double min, double max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    public List<ProductoModel> buscarConImagen() {
        return productoRepository.findByImagenUrlIsNotNull();
    }
    public void eliminarTodos() {
    productoRepository.deleteAll();
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    public List<ProductoModel> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }   
}
