package br.com.leandro.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leandro.bean.Usuario;

@WebFilter(urlPatterns = {"*.xhtml"})
public class AutorizacaoFilter implements Filter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Usuario usuario;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse rsp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (!usuario.isLogado() &&
				 !req.getRequestURI().endsWith("/Login.xhtml") &&
				 !req.getRequestURI().contains("/javax.faces.resource/")) {
			rsp.sendRedirect(req.getContextPath() + "/Login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
