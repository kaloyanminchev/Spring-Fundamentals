package softuni.heroes.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    private String name;
    private Class heroClass;
    private Integer level;

    public Hero() {
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "class", nullable = false)
    @Enumerated(EnumType.STRING)
    public Class getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(Class heroClass) {
        this.heroClass = heroClass;
    }

    @Column(nullable = false)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
