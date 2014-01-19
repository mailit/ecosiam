package ma.ecosiam.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Specialite")
public class Specialite {
	
	private int idSpecialite;
	private String libelleSpecialite;
	
	private List<Formateur> formateurs;

	public Specialite()
	{
		
	}
	
	public Specialite(int idSpecialite, String libSpecialite) {
		super();
		this.idSpecialite = idSpecialite;
		this.libelleSpecialite = libSpecialite;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idSpecialite")
	public int getIdSpecialite() {
		return idSpecialite;
	}

	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	@Column(name = "libelleSpecialite")
	public String getLibelleSpecialite() {
		return libelleSpecialite;
	}

	public void setLibelleSpecialite(String libelleSpecialite) {
		this.libelleSpecialite = libelleSpecialite;
	}

	@ManyToMany(mappedBy="specialites")
	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((formateurs == null) ? 0 : formateurs.hashCode());
		result = prime * result + idSpecialite;
		result = prime
				* result
				+ ((libelleSpecialite == null) ? 0 : libelleSpecialite
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialite other = (Specialite) obj;
		if (formateurs == null) {
			if (other.formateurs != null)
				return false;
		} else if (!formateurs.equals(other.formateurs))
			return false;
		if (idSpecialite != other.idSpecialite)
			return false;
		if (libelleSpecialite == null) {
			if (other.libelleSpecialite != null)
				return false;
		} else if (!libelleSpecialite.equals(other.libelleSpecialite))
			return false;
		return true;
	}

	
}
