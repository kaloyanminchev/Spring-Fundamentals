package softuni.andreysexam.service;

import softuni.andreysexam.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
