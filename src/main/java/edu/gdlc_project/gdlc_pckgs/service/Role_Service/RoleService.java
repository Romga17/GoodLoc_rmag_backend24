package edu.gdlc_project.gdlc_pckgs.service.Role_Service;

import edu.gdlc_project.gdlc_pckgs.model.Role;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RoleService {
    public Role saveRole(Role role);

    public List<Role> getAllRoles();
}
