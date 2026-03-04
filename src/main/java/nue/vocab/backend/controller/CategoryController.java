package nue.vocab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nue.vocab.backend.service.CategoryService;
import nue.vocab.backend.model.Category;
import nue.vocab.backend.routes.CategoryRoutes;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(CategoryRoutes.GET_ALL)
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping(CategoryRoutes.GET_BY_ID)
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping(CategoryRoutes.CREATE)
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category saved = categoryService.create(category);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(CategoryRoutes.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok("Category deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}