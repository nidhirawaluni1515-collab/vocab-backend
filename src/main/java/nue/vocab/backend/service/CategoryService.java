package nue.vocab.backend.service;

import nue.vocab.backend.model.Category;
import nue.vocab.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Category create(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }
        if (category.getCode() == null || category.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("code is required");
        }
        return repo.save(category);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        repo.deleteById(id);
    }
}