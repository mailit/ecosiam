package ma.ecosiam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ma.ecosiam.entity.Classe;
import ma.ecosiam.entity.Filiere;
import ma.ecosiam.facade.ClasseFacade;
import ma.ecosiam.facade.FiliereFacade;

@ViewScoped
@ManagedBean(name = "classeMB")
public class ClasseMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Classe> classes;
	private List<Classe> classesFiltres;

	private Classe classe;
	private ClasseFacade classeFacade;
	
	private List<Filiere> filieres;
	private FiliereFacade filiereFacade;

	private Filiere filiere;
	
	
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public ClasseFacade getClasseFacade() {
		if (classeFacade == null) {
			classeFacade = new ClasseFacade();
		}

		return classeFacade;
	}

	public List<Filiere> completeFilieres(String nSerie) {
		List<Filiere> queryResult = new ArrayList<Filiere>();

		if (filieres == null) {
			filiereFacade = new FiliereFacade();
			filieres = filiereFacade.listAll();
		}

		for (Filiere filiere : filieres) {
			queryResult.add(filiere);
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
	public List<Classe> getClassesFiltres() {
		return classesFiltres;
	}


	public void setClassesFiltres(List<Classe> classesFiltres) {
		this.classesFiltres = classesFiltres;
	}


	public Classe getClasse() {
		if (classe == null) {
			classe = new Classe();
		}

		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public void createClasse() {
		try {
			getClasseFacade().createClasse(classe,filiere);
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/classes/classes.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadClasses() {
		classes = getClasseFacade().listAll();
	}

	public List<Classe> getClasses() {
		if (classes == null) {
			loadClasses();
		}
		return classes;
	}

	public void resetClasse() {
		classe = new Classe();
	}

	public void updateClasse() {
		try {
			getClasseFacade().updateClasse(classe,filiere);
			loadClasses();
			resetClasse();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/classes/classes.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteClasse(Classe s) {
		try {
			getClasseFacade().deleteClasse(s);
			loadClasses();
			resetClasse();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"/CECOSIAM/pages/prive/utilisateur/formation/classes/classes.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}