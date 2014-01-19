package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Ville;
import ma.ecosiam.facade.VilleFacade;

@ViewScoped
@ManagedBean(name = "villeMB")
public class VilleMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Ville> villes;
	private List<Ville> villesFiltres;

	private Ville ville;
	private VilleFacade villeFacade;

	public VilleFacade getVilleFacade() {
		if (villeFacade == null) {
			villeFacade = new VilleFacade();
		}

		return villeFacade;
	}

	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }  
	public List<Ville> getVillesFiltres() {
		return villesFiltres;
	}


	public void setVillesFiltres(List<Ville> villesFiltres) {
		this.villesFiltres = villesFiltres;
	}


	public Ville getVille() {
		if (ville == null) {
			ville = new Ville();
		}

		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public void createVille() {
		try {
			getVilleFacade().createVille(ville);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/villes/villes.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadVilles() {
		villes = getVilleFacade().listAll();
	}

	public List<Ville> getVilles() {
		if (villes == null) {
			loadVilles();
		}
		return villes;
	}

	public void resetVille() {
		ville = new Ville();
	}

	public void updateVille() {
		try {
			getVilleFacade().updateVille(ville);
			loadVilles();
			resetVille();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/villes/villes.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteVille(Ville s) {
		try {
			getVilleFacade().deleteVille(s);
			loadVilles();
			resetVille();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/villes/villes.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}