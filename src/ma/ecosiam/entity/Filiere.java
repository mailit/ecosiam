package ma.ecosiam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Filiere")
public class Filiere {

	private java.util.Date dateCreationFiliere;
	private int idFiliere;
	private String libelleFiliere;
	private double prixFormation;
	private Formation formation;

	private List<Classe> classes;

	private List<Matiere> matieres;

	//
	// private Utilisateur responsableFiliere;

	public Filiere() {

	}

	public Filiere(Date dateCreationFiliere, int idFiliere,
			String libelleFiliere, double prixFormation, Formation formation,
			List<Classe> classes, List<Matiere> matieres) {
		super();
		this.dateCreationFiliere = dateCreationFiliere;
		this.idFiliere = idFiliere;
		this.libelleFiliere = libelleFiliere;
		this.prixFormation = prixFormation;
		this.formation = formation;
		this.classes = classes;
		this.matieres = matieres;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFiliere")
	public int getIdFiliere() {
		return idFiliere;
	}

	public void setIdFiliere(int idFiliere) {
		this.idFiliere = idFiliere;
	}

	@Column(name = "dateCreationFiliere")
	public java.util.Date getDateCreationFiliere() {
		return dateCreationFiliere;
	}

	public void setDateCreationFiliere(java.util.Date dateCreationFiliere) {
		this.dateCreationFiliere = dateCreationFiliere;
	}

	@Column(name = "libelleFiliere")
	public String getLibelleFiliere() {
		return libelleFiliere;
	}

	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}

	@Column(name = "prixFormation")
	public double getPrixFormation() {
		return prixFormation;
	}

	public void setPrixFormation(double prixFormation) {
		this.prixFormation = prixFormation;
	}

	@ManyToOne
	@JoinColumn(name = "formation")
	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	@OneToMany(mappedBy="filiere")
	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classe) {
		this.classes = classe;
	}

	@ManyToMany()
	@JoinTable(name = "matiere_filiere", joinColumns = { @JoinColumn(name = "filiere") }, inverseJoinColumns = { @JoinColumn(name = "matiere") })
	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	// @Column(name = "responsableFiliere")
	// public Utilisateur getResponsableFiliere() {
	// return responsableFiliere;
	// }
	//
	// public void setResponsableFiliere(Utilisateur responsableFiliere) {
	// this.responsableFiliere = responsableFiliere;
	// }
	//

}
