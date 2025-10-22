package com.application.rest.RestTecnoPc.controller;

import com.application.rest.RestTecnoPc.controller.dto.SubcategoryDTO;
import com.application.rest.RestTecnoPc.entities.Subcategory;
import com.application.rest.RestTecnoPc.service.ICategoryService;
import com.application.rest.RestTecnoPc.service.ISubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.application.rest.RestTecnoPc.entities.Category;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/subcategory")
public class SubcategoryController {

    @Autowired
    private ISubCategoryService subcategoryService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Subcategory> subcategoryOptional = subcategoryService.findById(id);
        if (subcategoryOptional.isPresent()) {
            Subcategory sub = subcategoryOptional.get();
            SubcategoryDTO dto = SubcategoryDTO.builder()
                    .id(sub.getId())
                    .nombre(sub.getNombre())
                    .categoryId(sub.getCategory().getId())
                    .build();
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Subcategory> subcategories = subcategoryService.findAll();
        List<SubcategoryDTO> dtos = subcategories.stream().map(sub -> SubcategoryDTO.builder()
                .id(sub.getId())
                .nombre(sub.getNombre())
                .categoryId(sub.getCategory().getId())
                .build()).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SubcategoryDTO subcategoryDTO) {
        if (subcategoryDTO.getNombre() == null || subcategoryDTO.getCategoryId() <= 0) {
            return ResponseEntity.badRequest().body("Nombre y categoría son obligatorios");
        }

        Optional<Category> categoryOpt = categoryService.findById(subcategoryDTO.getCategoryId());
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Categoría no encontrada");
        }

        Subcategory sub = Subcategory.builder()
                .nombre(subcategoryDTO.getNombre())
                .category(categoryOpt.get())
                .build();
        subcategoryService.save(sub);

        return ResponseEntity.status(HttpStatus.CREATED).body("Subcategoría creada correctamente");
    }
    @PostMapping("/saveAll")
public ResponseEntity<?> saveAll(@RequestBody List<SubcategoryDTO> subcategoriesDTO) {
    StringBuilder errores = new StringBuilder();

    for (int i = 0; i < subcategoriesDTO.size(); i++) {
        SubcategoryDTO dto = subcategoriesDTO.get(i);

        if (dto.getNombre() == null|| dto.getCategoryId() <= 0) {
            errores.append("Subcategoría en posición ").append(i).append(" tiene datos inválidos.\n");
            continue;
        }

        Optional<Category> categoryOpt = categoryService.findById(dto.getCategoryId());
        if (categoryOpt.isEmpty()) {
            errores.append("Categoría no encontrada para subcategoría en posición ").append(i).append("\n");
            continue;
        }

        subcategoryService.save(Subcategory.builder()
            .nombre(dto.getNombre())
            .category(categoryOpt.get())
            .build()
        );
    }

    if (errores.length() > 0) {
        return ResponseEntity.badRequest().body("Errores encontrados:\n" + errores);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("Todas las subcategorías fueron creadas correctamente.");
}


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SubcategoryDTO subcategoryDTO) {
        Optional<Subcategory> subcategoryOptional = subcategoryService.findById(id);
        
        if (subcategoryOptional.isPresent()) {
            Subcategory sub = subcategoryOptional.get();
            sub.setNombre(subcategoryDTO.getNombre());
            Optional<Category> categoryOpt = categoryService.findById(subcategoryDTO.getCategoryId());
            if (categoryOpt.isPresent()) {
                sub.setCategory(categoryOpt.get());
            } else {
                return ResponseEntity.badRequest().body("Categoría no encontrada");
            }
            subcategoryService.save(sub);
            return ResponseEntity.ok("Subcategoría actualizada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subcategoría no encontrada");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Subcategory> subcategoryOptional = subcategoryService.findById(id);
        if (subcategoryOptional.isPresent()) {
            subcategoryService.deleteById(id);
            return ResponseEntity.ok("Subcategoría eliminada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subcategoría no encontrada");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        subcategoryService.findAll().forEach(s -> subcategoryService.deleteById(s.getId()));
        return ResponseEntity.ok("Todas las subcategorías han sido eliminadas");
    }
}
