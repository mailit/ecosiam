package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Filiere;
import ma.ecosiam.facade.FiliereFacade;

@ManagedBean(name="filiereConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Filiere.class)
public class FiliereConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		FiliereFacade filiereFacade = new FiliereFacade();
		int nFiliere;

		try {
			nFiliere = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Filiere and select it (or use the dropdow)", "Type the name of a Filiere and select it (or use the dropdow)"));
		}

		return filiereFacade.findFiliere(nFiliere);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Filiere filiere = (Filiere) arg2;
		return String.valueOf(filiere.getIdFiliere());
	}
}