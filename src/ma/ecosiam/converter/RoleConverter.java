package ma.ecosiam.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ma.ecosiam.entity.Role;
import ma.ecosiam.facade.RoleFacade;

@ManagedBean(name="converterrole")
@FacesConverter(forClass = ma.ecosiam.entity.Role.class)
public class RoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		RoleFacade roleFacade = new RoleFacade();
		int nRole;

		try {
			nRole = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type the name of a Role and select it (or use the dropdow)", "Type the name of a Role and select it (or use the dropdow)"));
		}

		return roleFacade.findRole(nRole);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Role role = (Role) arg2;
		return String.valueOf(role.getIdRole());
	}
}