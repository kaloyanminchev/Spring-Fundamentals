package softuni.andreysexam.model.service;

import softuni.andreysexam.model.entity.CategoryName;

public class CategoryServiceModel extends BaseServiceModel {

    private CategoryName name;
    private String description;

    public CategoryServiceModel() {
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
