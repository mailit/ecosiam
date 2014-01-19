package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ma.ecosiam.dao.UtilisateurDAO;
import ma.ecosiam.entity.Utilisateur;

public class UtilisateurFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

	public void createUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.beginTransaction();
		utilisateurDAO.save(utilisateur);
		utilisateurDAO.commitAndCloseTransaction();
	}

	public List<Utilisateur> listAll() {
		utilisateurDAO.beginTransaction();
		List<Utilisateur> result = utilisateurDAO.findAll();
		utilisateurDAO.closeTransaction();
		return result;
	}

	public void updateUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.beginTransaction();
		Utilisateur persistedUtilisateur = utilisateurDAO.find(utilisateur
				.getIdUser());

		utilisateurDAO.update(persistedUtilisateur);
		utilisateurDAO.commitAndCloseTransaction();
	}

	public Utilisateur findUtilisateur(int utilisateurId) {
		utilisateurDAO.beginTransaction();
		Utilisateur utilisateur = utilisateurDAO.find(utilisateurId);
		utilisateurDAO.closeTransaction();
		return utilisateur;
	}

	public void deleteUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.beginTransaction();
		Utilisateur persistedUtilisateur = utilisateurDAO.find(utilisateur
				.getIdUser());

		utilisateurDAO.delete(persistedUtilisateur);
		utilisateurDAO.commitAndCloseTransaction();
	}

	public Utilisateur estValide(String nomUtilisateur, String motDePasse) {
		utilisateurDAO.beginTransaction();
		Utilisateur utilisateur = utilisateurDAO
				.trouverUtilisateurParNom(nomUtilisateur);
		if (utilisateur == null
				|| !utilisateur.getPasswordUser().equals(motDePasse)) {
			return null;
		}
		return utilisateur;
	}

	public void modifierPass(Utilisateur utilisateur, String pass, String login) {

		utilisateurDAO.beginTransaction();
		Utilisateur persistedUtilisateur = utilisateurDAO
				.trouverUtilisateurParNom(login);
		if (pass.compareTo(persistedUtilisateur.getPasswordUser()) == 0) {
			persistedUtilisateur.setPasswordUser(utilisateur.getPasswordUser());
			utilisateurDAO.update(persistedUtilisateur);
			utilisateurDAO.emailModifierPass(persistedUtilisateur);
			utilisateurDAO.commitAndCloseTransaction();
		}
	}

	public void email(Utilisateur utilisateur) {

		utilisateurDAO.email(utilisateur);
	}
}
