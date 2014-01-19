package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Etablissement;
import ma.ecosiam.entity.Formation;
import ma.ecosiam.entity.Ville;
import ma.ecosiam.facade.EtablissementFacade;
import ma.ecosiam.facade.FormationFacade;
import ma.ecosiam.facade.VilleFacade;

@ViewScoped
@ManagedBean(name = "formationMB")
public class FormationMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Formation> formations;
	private List<Formation> formationsFiltres;

	private Formation formation;
	private FormationFacade formationFacade;
	
	private List<Etablissement> etablissements;
	private EtablissementFacade etablissementFacade;
	
	private Etablissement etablissement;
	
	
	public List<Etablissement> getEtablissements() {
		return etablissements;
	}

	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public List<Etablissement> completeEtablissements(String nSerie) {
		List<Etablissement> queryResult = new ArrayList<Etablissement>();

		if (etablissements == null) {
			etablissementFacade = new EtablissementFacade();
			etablissements = etablissementFacade.listAll();
		}

		for (Etablissement etablissement : etablissements) {
			queryResult.add(etablissement);
		}

		return queryResult;
	}

	public FormationFacade getFormationFacade() {
		if (formationFacade == null) {
			formationFacade = new FormationFacade();
		}

		return formationFacade;
	}

	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }  
	public List<Formation> getFormationsFiltres() {
		return formationsFiltres;
	}


	public void setFormationsFiltres(List<Formation> formationsFiltres) {
		this.formationsFiltres = formationsFiltres;
	}


	public Formation getFormation() {
		if (formation == null) {
			formation = new Formation();
		}

		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public void createFormation() {
		try {
			getFormationFacade().createFormation(formation,etablissement);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/formations/formations.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFormations() {
		formations = getFormationFacade().listAll();
	}

	public List<Formation> getFormations() {
		if (formations == null) {
			loadFormations();
		}
		return formations;
	}

	public void resetFormation() {
		formation = new Formation();
	}

	public void updateFormation() {
		try {
			getFormationFacade().updateFormation(formation,etablissement);
			loadFormations();
			resetFormation();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/formations/formations.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteFormation(Formation s) {
		try {
			getFormationFacade().deleteFormation(s);
			loadFormations();
			resetFormation();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/formations/formations.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}