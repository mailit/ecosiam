package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Ville;
import ma.ecosiam.facade.VilleFacade;

@ManagedBean(name="converterville")
@FacesConverter(forClass = ma.ecosiam.entity.Ville.class)
public class VilleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		VilleFacade villeFacade = new VilleFacade();
		int nVille;

		try {
			nVille = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Ville and select it (or use the dropdow)", "Type the name of a Ville and select it (or use the dropdow)"));
		}

		return villeFacade.findVille(nVille);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Ville ville = (Ville) arg2;
		return String.valueOf(ville.getIdVille());
	}
}