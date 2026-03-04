package nue.vocab.backend.routes;

import nue.vocab.backend.model.Category;
import nue.vocab.backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRoutes {

    private final CategoryService service;

    public CategoryRoutes(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }
}