package io.bootify.my_app.rest;

import io.bootify.my_app.model.CategoryDTO;
import io.bootify.my_app.service.CategoryService;
import io.bootify.my_app.util.ReferencedException;
import io.bootify.my_app.util.ReferencedWarning;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(
            @PathVariable(name = "categoryId") final Integer categoryId) {
        return ResponseEntity.ok(categoryService.get(categoryId));
    }

    @PostMapping
    public ResponseEntity<Integer> createCategory(
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        final Integer createdCategoryId = categoryService.create(categoryDTO);
        return new ResponseEntity<>(createdCategoryId, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Integer> updateCategory(
            @PathVariable(name = "categoryId") final Integer categoryId,
            @RequestBody @Valid final CategoryDTO categoryDTO) {
        categoryService.update(categoryId, categoryDTO);
        return ResponseEntity.ok(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable(name = "categoryId") final Integer categoryId) {
        final ReferencedWarning referencedWarning = categoryService.getReferencedWarning(categoryId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

}
