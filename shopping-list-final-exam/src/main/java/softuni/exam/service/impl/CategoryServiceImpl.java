package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.model.entity.Category;
import softuni.exam.model.entity.CategoryName;
import softuni.exam.model.service.CategoryServiceModel;
import softuni.exam.repository.CategoryRepository;
import softuni.exam.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,
                                String.format("Description for %s.", categoryName.name())));
                    });
        }
    }

    @Override
    public CategoryServiceModel findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository
                .findByCategoryName(categoryName)
                .map(category -> this.mapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }

    @Override
    public Category findByCategoryNameAndReturnCategoryEntity(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName).orElse(null);
    }
}
