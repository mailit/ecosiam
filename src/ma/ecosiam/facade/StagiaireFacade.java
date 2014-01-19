package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.ClasseDAO;
import ma.ecosiam.dao.StagiaireDAO;
import ma.ecosiam.dao.UtilisateurDAO;
import ma.ecosiam.entity.Classe;
import ma.ecosiam.entity.Role;
import ma.ecosiam.entity.Stagiaire;
import ma.ecosiam.entity.Utilisateur;

public class StagiaireFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private StagiaireDAO stagiaireDAO = new StagiaireDAO();
	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
	private ClasseDAO classeDAO = new ClasseDAO();
	private RoleFacade r = new RoleFacade();
	List<Role> res = r.listAll();
	Role role = res.get(0);

	public void createStagiaire(Stagiaire stagiaire, Utilisateur utilisateur,
			Classe classe) {
		classeDAO.beginTransaction();
		utilisateurDAO.beginTransaction();
		stagiaireDAO.beginTransaction();
		utilisateur.setRole(role);
		utilisateurDAO.save(utilisateur);
		utilisateurDAO.email(utilisateur);

		utilisateurDAO.commitAndCloseTransaction();

		stagiaire.setClasse(classe);
		stagiaire.setUtilisateur(utilisateur);

		stagiaireDAO.save(stagiaire);
		stagiaireDAO.commitAndCloseTransaction();
	}

	public List<Stagiaire> listAll() {
		utilisateurDAO.beginTransaction();

		stagiaireDAO.beginTransaction();
		List<Stagiaire> result = stagiaireDAO.findAll();
		stagiaireDAO.closeTransaction();
		return result;
	}

	public void updateStagiaire(Stagiaire stagiaire, Utilisateur utilisateur,
			Classe classe) {
		stagiaireDAO.beginTransaction();
		utilisateurDAO.beginTransaction();

		Stagiaire persistedStagiaire = stagiaireDAO.find(stagiaire
				.getIdStagiaire());
		persistedStagiaire.setClasse(classe);
		persistedStagiaire.setCneStagiaire(stagiaire.getCneStagiaire());
		persistedStagiaire.setDateInscription(stagiaire.getDateInscription());
		persistedStagiaire.setDateNaissance(stagiaire.getDateNaissance());
		persistedStagiaire.setUtilisateur(utilisateur);

		Utilisateur persistedUtilisateur = utilisateurDAO.find(stagiaire
				.getUtilisateur().getIdUser());
		persistedUtilisateur.setEmailUser(utilisateur.getEmailUser());
		persistedUtilisateur.setNomUser(utilisateur.getNomUser());
		persistedUtilisateur.setPrenomUser(utilisateur.getPrenomUser());
		persistedUtilisateur.setTelUser(utilisateur.getTelUser());
		utilisateurDAO.update(persistedUtilisateur);
		utilisateurDAO.commitAndCloseTransaction();
		stagiaireDAO.update(persistedStagiaire);
		stagiaireDAO.commitAndCloseTransaction();
	}

	public Stagiaire findStagiaire(int stagiaireId) {
		stagiaireDAO.beginTransaction();
		utilisateurDAO.beginTransaction();
		classeDAO.beginTransaction();
		Stagiaire stagiaire = stagiaireDAO.find(stagiaireId);
		stagiaireDAO.closeTransaction();
		return stagiaire;
	}

	public void deleteStagiaire(Stagiaire stagiaire, Utilisateur utilisateur) {
		stagiaireDAO.beginTransaction();
		utilisateurDAO.beginTransaction();
		Stagiaire persistedStagiaire = stagiaireDAO.find(stagiaire
				.getIdStagiaire());
		Utilisateur persisteUtilisateur = utilisateurDAO.find(stagiaire
				.getUtilisateur().getIdUser());
		stagiaireDAO.delete(persistedStagiaire);
		utilisateurDAO.delete(persisteUtilisateur);
		utilisateurDAO.commitAndCloseTransaction();
		stagiaireDAO.commitAndCloseTransaction();
	}
}
