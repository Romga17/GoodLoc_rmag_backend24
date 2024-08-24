package edu.gdlc_project.gdlc_pckgs.service.Role_Service;

import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new NotFoundException("Role non trouvé avec le nom : " + roleId);
        }
    }
}
