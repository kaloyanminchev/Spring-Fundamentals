package softuni.andreysexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.andreysexam.model.entity.Category;
import softuni.andreysexam.model.entity.CategoryName;
import softuni.andreysexam.model.service.CategoryServiceModel;
import softuni.andreysexam.repository.CategoryRepository;
import softuni.andreysexam.service.CategoryService;

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

    /* another option with @PostConstruct rather than creating an init class
    @PostConstruct
    public void init() {
        if (this.categoryRepository.count() == 0) {
            Category cat1 = new Category(CategoryName.valueOf("DENIM"),
                    "Denim is a sturdy cotton warp-faced textile in which the weft passes under two or more warp threads.");
            Category cat2 = new Category(CategoryName.valueOf("SHIRT"),
                    "A shirt is a cloth garment for the upper body (from the neck to the waist).");
            Category cat3 = new Category(CategoryName.valueOf("JACKET"),
                    "A jacket is a mid-stomach–length garment for the upper body");
            Category cat4 = new Category(CategoryName.valueOf("SHORTS"),
                    "They are called \"shorts\" because they are a shortened version of trousers.");

            this.categoryRepository.save(cat1);
            this.categoryRepository.save(cat2);
            this.categoryRepository.save(cat3);
            this.categoryRepository.save(cat4);
        }
    } */

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
    public CategoryServiceModel getByCategoryName(CategoryName categoryName) {
        return this.categoryRepository
                .findByName(categoryName)
                .map(category -> this.mapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }

    /*
    @Override
    public Category find(CategoryName categoryName) {
        return this.categoryRepository
                .findByName(categoryName)
                .orElse(null);
    } */
}
