package edu.gdlc_project.gdlc_pckgs.service.Role_Service;

import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RoleService {
    public Role saveRole(Role role);

    public List<Role> getAllRoles();

    public Role getRoleById(int id);

}
