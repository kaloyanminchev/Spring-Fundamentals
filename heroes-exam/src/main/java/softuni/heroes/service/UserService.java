package softuni.heroes.service;

import softuni.heroes.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
