package dao;

import model.Role;

import java.util.List;

public interface RoleDao {

    Role getRole(String role);

    List<Role> getAllRoles();
}
