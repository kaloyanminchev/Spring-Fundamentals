package softuni.andreysexam.service;

import softuni.andreysexam.model.service.ItemServiceModel;
import softuni.andreysexam.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    void addItem(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel getById(String id);

    void delete(String id);
}
