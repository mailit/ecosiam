package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Etablissement;
import ma.ecosiam.facade.EtablissementFacade;

@ManagedBean(name="etablissementConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Etablissement.class)
public class EtablissementConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		EtablissementFacade etablissementFacade = new EtablissementFacade();
		int nEtablissement;

		try {
			nEtablissement = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Etablissement and select it (or use the dropdow)", "Type the name of a Etablissement and select it (or use the dropdow)"));
		}

		return etablissementFacade.findEtablissement(nEtablissement);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Etablissement etablissement = (Etablissement) arg2;
		return String.valueOf(etablissement.getIdEtablissement());
	}
}