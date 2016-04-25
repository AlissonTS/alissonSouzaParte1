package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.dao.ProdutoDao;

public class RemoverProduto implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		String codigo = rq.getParameter("codigo");
		
		new ProdutoDao().remover(Long.parseLong(codigo));
		
		rq.setAttribute("produtos", new ProdutoDao().getProdutos());
		
		return "/WEB-INF/jsp/cadastraProduto.jsp";
	}

}
