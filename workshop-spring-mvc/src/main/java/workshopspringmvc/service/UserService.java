package workshopspringmvc.service;

import workshopspringmvc.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> findAllUsernames();

    void addRoleToUser(String username, String role);
}
