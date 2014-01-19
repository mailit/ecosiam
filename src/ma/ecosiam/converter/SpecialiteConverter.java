package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Specialite;
import ma.ecosiam.facade.SpecialiteFacade;

@ManagedBean(name="specialiteConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Specialite.class)
public class SpecialiteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		SpecialiteFacade specialiteFacade = new SpecialiteFacade();
		int nSpecialite;

		try {
			nSpecialite = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Specialite and select it (or use the dropdow)", "Type the name of a Specialite and select it (or use the dropdow)"));
		}

		return specialiteFacade.findSpecialite(nSpecialite);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Specialite specialite = (Specialite) arg2;
		return String.valueOf(specialite.getIdSpecialite());
	}
}