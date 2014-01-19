package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.SpecialiteDAO;
import ma.ecosiam.entity.Specialite;

public class SpecialiteFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private SpecialiteDAO specialiteDAO = new SpecialiteDAO();
	
	public void createSpecialite(Specialite specialite) {
		specialiteDAO.beginTransaction();
		specialiteDAO.save(specialite);
		specialiteDAO.commitAndCloseTransaction();
	}

	public List<Specialite> listAll() {
		specialiteDAO.beginTransaction();
		List<Specialite> result = specialiteDAO.findAll();
		specialiteDAO.closeTransaction();
		return result;
	}

	public void updateSpecialite(Specialite specialite) {
		specialiteDAO.beginTransaction();
		Specialite persistedSpecialite = specialiteDAO.find(specialite.getIdSpecialite());
		persistedSpecialite.setLibelleSpecialite(specialite.getLibelleSpecialite());
		specialiteDAO.update(persistedSpecialite);
		specialiteDAO.commitAndCloseTransaction();
	}

	public Specialite findSpecialite(int specialiteId) {
		specialiteDAO.beginTransaction();
		Specialite specialite = specialiteDAO.find(specialiteId);
		specialiteDAO.closeTransaction();
		return specialite;
	}

	public void deleteSpecialite(Specialite specialite) {
		specialiteDAO.beginTransaction();
		Specialite persistedSpecialite = specialiteDAO.find(specialite.getIdSpecialite());
		specialiteDAO.delete(persistedSpecialite);
		specialiteDAO.commitAndCloseTransaction();
	}

}
