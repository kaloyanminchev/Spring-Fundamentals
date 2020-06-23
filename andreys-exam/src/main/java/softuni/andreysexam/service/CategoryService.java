package softuni.andreysexam.service;

import softuni.andreysexam.model.entity.Category;
import softuni.andreysexam.model.entity.CategoryName;
import softuni.andreysexam.model.service.CategoryServiceModel;

public interface CategoryService {

    void initCategories();

    CategoryServiceModel getByCategoryName(CategoryName categoryName);

//    Category find(CategoryName categoryName);
}
