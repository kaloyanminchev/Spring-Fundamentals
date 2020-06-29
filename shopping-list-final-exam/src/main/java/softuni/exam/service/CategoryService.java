package softuni.exam.service;

import softuni.exam.model.entity.Category;
import softuni.exam.model.entity.CategoryName;
import softuni.exam.model.service.CategoryServiceModel;

public interface CategoryService {

    void initCategories();

    CategoryServiceModel findByCategoryName(CategoryName categoryName);

    Category findByCategoryNameAndReturnCategoryEntity(CategoryName name);
}
