package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.MatiereDAO;
import ma.ecosiam.dao.FormateurDAO;
import ma.ecosiam.entity.Formateur;
import ma.ecosiam.entity.Matiere;

public class MatiereFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private MatiereDAO matiereDAO = new MatiereDAO();
	private FormateurDAO formateurDAO= new FormateurDAO();


	public void createMatiere(Matiere matiere, Formateur formateur) {
		matiereDAO.beginTransaction();
		formateurDAO.beginTransaction();
		matiere.setFormateur(formateur);
		matiereDAO.save(matiere);
		matiereDAO.commitAndCloseTransaction();
	}

	public List<Matiere> listAll() {
		matiereDAO.beginTransaction();
		List<Matiere> result = matiereDAO.findAll();
		matiereDAO.closeTransaction();
		return result;
	}

	public void updateMatiere(Matiere matiere, Formateur formateur) {
		matiereDAO.beginTransaction();
		Matiere persistedMatiere = matiereDAO.find(matiere.getIdMatiere());
		persistedMatiere.setCoeffMatiere(matiere.getCoeffMatiere());
		persistedMatiere.setLibelleMatiere(matiere.getLibelleMatiere());
		persistedMatiere.setFormateur(formateur);
		matiereDAO.update(persistedMatiere);
		matiereDAO.commitAndCloseTransaction();
	}

	public Matiere findMatiere(int matiereId) {
		matiereDAO.beginTransaction();
		Matiere matiere = matiereDAO.find(matiereId);
		matiereDAO.closeTransaction();
		return matiere;
	}

	public void deleteMatiere(Matiere matiere) {
		matiereDAO.beginTransaction();
		Matiere persistedMatiere = matiereDAO.find(matiere.getIdMatiere());
		matiereDAO.delete(persistedMatiere);
		matiereDAO.commitAndCloseTransaction();
	}
}
