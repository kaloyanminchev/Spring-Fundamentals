package softuni.andreysexam.model.service;

import softuni.andreysexam.model.entity.Category;
import softuni.andreysexam.model.entity.Gender;

import java.math.BigDecimal;

public class ItemServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private BigDecimal price;
    private CategoryServiceModel category;
    private Gender gender;

    public ItemServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryServiceModel getCategory() {
        return category;
    }

    public void setCategory(CategoryServiceModel category) {
        this.category = category;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
