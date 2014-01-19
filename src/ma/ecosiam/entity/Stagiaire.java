package ma.ecosiam.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stagiaire")
public class Stagiaire {

	private int idStagiaire;
	private String cneStagiaire;
	private java.util.Date dateInscription;
	private java.util.Date dateNaissance;
	private Utilisateur utilisateur;
	private Classe classe;

	public Stagiaire() {

	}


	public Stagiaire(int idStagiaire, String cneStagiaire,
			Date dateInscription, Date dateNaissance, Utilisateur utilisateur,
			Classe classe) {
		super();
		this.idStagiaire = idStagiaire;
		this.cneStagiaire = cneStagiaire;
		this.dateInscription = dateInscription;
		this.dateNaissance = dateNaissance;
		this.utilisateur = utilisateur;
		this.classe = classe;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idStagiaire")
	public int getIdStagiaire() {
		return idStagiaire;
	}

	public void setIdStagiaire(int idStagiaire) {
		this.idStagiaire = idStagiaire;
	}

	@ManyToOne
	@JoinColumn(name = "utilisateur")
	public Utilisateur getUtilisateur() {
		if (utilisateur != null)
			return utilisateur;
		else
			return new Utilisateur();
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "cneStagiaire")
	public String getCneStagiaire() {
		return cneStagiaire;
	}

	public void setCneStagiaire(String cneStagiaire) {
		this.cneStagiaire = cneStagiaire;
	}

	@Column(name = "dateInscription")
	public java.util.Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(java.util.Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	@Column(name = "dateNaissance")
	public java.util.Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(java.util.Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@ManyToOne
	@JoinColumn(name = "classe")
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

}
