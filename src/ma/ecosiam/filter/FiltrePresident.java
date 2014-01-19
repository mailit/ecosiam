package ma.ecosiam.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import ma.ecosiam.entity.Utilisateur;


public class FiltrePresident extends AbstractFilter implements Filter {

	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Utilisateur utilisateur = (Utilisateur) req.getSession(true)
				.getAttribute("utilisateur");
		System.out.println(utilisateur.getRole().getRole());
		if (utilisateur.getRole().getRole().compareTo("President")!=0) {
			accesRefuse(request, response, req);
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}