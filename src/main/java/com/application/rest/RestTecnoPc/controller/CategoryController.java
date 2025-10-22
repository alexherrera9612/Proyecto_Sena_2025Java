package com.application.rest.RestTecnoPc.controller;

import com.application.rest.RestTecnoPc.controller.dto.CategoryDTO;
import com.application.rest.RestTecnoPc.entities.Category;
import com.application.rest.RestTecnoPc.service.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .nombre(category.getNombre())
                    .descripcion(category.getDescripcion())
                    .build();
            return ResponseEntity.ok(categoryDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<CategoryDTO> categoryList = categoryService.findAll()
                .stream()
                .map(category -> CategoryDTO.builder()
                        .id(category.getId())
                        .nombre(category.getNombre())
                        .descripcion(category.getDescripcion())
                        .build())
                .toList();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO.getNombre() == null || categoryDTO.getDescripcion() == null) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
        }
        Category category = Category.builder()
                .nombre(categoryDTO.getNombre())
                .descripcion(categoryDTO.getDescripcion())
                .build();
        categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoría creada correctamente");
    }

    @PostMapping("/saveAll")
public ResponseEntity<?> saveAll(@RequestBody List<CategoryDTO> categoriesDTO) {
    StringBuilder errores = new StringBuilder();

    for (int i = 0; i < categoriesDTO.size(); i++) {
        CategoryDTO dto = categoriesDTO.get(i);

        // Validación básica
        if (dto.getNombre() == null || dto.getDescripcion() == null) {
            errores.append("Categoría en posición ")
                   .append(i)
                   .append(" tiene datos inválidos o faltantes.\n");
            continue;
        }

        // Guardar categoría
        categoryService.save(Category.builder()
            .nombre(dto.getNombre())
            .descripcion(dto.getDescripcion())
            .build()
        );
    }

    if (errores.length() > 0) {
        return ResponseEntity.badRequest().body("Errores encontrados:\n" + errores);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("Todas las categorías fueron guardadas correctamente.");
}

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setNombre(categoryDTO.getNombre());
            category.setDescripcion(categoryDTO.getDescripcion());
            categoryService.save(category);
            return ResponseEntity.ok("Categoría actualizada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Categoría no encontrada con ID: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.ok("Categoría eliminada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Categoría no encontrada con ID: " + id);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        categoryService.findAll().forEach(c -> categoryService.deleteById(c.getId()));
        return ResponseEntity.ok("Todas las categorías han sido eliminadas");
    }
}
