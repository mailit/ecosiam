package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.EtablissementDAO;
import ma.ecosiam.dao.FormationDAO;
import ma.ecosiam.entity.Etablissement;
import ma.ecosiam.entity.Formation;

public class FormationFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private FormationDAO formationDAO = new FormationDAO();
	private EtablissementDAO etablissementDAO = new EtablissementDAO();

	public void createFormation(Formation formation, Etablissement etablissement) {
		formationDAO.beginTransaction();
		etablissementDAO.beginTransaction();
		formation.setEtablissement(etablissement);
		formationDAO.save(formation);
		formationDAO.commitAndCloseTransaction();
	}

	public List<Formation> listAll() {
		formationDAO.beginTransaction();
		List<Formation> result = formationDAO.findAll();
		formationDAO.closeTransaction();
		return result;
	}

	public void updateFormation(Formation formation, Etablissement etablissement) {
		formationDAO.beginTransaction();
		Formation persistedFormation = formationDAO.find(formation
				.getIdFormation());
		persistedFormation.setDateCreationFormation(formation
				.getDateCreationFormation());
		persistedFormation.setEtablissement(etablissement);
		persistedFormation.setLibelleFormation(formation.getLibelleFormation());
		formationDAO.update(persistedFormation);
		formationDAO.commitAndCloseTransaction();
	}

	public Formation findFormation(int formationId) {
		formationDAO.beginTransaction();
		Formation formation = formationDAO.find(formationId);
		formationDAO.closeTransaction();
		return formation;
	}

	public void deleteFormation(Formation formation) {
		formationDAO.beginTransaction();
		Formation persistedFormation = formationDAO.find(formation
				.getIdFormation());
		formationDAO.delete(persistedFormation);
		formationDAO.commitAndCloseTransaction();
	}
}
