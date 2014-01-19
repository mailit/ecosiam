package ma.ecosiam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Etablissement")
public class Etablissement {
	
	private java.util.Date dateAutorisation;
	private String emailEtablissement;
	private int idEtablissement;
	private String libelleEtablissement;
	private String numAutorisation;
	private String telEtablissement;
	private Ville ville;

	private List<Formation> formations;
//	
//	private Utilisateur gestionnaire;

	public Etablissement()
	{
		
	}
//	public Etablissement(Date dateAutorisation, String emailEtablissement,
//			int idEtablissement, String libelleEtablissement,
//			String numAutorisation, String telEtablissement,
//			List<Formation> formations, Ville ville, Utilisateur gestionnaire) {
//		super();
//		this.dateAutorisation = dateAutorisation;
//		this.emailEtablissement = emailEtablissement;
//		this.idEtablissement = idEtablissement;
//		this.libelleEtablissement = libelleEtablissement;
//		this.numAutorisation = numAutorisation;
//		this.telEtablissement = telEtablissement;
//		this.formations = formations;
//		this.ville = ville;
//		this.gestionnaire = gestionnaire;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idEtablissement")
	public int getIdEtablissement() {
		return idEtablissement;
	}

	
	public Etablissement(Date dateAutorisation, String emailEtablissement,
		int idEtablissement, String libelleEtablissement,
		String numAutorisation, String telEtablissement, Ville ville,
		List<Formation> formations) {
	super();
	this.dateAutorisation = dateAutorisation;
	this.emailEtablissement = emailEtablissement;
	this.idEtablissement = idEtablissement;
	this.libelleEtablissement = libelleEtablissement;
	this.numAutorisation = numAutorisation;
	this.telEtablissement = telEtablissement;
	this.ville = ville;
	this.formations = formations;
}
	public void setIdEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	
	@Column(name = "dateAutorisation")
	public java.util.Date getDateAutorisation() {
		return dateAutorisation;
	}

	public void setDateAutorisation(java.util.Date dateAutorisation) {
		this.dateAutorisation = dateAutorisation;
	}

	@Column(name = "emailEtablissement")
	public String getEmailEtablissement() {
		return emailEtablissement;
	}

	public void setEmailEtablissement(String emailEtablissement) {
		this.emailEtablissement = emailEtablissement;
	}

	
	@Column(name = "libelleEtablissement")
	public String getLibelleEtablissement() {
		return libelleEtablissement;
	}

	public void setLibelleEtablissement(String libelleEtablissement) {
		this.libelleEtablissement = libelleEtablissement;
	}
	@Column(name = "numAutorisation")
	public String getNumAutorisation() {
		return numAutorisation;
	}

	public void setNumAutorisation(String numAutorisation) {
		this.numAutorisation = numAutorisation;
	}

	@Column(name = "telEtablissement")
	public String getTelEtablissement() {
		return telEtablissement;
	}

	public void setTelEtablissement(String telEtablissement) {
		this.telEtablissement = telEtablissement;
	}

	@OneToMany(mappedBy="etablissement")
	public List<Formation> getFormations() {
		return formations;
	}

	
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	
	@ManyToOne()
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	@JoinColumn(name="ville")
	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
//	@Column(name = "gestionnaire")
//	public Utilisateur getGestionnaire() {
//		return gestionnaire;
//	}
//
//	public void setGestionnaire(Utilisateur gestionnaire) {
//		this.gestionnaire = gestionnaire;
//	}
}
