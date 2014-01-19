package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Role;
import ma.ecosiam.facade.RoleFacade;

@ViewScoped
@ManagedBean(name = "roleMB")
public class RoleMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Role> roles;
	private List<Role> rolesFiltres;

	private Role role;
	private RoleFacade roleFacade;

	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public RoleFacade getRoleFacade() {
		if (roleFacade == null) {
			roleFacade = new RoleFacade();
		}

		return roleFacade;
	}

	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }  
	public List<Role> getRolesFiltres() {
		return rolesFiltres;
	}


	public void setRolesFiltres(List<Role> rolesFiltres) {
		this.rolesFiltres = rolesFiltres;
	}


	public Role getRole() {
		if (role == null) {
			role = new Role();
		}

		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void createRole() {
		try {
			getRoleFacade().createRole(role);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/admin/privileges/roles/roles.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadRoles() {
		roles = getRoleFacade().listAll();
	}

	public List<Role> getRoles() {
		if (roles == null) {
			loadRoles();
		}
		return roles;
	}

	public void resetRole() {
		role = new Role();
	}

	public void updateRole() {
		try {
			getRoleFacade().updateRole(role);
			loadRoles();
			resetRole();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/admin/privileges/roles/roles.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteRole(Role s) {
		try {
			getRoleFacade().deleteRole(s);
			loadRoles();
			resetRole();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/admin/privileges/roles/roles.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}