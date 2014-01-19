package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.RoleDAO;
import ma.ecosiam.entity.Role;

public class RoleFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private RoleDAO roleDAO = new RoleDAO();

	public void createRole(Role role) {
		roleDAO.beginTransaction();
		roleDAO.save(role);
		roleDAO.commitAndCloseTransaction();
	}

	public List<Role> listAll() {
		roleDAO.beginTransaction();
		List<Role> result = roleDAO.findAll();
		roleDAO.closeTransaction();
		return result;
	}

	public void updateRole(Role role) {
		roleDAO.beginTransaction();
		Role persistedRole = roleDAO.find(role.getIdRole());
		persistedRole.setRole(role.getRole());
		persistedRole.setDescription(role.getDescription());
		roleDAO.update(persistedRole);
		roleDAO.commitAndCloseTransaction();
	}

	public Role findRole(int roleId) {
		roleDAO.beginTransaction();
		Role role = roleDAO.find(roleId);
		roleDAO.closeTransaction();
		return role;
	}

	public void deleteRole(Role role) {
		roleDAO.beginTransaction();
		Role persistedRole = roleDAO.find(role.getIdRole());
		roleDAO.delete(persistedRole);
		roleDAO.commitAndCloseTransaction();
	}
}
