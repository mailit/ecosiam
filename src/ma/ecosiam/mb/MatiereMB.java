package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Filiere;
import ma.ecosiam.entity.Matiere;
import ma.ecosiam.entity.Formateur;
import ma.ecosiam.facade.FiliereFacade;
import ma.ecosiam.facade.MatiereFacade;
import ma.ecosiam.facade.FormateurFacade;


@ViewScoped
@ManagedBean(name = "matiereMB")
public class MatiereMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Matiere> matieres;
	private List<Matiere> matieresFiltres;

	private Matiere matiere;
	private MatiereFacade matiereFacade;

	private List<Filiere> allFilieres;
	private FiliereFacade filiereFacade;

	private List<Formateur> formateurs;
	private FormateurFacade formateurFacade;
	
	private Formateur formateur;
	
	
	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public List<Formateur> completeFormateurs(String nSerie) {
		List<Formateur> queryResult = new ArrayList<Formateur>();

		if (formateurs == null) {
			formateurFacade = new FormateurFacade();
			formateurs = formateurFacade.listAll();
		}

		for (Formateur formateur : formateurs) {
			queryResult.add(formateur);
		}

		return queryResult;
	}
	
	public MatiereFacade getMatiereFacade() {
		if (matiereFacade == null) {
			matiereFacade = new MatiereFacade();
		}

		return matiereFacade;
	}

	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }  
	public List<Matiere> getMatieresFiltres() {
		return matieresFiltres;
	}


	public void setMatieresFiltres(List<Matiere> matieresFiltres) {
		this.matieresFiltres = matieresFiltres;
	}


	public Matiere getMatiere() {
		if (matiere == null) {
			matiere = new Matiere();
		}

		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public void createMatiere() {
		try {
			getMatiereFacade().createMatiere(matiere,formateur);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/matieres/matieres.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadMatieres() {
		matieres = getMatiereFacade().listAll();
	}

	public List<Matiere> getMatieres() {
		if (matieres == null) {
			loadMatieres();
		}
		return matieres;
	}

	public void resetMatiere() {
		matiere = new Matiere();
	}

	public void updateMatiere() {
		try {
			getMatiereFacade().updateMatiere(matiere,formateur);
			loadMatieres();
			resetMatiere();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/matieres/matieres.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteMatiere(Matiere s) {
		try {
			getMatiereFacade().deleteMatiere(s);
			loadMatieres();
			resetMatiere();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/matieres/matieres.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Filiere> completeFilieres(String nSerie) {
		List<Filiere> queryResult = new ArrayList<Filiere>();

		if (allFilieres == null) {
			filiereFacade = new FiliereFacade();
			allFilieres = filiereFacade.listAll();
		}

		for (Filiere filiere : allFilieres) {
			queryResult.add(filiere);
		}

		return queryResult;
	}

}