package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.FiliereDAO;
import ma.ecosiam.dao.FormationDAO;
import ma.ecosiam.dao.MatiereDAO;
import ma.ecosiam.entity.Filiere;
import ma.ecosiam.entity.Formation;
import ma.ecosiam.entity.Matiere;

public class FiliereFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private FiliereDAO filiereDAO = new FiliereDAO();
	private FormationDAO formationDAO = new FormationDAO();
	private MatiereDAO matiereDAO = new MatiereDAO();

	public void createFiliere(Filiere filiere, Formation formation) {
		filiereDAO.beginTransaction();
		formationDAO.beginTransaction();
		filiere.setFormation(formation);
		filiereDAO.save(filiere);
		filiereDAO.commitAndCloseTransaction();
	}

	public List<Filiere> listAll() {
		filiereDAO.beginTransaction();
		List<Filiere> result = filiereDAO.findAll();
		filiereDAO.closeTransaction();
		return result;
	}

	public void ajouterMatiere(Filiere filiere,Matiere matiere)
	{
		filiereDAO.beginTransaction();
		matiereDAO.joinTransaction();
		matiere = matiereDAO.find(matiere.getIdMatiere());
		filiere = filiereDAO.find(filiere.getIdFiliere());
		filiere.getMatieres().add(matiere);
		matiere.getFilieres().add(filiere);
		filiereDAO.commitAndCloseTransaction();
		
	}
	public void updateFiliere(Filiere filiere, Formation formation) {
		filiereDAO.beginTransaction();
		Filiere persistedFiliere = filiereDAO.find(filiere.getIdFiliere());
		persistedFiliere.setDateCreationFiliere(filiere
				.getDateCreationFiliere());
		persistedFiliere.setFormation(formation);
		persistedFiliere.setLibelleFiliere(filiere.getLibelleFiliere());
		persistedFiliere.setPrixFormation(filiere.getPrixFormation());

		filiereDAO.update(persistedFiliere);
		filiereDAO.commitAndCloseTransaction();
	}

	public Filiere findFiliere(int filiereId) {
		filiereDAO.beginTransaction();
		Filiere filiere = filiereDAO.find(filiereId);
		filiereDAO.closeTransaction();
		return filiere;
	}

	public void deleteFiliere(Filiere filiere) {
		filiereDAO.beginTransaction();
		Filiere persistedFiliere = filiereDAO.find(filiere.getIdFiliere());
		filiereDAO.delete(persistedFiliere);
		filiereDAO.commitAndCloseTransaction();
	}
}
