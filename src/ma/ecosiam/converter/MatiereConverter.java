package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Matiere;
import ma.ecosiam.facade.MatiereFacade;

@ManagedBean(name="matiereConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Matiere.class)
public class MatiereConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		MatiereFacade matiereFacade = new MatiereFacade();
		int nMatiere;

		try {
			nMatiere = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Matiere and select it (or use the dropdow)", "Type the name of a Matiere and select it (or use the dropdow)"));
		}

		return matiereFacade.findMatiere(nMatiere);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Matiere matiere = (Matiere) arg2;
		return String.valueOf(matiere.getIdMatiere());
	}
}