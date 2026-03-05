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

    public Category create(Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }
        if (category.getCode() == null || category.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("code is required");
        }

        String code = category.getCode().trim().toUpperCase();

        if (repo.existsByCode(code)) {
            throw new IllegalArgumentException("Category code already exists");
        }

        category.setCode(code);
        return repo.save(category);
    }
}