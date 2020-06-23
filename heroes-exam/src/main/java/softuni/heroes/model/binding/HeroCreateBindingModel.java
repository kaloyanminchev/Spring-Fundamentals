package softuni.heroes.model.binding;

import org.hibernate.validator.constraints.Length;
import softuni.heroes.model.entity.Class;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class HeroCreateBindingModel {

    private String heroName;
    private Class heroClass;
    private Integer level;

    public HeroCreateBindingModel() {
    }

    @Length(min = 3, message = "Hero name must be more than two characters")
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @NotNull
    public Class getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(Class heroClass) {
        this.heroClass = heroClass;
    }

    @Min(value = 0, message = "Hero level must be positive number")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
