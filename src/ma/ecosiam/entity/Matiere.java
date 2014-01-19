package ma.ecosiam.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Matiere")
public class Matiere {
	
	private double coeffMatiere;
	private int idMatiere;
	private String libelleMatiere;
	
	private Formateur formateur;
	
	private List<Filiere> filieres;
	
	public Matiere()
	{
		
	}

	public Matiere(double coeffMatiere, int idMatiere, String libelleMatiere,
			Formateur formateur, List<Filiere> filieres) {
		super();
		this.coeffMatiere = coeffMatiere;
		this.idMatiere = idMatiere;
		this.libelleMatiere = libelleMatiere;
		this.formateur = formateur;
		this.filieres = filieres;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idMatiere")
	public int getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	
	@Column(name = "coeffMatiere")
	public double getCoeffMatiere() {
		return coeffMatiere;
	}
	public void setCoeffMatiere(double coeffMatiere) {
		this.coeffMatiere = coeffMatiere;
	}
	
	@Column(name = "libelleMatiere")
	public String getLibelleMatiere() {
		return libelleMatiere;
	}

	public void setLibelleMatiere(String libelleMatiere) {
		this.libelleMatiere = libelleMatiere;
	}

	 @ManyToMany(mappedBy="matieres")
	 public List<Filiere> getFilieres() {
		return filieres;
	}
	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	@ManyToOne
	@JoinColumn(name="formateur")
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

}
