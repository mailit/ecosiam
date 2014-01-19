package ma.ecosiam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import ma.ecosiam.entity.Etablissement;


@Entity
@Table(name = "Ville")
public class Ville {

	private int idVille;
	private String nomVille;
	private List<Etablissement> etablissements;
	
	public Ville()
	{
		
	}
	public Ville(int idVille, String nomVille, List<Etablissement> etablissements) {
		super();
		this.idVille = idVille;
		this.nomVille = nomVille;
		this.etablissements = etablissements;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idVille")
	public int getIdVille() {
		return idVille;
	}
	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}
	
	@Column(name = "nomVille")
	public String getNomVille() {
		return nomVille;
	}
	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}
	
	@OneToMany(mappedBy="ville")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	public List<Etablissement> getEtablissements() {
		return etablissements;
	}
	public void setEtablissements(List<Etablissement> etablissements) {
		this.etablissements = etablissements;
	}	
}
