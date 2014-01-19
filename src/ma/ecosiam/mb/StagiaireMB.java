package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;

import ma.ecosiam.entity.Classe;
import ma.ecosiam.entity.Stagiaire;
import ma.ecosiam.entity.Utilisateur;
import ma.ecosiam.facade.ClasseFacade;
import ma.ecosiam.facade.StagiaireFacade;
import ma.ecosiam.facade.UtilisateurFacade;

@ViewScoped
@ManagedBean(name = "stagiaireMB")
public class StagiaireMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Stagiaire> stagiaires;
	private List<Stagiaire> stagiairesFiltres;

	private Stagiaire stagiaire;
	private StagiaireFacade stagiaireFacade;

	private List<Classe> classes;
	private ClasseFacade classeFacade;

	private Utilisateur utilisateur = new Utilisateur();
	private UtilisateurFacade utilisateurFacade;

	private Classe classe = new Classe();

	public UtilisateurFacade getUtilisateurFacade() {
		return utilisateurFacade;
	}

	public void setUtilisateurFacade(UtilisateurFacade utilisateurFacade) {
		this.utilisateurFacade = utilisateurFacade;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public ClasseFacade getClasseFacade() {
		return classeFacade;
	}

	public void setClasseFacade(ClasseFacade classeFacade) {
		this.classeFacade = classeFacade;
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

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public List<Classe> completeClasses(String nSerie) {
		List<Classe> queryResult = new ArrayList<Classe>();

		if (classes == null) {
			classeFacade = new ClasseFacade();
			classes = classeFacade.listAll();
		}

		for (Classe classe : classes) {
			queryResult.add(classe);
		}

		return queryResult;
	}

	public StagiaireFacade getStagiaireFacade() {
		if (stagiaireFacade == null) {
			stagiaireFacade = new StagiaireFacade();
		}

		return stagiaireFacade;
	}

	private SelectItem[] createFilterOptions(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}

		return options;
	}

	public List<Stagiaire> getStagiairesFiltres() {
		return stagiairesFiltres;
	}

	public void setStagiairesFiltres(List<Stagiaire> stagiairesFiltres) {
		this.stagiairesFiltres = stagiairesFiltres;
	}

	public Stagiaire getStagiaire() {
		if (stagiaire == null) {
			stagiaire = new Stagiaire();
		}

		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public void createStagiaire() {
		try {
			getStagiaireFacade()
					.createStagiaire(stagiaire, utilisateur, classe);
			System.out.println(classe.getLibelleClasse());
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/stagiaire/stagiaires/stagiaires.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadStagiaires() {
		stagiaires = getStagiaireFacade().listAll();
	}

	public List<Stagiaire> getStagiaires() {
		if (stagiaires == null) {
			loadStagiaires();
		}
		return stagiaires;
	}

	public void resetStagiaire() {
		stagiaire = new Stagiaire();
	}

	public void updateStagiaire() {
		try {
			getStagiaireFacade()
					.updateStagiaire(stagiaire, utilisateur, classe);
			loadStagiaires();
			resetStagiaire();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/stagiaire/stagiaires/stagiaires.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteStagiaire(Stagiaire s, Utilisateur u) {
		try {
			getStagiaireFacade().deleteStagiaire(s, u);
			loadStagiaires();
			resetStagiaire();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/stagiaire/stagiaires/stagiaires.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("ok");
		utilisateur.setImageUser(event.getFile().getFileName());
		System.out.println(utilisateur.getImageUser());
	}
}