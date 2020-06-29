package softuni.exam.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryName categoryName;
    private String description;

    public Category() {
    }

    public Category(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public Category(CategoryName categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
