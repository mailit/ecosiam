package ma.ecosiam.facade;

import java.io.Serializable;
import java.util.List;

import ma.ecosiam.dao.EtablissementDAO;
import ma.ecosiam.dao.VilleDAO;
import ma.ecosiam.entity.Etablissement;
import ma.ecosiam.entity.Ville;

public class EtablissementFacade implements Serializable {
	private static final long serialVersionUID = 1L;

	private EtablissementDAO etablissementDAO = new EtablissementDAO();
	private VilleDAO villeDAO= new VilleDAO();

	public void createEtablissement(Etablissement etablissement, Ville ville) {
		villeDAO.beginTransaction();
		etablissementDAO.beginTransaction();
		etablissement.setVille(ville);
		etablissementDAO.save(etablissement);
		etablissementDAO.commitAndCloseTransaction();

	}

	public List<Etablissement> listAll() {
		etablissementDAO.beginTransaction();
		List<Etablissement> result = etablissementDAO.findAll();
		etablissementDAO.closeTransaction();
		return result;
	}

	public void updateEtablissement(Etablissement etablissement, Ville ville) {
		etablissementDAO.beginTransaction();
		Etablissement persistedEtablissement = etablissementDAO
				.find(etablissement.getIdEtablissement());
		persistedEtablissement.setDateAutorisation(etablissement.getDateAutorisation());
		persistedEtablissement.setEmailEtablissement(etablissement.getEmailEtablissement());
		persistedEtablissement.setLibelleEtablissement(etablissement.getLibelleEtablissement());
		persistedEtablissement.setNumAutorisation(etablissement.getNumAutorisation());
		persistedEtablissement.setTelEtablissement(etablissement.getTelEtablissement());
		persistedEtablissement.setVille(ville);
	
		etablissementDAO.update(persistedEtablissement);
		etablissementDAO.commitAndCloseTransaction();
	}

	public Etablissement findEtablissement(int etablissementId) {
		etablissementDAO.beginTransaction();
		villeDAO.beginTransaction();
		Etablissement etablissement = etablissementDAO.find(etablissementId);
		
		etablissementDAO.closeTransaction();
		return etablissement;
	}

	public void deleteEtablissement(Etablissement etablissement) {
		etablissementDAO.beginTransaction();
		Etablissement persistedEtablissement = etablissementDAO
				.find(etablissement.getIdEtablissement());
		etablissementDAO.delete(persistedEtablissement);
		etablissementDAO.commitAndCloseTransaction();
	}
}
