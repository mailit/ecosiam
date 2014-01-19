package ma.ecosiam.entity;

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
@Table(name = "Classe")
public class Classe {
	
	private int idClasse;
	private String libelleClasse;
	private Filiere filiere;
	
	private List<Stagiaire> stagiaires;

	
	public Classe(){}
	
	public Classe(int idClasse, String libelleClasse, Filiere filiere) {
		super();
		this.idClasse = idClasse;
		this.libelleClasse = libelleClasse;
		this.filiere = filiere;
		//this.stagiaires = stagiaires;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idClasse")
	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	@Column(name = "libelleClasse")
	public String getLibelleClasse() {
		return libelleClasse;
	}

	public void setLibelleClasse(String libelleClasse) {
		this.libelleClasse = libelleClasse;
	}

	@ManyToOne
	@JoinColumn(name = "filiere")	
	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	@OneToMany(mappedBy="classe")
	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	

}
