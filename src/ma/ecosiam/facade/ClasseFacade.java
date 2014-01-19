package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.ClasseDAO;
import ma.ecosiam.dao.FiliereDAO;
import ma.ecosiam.entity.Classe;
import ma.ecosiam.entity.Filiere;

public class ClasseFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private ClasseDAO classeDAO = new ClasseDAO();
	private FiliereDAO filiereDAO = new FiliereDAO();

	public void createClasse(Classe classe, Filiere filiere) {
		classeDAO.beginTransaction();
		filiereDAO.beginTransaction();
		classe.setFiliere(filiere);
		classeDAO.save(classe);
		classeDAO.commitAndCloseTransaction();
	}

	public List<Classe> listAll() {
		classeDAO.beginTransaction();
		List<Classe> result = classeDAO.findAll();
		classeDAO.closeTransaction();
		return result;
	}

	public void updateClasse(Classe classe, Filiere filiere) {
		classeDAO.beginTransaction();
		Classe persistedClasse = classeDAO.find(classe.getIdClasse());
		persistedClasse.setFiliere(filiere);
		persistedClasse.setLibelleClasse(classe.getLibelleClasse());
		classeDAO.update(persistedClasse);
		classeDAO.commitAndCloseTransaction();
	}

	public Classe findClasse(int classeId) {
		classeDAO.beginTransaction();
		Classe classe = classeDAO.find(classeId);
		classeDAO.closeTransaction();
		return classe;
	}

	public void deleteClasse(Classe classe) {
		classeDAO.beginTransaction();
		Classe persistedClasse = classeDAO.find(classe.getIdClasse());
		classeDAO.delete(persistedClasse);
		classeDAO.commitAndCloseTransaction();
	}
}
