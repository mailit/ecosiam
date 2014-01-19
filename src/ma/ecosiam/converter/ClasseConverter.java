package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Classe;
import ma.ecosiam.facade.ClasseFacade;

@ManagedBean(name="classeConverter")
@FacesConverter(forClass = ma.ecosiam.entity.Classe.class)
public class ClasseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ClasseFacade classeFacade = new ClasseFacade();
		int nClasse;

		try {
			nClasse = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Classe and select it (or use the dropdow)", "Type the name of a Classe and select it (or use the dropdow)"));
		}

		return classeFacade.findClasse(nClasse);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Classe classe = (Classe) arg2;
		return String.valueOf(classe.getIdClasse());
	}
}