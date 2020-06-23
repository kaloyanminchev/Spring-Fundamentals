package softuni.heroes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppBeanConfig {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
