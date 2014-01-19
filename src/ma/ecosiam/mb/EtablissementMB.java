package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Etablissement;
import ma.ecosiam.entity.Ville;
import ma.ecosiam.facade.EtablissementFacade;
import ma.ecosiam.facade.VilleFacade;


@ViewScoped
@ManagedBean(name = "etablissementMB")
public class EtablissementMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Etablissement> etablissements;
	private List<Etablissement> etablissementsFiltres;

	private Etablissement etablissement;
	private EtablissementFacade etablissementFacade;

	private List<Ville> villes;
	private VilleFacade villeFacade;
	
	private Ville ville;
	
	
	
	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	
	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}

	public EtablissementFacade getEtablissementFacade() {
		if (etablissementFacade == null) {
			etablissementFacade = new EtablissementFacade();
		}

		return etablissementFacade;
	}

	public List<Ville> completeVilles(String nSerie) {
		List<Ville> queryResult = new ArrayList<Ville>();

		if (villes == null) {
			villeFacade = new VilleFacade();
			villes = villeFacade.listAll();
		}

		for (Ville ville : villes) {
			queryResult.add(ville);
		}

		return queryResult;
	}
	
	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }  
	public List<Etablissement> getEtablissementsFiltres() {
		return etablissementsFiltres;
	}


	public void setEtablissementsFiltres(List<Etablissement> etablissementsFiltres) {
		this.etablissementsFiltres = etablissementsFiltres;
	}


	public Etablissement getEtablissement() {
		if (etablissement == null) {
			etablissement = new Etablissement();
		}

		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public void createEtablissement() {
		try {
			getEtablissementFacade().createEtablissement(etablissement,ville);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/etablissements/etablissements.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadEtablissements() {
		etablissements = getEtablissementFacade().listAll();
	}

	public List<Etablissement> getEtablissements() {
		if (etablissements == null) {
			loadEtablissements();
		}
		return etablissements;
	}

	public void resetEtablissement() {
		etablissement = new Etablissement();
	}

	public void updateEtablissement() {
		try {
			getEtablissementFacade().updateEtablissement(etablissement,ville);
			loadEtablissements();
			resetEtablissement();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/etablissements/etablissements.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteEtablissement(Etablissement s) {
		try {
			getEtablissementFacade().deleteEtablissement(s);
			loadEtablissements();
			resetEtablissement();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/etablissements/etablissements.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}