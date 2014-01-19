package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.ClasseDAO;
import ma.ecosiam.dao.FormateurDAO;
import ma.ecosiam.dao.RoleDAO;
import ma.ecosiam.dao.SpecialiteDAO;
import ma.ecosiam.dao.UtilisateurDAO;
import ma.ecosiam.entity.Formateur;
import ma.ecosiam.entity.Role;
import ma.ecosiam.entity.Specialite;
import ma.ecosiam.entity.Utilisateur;

public class FormateurFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private FormateurDAO formateurDAO = new FormateurDAO();
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
	private ClasseDAO classeDAO = new ClasseDAO();
	private SpecialiteDAO specialiteDAO = new SpecialiteDAO();
	private RoleDAO roleDAO = new RoleDAO();
	private RoleFacade r= new RoleFacade();
	List<Role> res = r.listAll();
	Role role = res.get(1);
	public void createFormateur(Formateur formateur, Utilisateur utilisateur) {
		utilisateurDAO.beginTransaction();
		formateurDAO.beginTransaction();
		utilisateur.setTelUser(utilisateur.getTelUser());
		utilisateur.setRole(role);
		utilisateurDAO.save(utilisateur);
		utilisateurDAO.email(utilisateur);
		utilisateurDAO.commitAndCloseTransaction();
		formateur.setUtilisateur(utilisateur);
		formateurDAO.save(formateur);
		formateurDAO.commitAndCloseTransaction();
	}

	public List<Formateur> listAll() {
		utilisateurDAO.beginTransaction();
		formateurDAO.beginTransaction();
		List<Formateur> result = formateurDAO.findAll();
		formateurDAO.closeTransaction();
		return result;
	}

	public void updateFormateur(Formateur formateur, Utilisateur utilisateur) {
		formateurDAO.beginTransaction();
		utilisateurDAO.beginTransaction();
		roleDAO.beginTransaction();
		Formateur persistedFormateur = formateurDAO.find(formateur
				.getIdFormateur());
		persistedFormateur.setRibFormateur(formateur.getRibFormateur());
		persistedFormateur.setDateNaissance(formateur.getDateNaissance());
		persistedFormateur.setUtilisateur(utilisateur);

		Utilisateur persistedUtilisateur = utilisateurDAO.find(formateur
				.getUtilisateur().getIdUser());
		persistedUtilisateur.setEmailUser(utilisateur.getEmailUser());
		persistedUtilisateur.setNomUser(utilisateur.getNomUser());
		persistedUtilisateur.setPrenomUser(utilisateur.getPrenomUser());
		persistedUtilisateur.setRole(role);
		utilisateurDAO.update(persistedUtilisateur);
		utilisateurDAO.commitAndCloseTransaction();
		formateurDAO.update(persistedFormateur);
		formateurDAO.commitAndCloseTransaction();
	}

	public Formateur findFormateur(int formateurId) {
		formateurDAO.beginTransaction();
		utilisateurDAO.beginTransaction();
		classeDAO.beginTransaction();
		Formateur formateur = formateurDAO.find(formateurId);
		formateurDAO.closeTransaction();
		return formateur;
	}

	public void deleteFormateur(Formateur formateur, Utilisateur utilisateur) {
		formateurDAO.beginTransaction();
		utilisateurDAO.beginTransaction();
		Formateur persistedFormateur = formateurDAO.find(formateur
				.getIdFormateur());
		Utilisateur persisteUtilisateur = utilisateurDAO.find(formateur
				.getUtilisateur().getIdUser());
		formateurDAO.delete(persistedFormateur);
		utilisateurDAO.delete(persisteUtilisateur);
		utilisateurDAO.commitAndCloseTransaction();
		formateurDAO.commitAndCloseTransaction();
	}

	public void ajouterSpecialite(Formateur formateur, Specialite specialite) {
		formateurDAO.beginTransaction();
		specialiteDAO.joinTransaction();
		System.out.println(formateur.getUtilisateur().getNomUser());
		System.out.println(specialite.getLibelleSpecialite());
		specialite = specialiteDAO.find(specialite.getIdSpecialite());
		formateur = formateurDAO.find(formateur.getIdFormateur());
		formateur.getSpecialites().add(specialite);
		specialite.getFormateurs().add(formateur);
		formateurDAO.commitAndCloseTransaction();

	}
}
