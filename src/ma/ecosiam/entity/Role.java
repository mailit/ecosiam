package ma.ecosiam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {
	private int idRole;
	private String role;
	private String description;
	private List<Utilisateur> utilisateurs;
	
	public Role()
	{
		
	}
	public Role(int idRole, String role, String description,
			List<Utilisateur> utilisateurs) {
		super();
		this.idRole = idRole;
		this.role = role;
		this.description = description;
		this.utilisateurs = utilisateurs;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idRole")
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	@Column(name = "role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="role")
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
}
