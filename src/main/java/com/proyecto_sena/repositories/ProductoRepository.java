package com.proyecto_sena.repositories;

import com.proyecto_sena.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {

    // Buscar por coincidencias en el nombre (ignora mayúsculas/minúsculas)
    List<ProductoModel> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por categoría (opcional si tienes ese campo más adelante)
    List<ProductoModel> findByCategoriaContainingIgnoreCase(String categoria);

    // Buscar por rango de precios
    List<ProductoModel> findByPrecioBetween(double min, double max);

    // Buscar productos con imágenes (por si quieres diferenciar productos sin imagen asignada aún)
    List<ProductoModel> findByImagenUrlIsNotNull();

    // Buscar por disponibilidad (stock mayor a cero)
    List<ProductoModel> findByStockGreaterThan(int cantidad);
}
