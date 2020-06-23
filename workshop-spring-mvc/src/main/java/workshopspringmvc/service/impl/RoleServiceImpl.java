package workshopspringmvc.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopspringmvc.model.entity.Role;
import workshopspringmvc.model.service.RoleServiceModel;
import workshopspringmvc.repository.RoleRepository;
import workshopspringmvc.service.RoleService;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public RoleServiceModel findByName(String name) {
        return this.roleRepository
                .findByName(name)
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class))
                .orElse(null);
    }
}
