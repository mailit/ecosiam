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
@Table(name = "Formateur")
public class Formateur{

	private int idFormateur;
	private java.util.Date dateNaissance;
	private String ribFormateur;
	private List<Specialite> specialites;
	private Utilisateur utilisateur;
	private List<Matiere> matieres;
	public Formateur() {
	}

	
	public Formateur(int idFormateur, Date dateNaissance, String ribFormateur,
			List<Specialite> specialites, Utilisateur utilisateur,
			List<Matiere> matieres) {
		super();
		this.idFormateur = idFormateur;
		this.dateNaissance = dateNaissance;
		this.ribFormateur = ribFormateur;
		this.specialites = specialites;
		this.utilisateur = utilisateur;
		this.matieres = matieres;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFomateur")
	public int getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}

	@ManyToOne
	@JoinColumn(name = "utilisateur")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "dateNaissance")
	public java.util.Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(java.util.Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Column(name = "ribFormateur")
	public String getRibFormateur() {
		return ribFormateur;
	}

	public void setRibFormateur(String ribFormateur) {
		this.ribFormateur = ribFormateur;
	}


	@ManyToMany()
	@JoinTable(name = "formateur_specialite", joinColumns = { @JoinColumn(name = "formateur") }, inverseJoinColumns = { @JoinColumn(name = "specialite") })
	public List<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(List<Specialite> specialites) {
		this.specialites = specialites;
	}

	@OneToMany(mappedBy="formateur")
	public List<Matiere> getMatieres() {
		return matieres;
	}
	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	
}
