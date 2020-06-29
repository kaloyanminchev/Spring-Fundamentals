package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.model.entity.CategoryName;
import softuni.exam.model.entity.Product;
import softuni.exam.model.service.CategoryServiceModel;
import softuni.exam.model.service.ProductServiceModel;
import softuni.exam.model.view.ProductViewModel;
import softuni.exam.repository.ProductRepository;
import softuni.exam.service.CategoryService;
import softuni.exam.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        CategoryServiceModel categoryServiceModel =
                this.categoryService.findByCategoryName(productServiceModel.getCategory().getCategoryName());

        productServiceModel.setCategory(categoryServiceModel);

        this.productRepository
                .saveAndFlush(this.mapper.map(productServiceModel, Product.class));
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal total = new BigDecimal(0);
        for (Product product : this.productRepository.findAll()) {
            total = total.add(product.getPrice());
        }
        return total;
    }

    @Override
    public List<ProductViewModel> findAllProductsByFood() {
        return this.productRepository
                .findAllByCategory(this.categoryService.findByCategoryNameAndReturnCategoryEntity(CategoryName.FOOD))
                .stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.mapper.map(product, ProductViewModel.class);
                    productViewModel.setImageUrl(String.format("/img/%s.png",
                            product.getCategory().getCategoryName().name().toLowerCase()));
                    return productViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> findAllProductsByDrinks() {
        return this.productRepository
                .findAllByCategory(this.categoryService.findByCategoryNameAndReturnCategoryEntity(CategoryName.DRINK))
                .stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.mapper.map(product, ProductViewModel.class);
                    productViewModel.setImageUrl(String.format("/img/%s.png",
                            product.getCategory().getCategoryName().name().toLowerCase()));
                    return productViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> findAllProductsByHouseholds() {
        return this.productRepository
                .findAllByCategory(this.categoryService.findByCategoryNameAndReturnCategoryEntity(CategoryName.HOUSEHOLD))
                .stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.mapper.map(product, ProductViewModel.class);
                    productViewModel.setImageUrl(String.format("/img/%s.png",
                            product.getCategory().getCategoryName().name().toLowerCase()));
                    return productViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductViewModel> findAllProductsByOthers() {
        return this.productRepository
                .findAllByCategory(this.categoryService.findByCategoryNameAndReturnCategoryEntity(CategoryName.OTHER))
                .stream()
                .map(product -> {
                    ProductViewModel productViewModel = this.mapper.map(product, ProductViewModel.class);
                    productViewModel.setImageUrl(String.format("/img/%s.png",
                            product.getCategory().getCategoryName().name().toLowerCase()));
                    return productViewModel;
                })
                .collect(Collectors.toList());
    }
}
