package softuni.heroes.model.service;

import softuni.heroes.model.entity.Class;

public class HeroServiceModel extends BaseServiceModel {

    private String name;
    private Class heroClass;
    private Integer level;

    public HeroServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(Class heroClass) {
        this.heroClass = heroClass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
