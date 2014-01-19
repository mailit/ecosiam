package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ma.ecosiam.entity.Utilisateur;
import ma.ecosiam.entity.Role;
import ma.ecosiam.entity.Ville;
import ma.ecosiam.facade.UtilisateurFacade;
import ma.ecosiam.facade.RoleFacade;
import ma.ecosiam.facade.VilleFacade;

@ViewScoped
@ManagedBean(name = "utilisateurMB")
public class UtilisateurMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Utilisateur> utilisateurs;
	private List<Utilisateur> utilisateursFiltres;

	private Utilisateur utilisateur;
	private UtilisateurFacade utilisateurFacade;

	private List<Role> roles;
	private RoleFacade roleFacade;

	private String pass;

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	// public UtilisateurMB()
	// {
	//
	// }
	// public UtilisateurMB(Utilisateur utilisateur)
	// {
	// this.utilisateur=utilisateur;
	// }
	public void setUtilisateurFacade(UtilisateurFacade utilisateurFacade) {
		this.utilisateurFacade = utilisateurFacade;
	}

	public UtilisateurFacade getUtilisateurFacade() {
		if (utilisateurFacade == null) {
			utilisateurFacade = new UtilisateurFacade();
		}

		return utilisateurFacade;
	}

	public List<Role> completeRoles(String nSerie) {
		List<Role> queryResult = new ArrayList<Role>();

		if (roles == null) {
			roleFacade = new RoleFacade();
			roles = roleFacade.listAll();
		}

		for (Role role : roles) {
			if (role.getRole().compareTo("Formateur") != 0
					&& role.getRole().compareTo("Stagiaire") != 0)
				queryResult.add(role);
		}

		return queryResult;
	}

	private SelectItem[] createFilterOptions(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}

		return options;
	}

	public List<Utilisateur> getUtilisateursFiltres() {
		return utilisateursFiltres;
	}

	public void setUtilisateursFiltres(List<Utilisateur> utilisateursFiltres) {
		this.utilisateursFiltres = utilisateursFiltres;
	}

	public Utilisateur getUtilisateur() {
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
		}

		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void createUtilisateur() {
		try {
			getUtilisateurFacade().createUtilisateur(utilisateur);
			getUtilisateurFacade().email(utilisateur);
			loadUtilisateurs();
			resetUtilisateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/admin/privileges/utilisateurs/utilisateurs.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadUtilisateurs() {
		utilisateurs = getUtilisateurFacade().listAll();
	}

	public List<Utilisateur> getUtilisateurs() {
		if (utilisateurs == null) {
			loadUtilisateurs();
		}
		return utilisateurs;
	}

	public void resetUtilisateur() {
		utilisateur = new Utilisateur();
	}

	public void updateUtilisateur() {
		try {
			getUtilisateurFacade().updateUtilisateur(utilisateur);
			loadUtilisateurs();
			resetUtilisateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/admin/privileges/utilisateurs/utilisateurs.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteUtilisateur(Utilisateur s) {
		try {
			getUtilisateurFacade().deleteUtilisateur(s);
			loadUtilisateurs();
			resetUtilisateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/admin/privileges/utilisateurs/utilisateurs.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifierPass() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			Map<String, String> params = fc.getExternalContext()
					.getRequestParameterMap();
			String login = params.get("loginUser");			
			getUtilisateurFacade().modifierPass(utilisateur, pass, login);
			loadUtilisateurs();
			resetUtilisateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/profil/motDePasseModifie.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}