package softuni.exam.service;

import softuni.exam.model.service.ProductServiceModel;
import softuni.exam.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void addProduct(ProductServiceModel productServiceModel);

    void delete(String id);

    void deleteAll();

    BigDecimal getTotalPrice();

    List<ProductViewModel> findAllProductsByFood();

    List<ProductViewModel> findAllProductsByDrinks();

    List<ProductViewModel> findAllProductsByHouseholds();

    List<ProductViewModel> findAllProductsByOthers();
}
