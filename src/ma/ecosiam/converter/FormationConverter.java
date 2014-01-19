package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Formation;
import ma.ecosiam.facade.FormationFacade;

@ManagedBean(name="formationConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Formation.class)
public class FormationConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		FormationFacade formationFacade = new FormationFacade();
		int nFormation;

		try {
			nFormation = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Formation and select it (or use the dropdow)", "Type the name of a Formation and select it (or use the dropdow)"));
		}

		return formationFacade.findFormation(nFormation);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Formation formation = (Formation) arg2;
		return String.valueOf(formation.getIdFormation());
	}
}