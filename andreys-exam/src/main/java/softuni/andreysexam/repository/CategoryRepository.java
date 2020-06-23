package softuni.andreysexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.andreysexam.model.entity.Category;
import softuni.andreysexam.model.entity.CategoryName;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(CategoryName categoryName);
}
