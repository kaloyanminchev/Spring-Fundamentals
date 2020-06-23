package softuni.heroes.service;

import softuni.heroes.model.service.HeroServiceModel;
import softuni.heroes.model.view.HeroViewModel;

import java.util.List;

public interface HeroService {

    void createHero(HeroServiceModel heroServiceModel);

    void deleteHero(String id);

    List<HeroViewModel> findAllHeroes();

    HeroViewModel findHeroById(String id);
}
