package com.application.rest.RestTecnoPc.controller;


import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.rest.RestTecnoPc.controller.dto.ProductDTO;
import com.application.rest.RestTecnoPc.entities.Product;
import com.application.rest.RestTecnoPc.entities.Subcategory;
import com.application.rest.RestTecnoPc.service.IProductService;
import com.application.rest.RestTecnoPc.service.ISubCategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ISubCategoryService subcategoryService;
    
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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
        // Endpoint to save a product
        if (productDTO.getNombre() == null || productDTO.getDescripcion() == null ||
            productDTO.getStock() < 0 || productDTO.getPrice() == null  ) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
        }

    Optional<Subcategory> subOpt = subcategoryService.findById(productDTO.getSubcategoryId());
    if (subOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Subcategoría no encontrada");
    }
        productService.save(Product.builder()
                .nombre(productDTO.getNombre())
                .descripcion(productDTO.getDescripcion())
                .stock(productDTO.getStock())
                .imagenUrl(productDTO.getImagenUrl())
                .price(productDTO.getPrice())
                .subcategoria(subOpt.get())
                .build());

        return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado correctamente");
    }

    @PostMapping("/saveAll")
public ResponseEntity<?> saveAll(@RequestBody List<ProductDTO> productosDTO) {
    List<String> errores = new ArrayList<>();
    
    for (int i = 0; i < productosDTO.size(); i++) {
        ProductDTO dto = productosDTO.get(i);
        
        // Validación
        if (dto.getNombre() == null || dto.getDescripcion() == null ||
            dto.getStock() < 0 || dto.getPrice() == null) {
            errores.add("Producto en posición " + i + " tiene campos inválidos o faltantes.");
            continue;
        }

        Optional<Subcategory> subOpt = subcategoryService.findById(dto.getSubcategoryId());
        if (subOpt.isEmpty()) {
            errores.add("Subcategoría no encontrada para el producto en posición " + i);
            continue;
        }

        // Guardar producto
        productService.save(Product.builder()
            .nombre(dto.getNombre())
            .descripcion(dto.getDescripcion())
            .stock(dto.getStock())
            .imagenUrl(dto.getImagenUrl())
            .price(dto.getPrice())
            .subcategoria(subOpt.get())
            .build()
        );
    }

    if (!errores.isEmpty()) {
        return ResponseEntity.badRequest().body("Algunos productos no fueron guardados:\n" + String.join("\n", errores));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("Todos los productos fueron guardados correctamente.");
}


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
    
        @PutMapping("/update/{id}")
        public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
            // Endpoint to update a product
            Optional<Product> productOptional = productService.findById(id);
            if (productOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto no encontrado con ID: " + id);
            }
             Optional<Subcategory> subOpt = subcategoryService.findById(productDTO.getSubcategoryId());
    if (subOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Subcategoría no encontrada con ID: " + productDTO.getSubcategoryId());
    }
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setNombre(productDTO.getNombre());
                product.setDescripcion(productDTO.getDescripcion());
                product.setStock(productDTO.getStock());
                product.setImagenUrl(productDTO.getImagenUrl());
                product.setPrice(productDTO.getPrice());
                product.setSubcategoria(subOpt.get());
                productService.save(product);
                return ResponseEntity.ok("Producto actualizado correctamente");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("Producto no encontrado con ID: " + id);

        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        // Endpoint to delete a product by ID
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto no encontrado con ID: " + id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        // Endpoint to delete all products
        productService.findAll().forEach(product -> productService.deleteById(product.getId()));
        return ResponseEntity.ok("Todos los productos han sido eliminados");
    }

    


    
       
}