package workshopspringmvc.service;

import workshopspringmvc.model.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByName(String name);
}
