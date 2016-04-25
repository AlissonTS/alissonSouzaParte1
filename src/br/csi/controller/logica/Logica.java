package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {
	
	public String executa(HttpServletRequest rq, 
			HttpServletResponse rp);
}
