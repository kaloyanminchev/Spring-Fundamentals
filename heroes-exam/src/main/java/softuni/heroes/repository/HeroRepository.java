package softuni.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.heroes.model.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {
}
