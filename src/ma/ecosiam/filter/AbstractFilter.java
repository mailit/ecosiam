package ma.ecosiam.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class AbstractFilter {

	public AbstractFilter() {
		super();
	}

	protected void doLogin(ServletRequest request,
			ServletResponse response, HttpServletRequest req)
			throws ServletException, IOException {
		RequestDispatcher rd = req
				.getRequestDispatcher("/pages/publique/authentification.xhtml");
		rd.forward(request, response);
	}

	protected void accesRefuse(ServletRequest request,
			ServletResponse response, HttpServletRequest req)
			throws ServletException, IOException {
		RequestDispatcher rd = req
				.getRequestDispatcher("/pages/publique/accesRefuse.xhtml");
		rd.forward(request, response);
	}
}