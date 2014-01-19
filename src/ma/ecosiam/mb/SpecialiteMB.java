package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Specialite;
import ma.ecosiam.facade.SpecialiteFacade;

@ViewScoped
@ManagedBean(name = "specialiteMB")
public class SpecialiteMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Specialite> specialites;
	private List<Specialite> specialitesFiltres;

	private Specialite specialite;
	private SpecialiteFacade specialiteFacade;

	public SpecialiteFacade getSpecialiteFacade() {
		if (specialiteFacade == null) {
			specialiteFacade = new SpecialiteFacade();
		}

		return specialiteFacade;
	}

	private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }  
	public List<Specialite> getSpecialitesFiltres() {
		return specialitesFiltres;
	}


	public void setSpecialitesFiltres(List<Specialite> specialitesFiltres) {
		this.specialitesFiltres = specialitesFiltres;
	}


	public Specialite getSpecialite() {
		if (specialite == null) {
			specialite = new Specialite();
		}

		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public void createSpecialite() {
		try {
			getSpecialiteFacade().createSpecialite(specialite);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadSpecialites() {
		specialites = getSpecialiteFacade().listAll();
	}

	public List<Specialite> getSpecialites() {
		if (specialites == null) {
			loadSpecialites();
		}
		return specialites;
	}

	public void resetSpecialite() {
		specialite = new Specialite();
	}

	public void updateSpecialite() {
		try {
			getSpecialiteFacade().updateSpecialite(specialite);
			loadSpecialites();
			resetSpecialite();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteSpecialite(Specialite s) {
		try {
			getSpecialiteFacade().deleteSpecialite(s);
			loadSpecialites();
			resetSpecialite();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}