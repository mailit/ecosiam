package ma.ecosiam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Formation")
public class Formation {

	private java.util.Date dateCreationFormation;
	private int idFormation;
	private String libelleFormation;
	private Etablissement etablissement;

	private List<Filiere> filiere;

	// private Utilisateur administrateur;

	public Formation() {

	}

	public Formation(Date dateCreationFormation, int idFormation,
			String libelleFormation, Etablissement etablissement,
			List<Filiere> filiere) {
		super();
		this.dateCreationFormation = dateCreationFormation;
		this.idFormation = idFormation;
		this.libelleFormation = libelleFormation;
		this.etablissement = etablissement;
		this.filiere = filiere;
	}

	// public Formation(Date dateCreationFormation, int idFormation,
	// String libelleFormation, Etablissement etablissement,
	// List<ma.ecosiam.entity.Filiere> filiere, Utilisateur administrateur) {
	// super();
	// this.dateCreationFormation = dateCreationFormation;
	// this.idFormation = idFormation;
	// this.libelleFormation = libelleFormation;
	// this.etablissement = etablissement;
	// filiere = filiere;
	// this.administrateur = administrateur;
	// }
	//
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFormation")
	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	@Column(name = "dateCreationFormation")
	public java.util.Date getDateCreationFormation() {
		return dateCreationFormation;
	}

	public void setDateCreationFormation(java.util.Date dateCreationFormation) {
		this.dateCreationFormation = dateCreationFormation;
	}

	@Column(name = "libelleFormation")
	public String getLibelleFormation() {
		return libelleFormation;
	}

	public void setLibelleFormation(String libelleFormation) {
		this.libelleFormation = libelleFormation;
	}

	@ManyToOne
	@JoinColumn(name = "etablissement")
	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	
	@OneToMany(mappedBy="formation")
	 public List<Filiere> getFiliere() {
	 return filiere;
	 }
	
	 public void setFiliere(List<Filiere> filiere) {
	 this.filiere = filiere;
	 }
	
	// @Column(name = "administrateur")
	// public Utilisateur getAdministrateur() {
	// return administrateur;
	// }
	//
	// public void setAdministrateur(Utilisateur administrateur) {
	// this.administrateur = administrateur;
	// }
	//

}
