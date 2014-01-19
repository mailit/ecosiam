package ma.ecosiam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	/**
	 * @param args
	 */
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("JSFCrudPU");
	private EntityManager em;

	public static void main(String[] args) {
		try {
			System.out.println("Chargement du driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver chargé !");
		} catch (ClassNotFoundException e) {
			System.out
					.println("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
							+ e.getMessage());
		}

		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/ecosiam";
		String utilisateur = "root";
		String motDePasse = "root";
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			System.out.println("Connexion à la base de données...");
			connexion = DriverManager.getConnection(url, utilisateur,
					motDePasse);
			System.out.println("Connexion réussie !");

			/* Création de l'objet gérant les requêtes */
			statement = connexion.createStatement();
			System.out.println("Objet requête créé !");

			/* Exécution d'une requête de lecture */
			resultat = statement.executeQuery("SELECT * FROM stagiaire;");
			System.out.println("Requête SELECT effectuée !");

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				int idUtilisateur = resultat.getInt("utilisateur");
				String emailUtilisateur = resultat.getString("cneStagiaire");
				/* Formatage des données pour affichage dans la JSP finale. */
				System.out.println("Données retournées par la requête : id = "
						+ idUtilisateur+ ", email = " + emailUtilisateur);
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connexion : <br/>"
					+ e.getMessage());
		} finally {
			System.out.println("Fermeture de l'objet ResultSet.");
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException ignore) {
				}
			}
			System.out.println("Fermeture de l'objet Statement.");
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			System.out.println("Fermeture de l'objet Connection.");
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException ignore) {
				}
			}
		}

	}
}
