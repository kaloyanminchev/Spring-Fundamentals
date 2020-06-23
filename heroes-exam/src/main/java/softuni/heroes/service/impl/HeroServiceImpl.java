package softuni.heroes.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.model.entity.Hero;
import softuni.heroes.model.service.HeroServiceModel;
import softuni.heroes.model.view.HeroViewModel;
import softuni.heroes.repository.HeroRepository;
import softuni.heroes.service.HeroService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper mapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper mapper) {
        this.heroRepository = heroRepository;
        this.mapper = mapper;
    }

    @Override
    public void createHero(HeroServiceModel heroServiceModel) {
        this.heroRepository.saveAndFlush(this.mapper.map(heroServiceModel, Hero.class));
    }

    @Override
    public void deleteHero(String id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public List<HeroViewModel> findAllHeroes() {
        return this.heroRepository
                .findAll()
                .stream()
                .map(hero -> {
                    HeroViewModel heroViewModel =
                            this.mapper.map(hero, HeroViewModel.class);
                    heroViewModel.setHeroClass(hero.getHeroClass().name());
                    heroViewModel.setImageUrl(String.format("/img/%s.jpg",
                            hero.getHeroClass().name().toLowerCase()));
                    return heroViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HeroViewModel findHeroById(String id) {
        return this.heroRepository
                .findById(id)
                .map(hero -> {
                    HeroViewModel heroViewModel =
                            this.mapper.map(hero, HeroViewModel.class);
                    heroViewModel.setHeroClass(hero.getHeroClass().name());
                    return heroViewModel;
                })
                .orElse(null);
    }
}
