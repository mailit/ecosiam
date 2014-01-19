package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Stagiaire;
import ma.ecosiam.facade.StagiaireFacade;

@ManagedBean(name="stagiaireConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Stagiaire.class)
public class StagiaireConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		StagiaireFacade stagiaireFacade = new StagiaireFacade();
		int nStagiaire;

		try {
			nStagiaire = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Stagiaire and select it (or use the dropdow)", "Type the name of a Stagiaire and select it (or use the dropdow)"));
		}

		return stagiaireFacade.findStagiaire(nStagiaire);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Stagiaire stagiaire = (Stagiaire) arg2;
		return String.valueOf(stagiaire.getIdStagiaire());
	}
}