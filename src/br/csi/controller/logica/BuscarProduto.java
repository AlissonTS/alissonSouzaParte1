package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.dao.ProdutoDao;

public class BuscarProduto implements Logica {

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		
		System.out.println("--------------------------------------------------------------------------------------");
		String codigo = rq.getParameter("codigo");
		System.out.println("Código do produto: "+codigo);
		
		rq.setAttribute("produtos", new ProdutoDao().getProdutos());
		rq.setAttribute("produto", new ProdutoDao().getProduto(Long.parseLong(codigo)));
		rq.setAttribute("fornecedores", new ProdutoDao().getFornecedores());
		
		return "/WEB-INF/jsp/cadastraProduto.jsp";
	}

}
