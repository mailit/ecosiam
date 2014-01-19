package ma.ecosiam.mb;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ma.ecosiam.entity.Utilisateur;
import ma.ecosiam.facade.UtilisateurFacade;

import org.primefaces.context.RequestContext;

@RequestScoped
@ManagedBean(name = "authentificationMB")
public class AuthentificationMB extends AbstractMB {

	// @ManagedProperty(value = UtilisateurMB.INJECTION_NAME)
	private UtilisateurMB utilisateurMB;
	private String loginUser;
	private String passwordUser;
	
	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public UtilisateurMB getUtilisateurMB() {
		return utilisateurMB;
	}

	public void setUtilisateurMB(UtilisateurMB utilisateurMB) {
		this.utilisateurMB = utilisateurMB;
	}

	public void seConnecter() throws IOException {

		RequestContext contexte = RequestContext.getCurrentInstance();

		UtilisateurFacade utilisateurFacade = new UtilisateurFacade();
		Utilisateur utilisateur = utilisateurFacade.estValide(loginUser,
				passwordUser);

		FacesMessage msg = null;

		if (utilisateur != null) {
			utilisateurMB = new UtilisateurMB();
			FacesContext context = FacesContext.getCurrentInstance();

			utilisateurMB.setUtilisateur(utilisateur);
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			request.getSession().setAttribute("utilisateur", utilisateur);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/CECOSIAM/pages/prive/index.xhtml");
		}

		displayErrorMessageToUser("Nom/Mot de passe erroné !");
	}

	public void deconnecter() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.getSession().invalidate();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect("/CECOSIAM/pages/publique/authentification.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}