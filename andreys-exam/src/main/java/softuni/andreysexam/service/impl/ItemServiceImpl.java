package softuni.andreysexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.andreysexam.model.entity.Item;
import softuni.andreysexam.model.service.CategoryServiceModel;
import softuni.andreysexam.model.service.ItemServiceModel;
import softuni.andreysexam.model.view.ItemViewModel;
import softuni.andreysexam.repository.ItemRepository;
import softuni.andreysexam.service.CategoryService;
import softuni.andreysexam.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {
        CategoryServiceModel categoryServiceModel =
                this.categoryService.getByCategoryName(itemServiceModel.getCategory().getName());

        itemServiceModel.setCategory(categoryServiceModel);

        this.itemRepository
                .saveAndFlush(this.mapper
                        .map(itemServiceModel, Item.class));

        /* Item item = this.mapper.map(itemServiceModel, Item.class);

        item.setCategory(this.categoryService.find(itemServiceModel.getCategory().getName()));

        this.itemRepository.saveAndFlush(item); */
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return this.itemRepository
                .findAll()
                .stream()
                .map(item -> {
                    ItemViewModel itemViewModel =
                            this.mapper.map(item, ItemViewModel.class);

                    itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg",
                            item.getGender().name(),
                            item.getCategory().getName().name()));

                    return itemViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel getById(String id) {
        return this.itemRepository
                .findById(id)
                .map(item -> {
                    ItemViewModel itemViewModel =
                            this.mapper.map(item, ItemViewModel.class);

                    itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg",
                            item.getGender().name(),
                            item.getCategory().getName().name()));

                    return itemViewModel;
                })
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }
}
