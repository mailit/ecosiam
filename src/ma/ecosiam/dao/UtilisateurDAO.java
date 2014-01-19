package ma.ecosiam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ma.ecosiam.entity.Utilisateur;

public class UtilisateurDAO extends GenericDAO<Utilisateur> {

	private static final long serialVersionUID = 1L;

	public UtilisateurDAO() {
		super(Utilisateur.class);
	}

	public Utilisateur trouverUtilisateurParNom(String loginUser) {
		// Map<String, Object> parameters = new HashMap<String, Object>();
		// parameters.put("loginUser", loginUser);
		List<Utilisateur> users = findAll();
		for (Utilisateur u : users) {
			if (u.getLoginUser().compareTo(loginUser) == 0)
				return u;
		}
		return null;
	}

	public void email(Utilisateur utilisateur) {

		String to = utilisateur.getEmailUser();
		String subject = "Bienvenue dans l'ECOSIAM";
		String l0="Bonjour " + utilisateur.getNomUser() + "\n";
		String l1 = "Bienvenue sur l'ECOSIAM ! Vous pouvez utiliser votre compte pour accedez à l'application de gestion de l'ECOSIAM \n";
		String l2 = "Voici les details de votre compte : \n";
		String l3 = "Nom d'utilisateur : " + utilisateur.getLoginUser() + "\n";
		String l4 = "Mot de passe : " + utilisateur.getPasswordUser() + "\n";
		String l5 = "Cordialement";

		String text = l0 + l1 + l2 + l3 + l4 + l5;

		String from = "ecosiam.application";
		String pass = "ecosiamecosiam";

		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);

			message.addRecipient(Message.RecipientType.TO, toAddress);

			message.setSubject(subject);
			message.setText(text);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	public void emailModifierPass(Utilisateur utilisateur) {

		String to = utilisateur.getEmailUser();
		String subject = "Modification du mot de passe";
		String l1 = "Votre mot de passe a été modifié \n";
		String l2 = "Votre nouveau mot de passe est : \n";
		String l4 = "Mot de passe : " + utilisateur.getPasswordUser() + "\n";
		String l5 = "Cordialement";
		String l0="Bonjour " + utilisateur.getNomUser() + "\n";

		String text =l0+ l1 + l2 + l4 + l5;

		String from = "ecosiam.application";
		String pass = "ecosiamecosiam";

		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);

			message.addRecipient(Message.RecipientType.TO, toAddress);

			message.setSubject(subject);
			message.setText(text);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}