package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ma.ecosiam.entity.Filiere;
import ma.ecosiam.entity.Formation;
import ma.ecosiam.entity.Matiere;
import ma.ecosiam.facade.FiliereFacade;
import ma.ecosiam.facade.FormationFacade;
import ma.ecosiam.facade.MatiereFacade;

@ViewScoped
@ManagedBean(name = "filiereMB")
public class FiliereMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Filiere> filieres;
	private List<Filiere> filieresFiltres;

	private Filiere filiere;
	private FiliereFacade filiereFacade;

	private Formation formation;

	private List<Formation> formations;
	private FormationFacade formationFacade;

	private List<Matiere> matieres;
	private MatiereFacade matiereFacade;

	private Matiere matiere;

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public List<Formation> completeFormations(String nSerie) {
		List<Formation> queryResult = new ArrayList<Formation>();

		if (formations == null) {
			formationFacade = new FormationFacade();
			formations = formationFacade.listAll();
		}

		for (Formation formation : formations) {
			queryResult.add(formation);
		}

		return queryResult;
	}

	public List<Matiere> completeMatieres(String nSerie) {
		List<Matiere> queryResult = new ArrayList<Matiere>();
		boolean a = true;
		if (matieres == null) {
			matiereFacade = new MatiereFacade();
			matieres = matiereFacade.listAll();
		}

		if (filiere.getMatieres().isEmpty()) {
			for (Matiere matiere : matieres) {
				queryResult.add(matiere);
			}
			return queryResult;

		} else {
			for (Matiere matiere : matieres) {
				a = true;
				for (Matiere matiereDansFiliere : filiere.getMatieres()) {
					if (matiere.getLibelleMatiere().compareTo(
							matiereDansFiliere.getLibelleMatiere()) == 0)
						a = false;
				}
				if (a == true)
					queryResult.add(matiere);
			}
			return queryResult;
		}
	}

	public FiliereFacade getFiliereFacade() {
		if (filiereFacade == null) {
			filiereFacade = new FiliereFacade();
		}

		return filiereFacade;
	}

	private SelectItem[] createFilterOptions(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}

		return options;
	}

	public List<Filiere> getFilieresFiltres() {
		return filieresFiltres;
	}

	public void setFilieresFiltres(List<Filiere> filieresFiltres) {
		this.filieresFiltres = filieresFiltres;
	}

	public Filiere getFiliere() {
		if (filiere == null) {
			filiere = new Filiere();
		}

		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public void createFiliere() {
		try {
			getFiliereFacade().createFiliere(filiere, formation);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/filieres/filieres.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFilieres() {
		filieres = getFiliereFacade().listAll();
	}

	public List<Filiere> getFilieres() {
		if (filieres == null) {
			loadFilieres();
		}
		return filieres;
	}

	public void resetFiliere() {
		filiere = new Filiere();
	}

	public void updateFiliere() {
		try {
			getFiliereFacade().updateFiliere(filiere, formation);
			loadFilieres();
			resetFiliere();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/filieres/filieres.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteFiliere(Filiere s) {
		try {
			getFiliereFacade().deleteFiliere(s);
			loadFilieres();
			resetFiliere();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/filieres/filieres.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ajouterMatiere() {
		try {
			getFiliereFacade().ajouterMatiere(filiere, matiere);
			loadFilieres();
			resetFiliere();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/filieres/filieres.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}