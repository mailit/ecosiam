package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.VilleDAO;
import ma.ecosiam.entity.Ville;

public class VilleFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private VilleDAO villeDAO = new VilleDAO();

	public void createVille(Ville ville) {
		villeDAO.beginTransaction();
		villeDAO.save(ville);
		villeDAO.commitAndCloseTransaction();
	}

	public List<Ville> listAll() {
		villeDAO.beginTransaction();
		List<Ville> result = villeDAO.findAll();
		villeDAO.closeTransaction();
		return result;
	}

	public void updateVille(Ville ville) {
		villeDAO.beginTransaction();
		Ville persistedVille = villeDAO.find(ville.getIdVille());
		persistedVille.setNomVille(ville.getNomVille());
		villeDAO.update(persistedVille);
		villeDAO.commitAndCloseTransaction();
	}

	public Ville findVille(int villeId) {
		villeDAO.beginTransaction();
		Ville ville = villeDAO.find(villeId);
		villeDAO.closeTransaction();
		return ville;
	}

	public void deleteVille(Ville ville) {
		villeDAO.beginTransaction();
		Ville persistedVille = villeDAO.find(ville.getIdVille());
		villeDAO.delete(persistedVille);
		villeDAO.commitAndCloseTransaction();
	}
}
