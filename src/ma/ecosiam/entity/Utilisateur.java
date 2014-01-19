package ma.ecosiam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

	private int idUser;
	private String emailUser;
	private String imageUser;
	private String loginUser;
	private String nomUser;
	private String passwordUser;
	private String prenomUser;
	private String telUser;
	private Role role;

	private List<Stagiaire> stagiaires;
	private List<Formateur> formateurs;

	public Utilisateur() {

	}

	public Utilisateur(int idUser, String emailUser, String imageUser,
			String loginUser, String nomUser, String passwordUser,
			String prenomUser, String telUser, Role role,
			List<Stagiaire> stagiaires, List<Formateur> formateurs) {
		super();
		this.idUser = idUser;
		this.emailUser = emailUser;
		this.imageUser = imageUser;
		this.loginUser = loginUser;
		this.nomUser = nomUser;
		this.passwordUser = passwordUser;
		this.prenomUser = prenomUser;
		this.telUser = telUser;
		this.role = role;
		this.stagiaires = stagiaires;
		this.formateurs = formateurs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUser")
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "emailUser")
	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	@Column(name = "imageUser")
	public String getImageUser() {
		return imageUser;
	}

	public void setImageUser(String imageUser) {
		this.imageUser = imageUser;
	}

	@Column(name = "loginUser")
	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	@Column(name = "nomUser")
	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	@Column(name = "passwordUser")
	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	@Column(name = "prenomUser")
	public String getPrenomUser() {
		return prenomUser;
	}

	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}

	@Column(name = "telUser")
	public String getTelUser() {
		return telUser;
	}

	public void setTelUser(String telUser) {
		this.telUser = telUser;
	}

	@OneToMany(mappedBy = "utilisateur")
	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	@OneToMany(mappedBy = "utilisateur")
	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	@ManyToOne()
	@JoinColumn(name = "role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
