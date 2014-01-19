package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Formateur;
import ma.ecosiam.facade.FormateurFacade;

@ManagedBean(name="formateurConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Formateur.class)
public class FormateurConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		FormateurFacade formateurFacade = new FormateurFacade();
		int nFormateur;

		try {
			nFormateur = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Formateur and select it (or use the dropdow)", "Type the name of a Formateur and select it (or use the dropdow)"));
		}

		return formateurFacade.findFormateur(nFormateur);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Formateur formateur = (Formateur) arg2;
		return String.valueOf(formateur.getIdFormateur());
	}
}