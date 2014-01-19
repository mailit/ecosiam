package ma.ecosiam.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import ma.ecosiam.entity.Utilisateur;


public class FiltreAuthentification extends AbstractFilter implements Filter {
	private static List<String> allowedURIs;

	public void init(FilterConfig fConfig) throws ServletException {
		if (allowedURIs == null) {
			allowedURIs = new ArrayList<String>();
			allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
			allowedURIs
					.add("/CECOSIAM/javax.faces.resource/main.css.xhtml");
			allowedURIs
					.add("/CECOSIAM/javax.faces.resource/theme.css.xhtml");
			allowedURIs
					.add("/CECOSIAM/javax.faces.resource/primefaces.js.xhtml");
			allowedURIs
					.add("/CECOSIAM/javax.faces.resource/primefaces.css.xhtml");
			
			allowedURIs
					.add("/CECOSIAM/javax.faces.resource/css/animate.css.xhtml");
			allowedURIs
					.add("/CECOSIAM/javax.faces.resource/css/styles.css.xhtml");

		}
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.isNew()) {
			doLogin(request, response, req);
			return;
		}

		Utilisateur utilisateur = (Utilisateur) session
				.getAttribute("utilisateur");

		if (utilisateur == null && !allowedURIs.contains(req.getRequestURI())) {
			System.out.println(req.getRequestURI());
			doLogin(request, response, req);
			return;
		}

		chain.doFilter(request, response);
	}
}