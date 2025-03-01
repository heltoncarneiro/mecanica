package br.com.helton.comand;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewServico implements Command{
	private static String pagina = "/WEB-INF/servico/cadastrarServico.jsp";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return pagina;
	};
}
