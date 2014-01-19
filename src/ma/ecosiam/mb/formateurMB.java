package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import ma.ecosiam.entity.Formateur;
import ma.ecosiam.entity.Formation;
import ma.ecosiam.entity.Matiere;
import ma.ecosiam.entity.Specialite;
import ma.ecosiam.entity.Utilisateur;
import ma.ecosiam.facade.FormateurFacade;
import ma.ecosiam.facade.FormationFacade;
import ma.ecosiam.facade.MatiereFacade;
import ma.ecosiam.facade.SpecialiteFacade;
import ma.ecosiam.facade.UtilisateurFacade;

@ViewScoped
@ManagedBean(name = "formateurMB")
public class formateurMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Formateur> formateurs;
	private List<Formateur> formateursFiltres;

	private Formateur formateur;
	private FormateurFacade formateurFacade;

	private Utilisateur utilisateur = new Utilisateur();
	private UtilisateurFacade utilisateurFacade;

	private List<Specialite> specialites;
	private SpecialiteFacade specialiteFacade;

	private Specialite specialite;
	
	private Formateur selectedFormateur;
	
	

	public Formateur getSelectedFormateur() {
		return selectedFormateur;
	}

	public void setSelectedFormateur(Formateur selectedFormateur) {
		this.selectedFormateur = selectedFormateur;
	}

	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public List<Specialite> completeSpecialites(String nSerie) {
		List<Specialite> queryResult = new ArrayList<Specialite>();
		List<Specialite> SpeciliateDeFormateur = formateur.getSpecialites();
		boolean a = true;
		if (specialites == null) {
			specialiteFacade = new SpecialiteFacade();
			specialites = specialiteFacade.listAll();
		}

		if (formateur.getSpecialites().isEmpty()) {
			for (Specialite specialite : specialites) {
				queryResult.add(specialite);
			}
			return queryResult;

		} else {
			for (Specialite specialite : specialites) {
				a=true;
				for (Specialite specialiteDeFormateur : formateur
						.getSpecialites()) {
					if (specialite.getLibelleSpecialite().compareTo(
							specialiteDeFormateur.getLibelleSpecialite()) == 0)
						a = false;
				}
				if (a == true)
					queryResult.add(specialite);
			}
			return queryResult;
		}
	}

	public UtilisateurFacade getUtilisateurFacade() {
		return utilisateurFacade;
	}

	public void setUtilisateurFacade(UtilisateurFacade utilisateurFacade) {
		this.utilisateurFacade = utilisateurFacade;
	}

	public Utilisateur getUtilisateur() {
		if (utilisateur != null)
			return utilisateur;
		else
			return new Utilisateur();
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public FormateurFacade getFormateurFacade() {
		if (formateurFacade == null) {
			formateurFacade = new FormateurFacade();
		}

		return formateurFacade;
	}

	private SelectItem[] createFilterOptions(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}

		return options;
	}

	public List<Formateur> getFormateursFiltres() {
		return formateursFiltres;
	}

	public void setFormateursFiltres(List<Formateur> formateursFiltres) {
		this.formateursFiltres = formateursFiltres;
	}

	public Formateur getFormateur() {
		if (formateur == null) {
			formateur = new Formateur();
		}

		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public void createFormateur() {
		try {
			getFormateurFacade().createFormateur(formateur, utilisateur);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formateur/formateurs/formateurs.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFormateurs() {
		formateurs = getFormateurFacade().listAll();
	}

	public List<Formateur> getFormateurs() {
		if (formateurs == null) {
			loadFormateurs();
		}
		return formateurs;
	}

	public void resetFormateur() {
		formateur = new Formateur();
	}

	public void updateFormateur() {
		try {
			getFormateurFacade().updateFormateur(formateur, utilisateur);
			loadFormateurs();
			resetFormateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formateur/formateurs/formateurs.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteFormateur(Formateur s, Utilisateur u) {
		try {
			getFormateurFacade().deleteFormateur(s, u);
			loadFormateurs();
			resetFormateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formateur/formateurs/formateurs.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ajouterSpecialite() {
		try {
			getFormateurFacade().ajouterSpecialite(formateur, specialite);
			loadFormateurs();
			resetFormateur();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formateur/formateurs/formateurs.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}