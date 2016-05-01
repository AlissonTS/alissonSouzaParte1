package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.dao.ProdutoDao;

public class RedirecionaProduto implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		System.out.println("\n--------------------------------------------------------------------------------------");
		System.out.println("dentro do redirecinamento ....");
		
		 rq.setAttribute("produtos", new ProdutoDao().getProdutos());
		 rq.setAttribute("fornecedores", new ProdutoDao().getFornecedores());
		 
		return "/WEB-INF/jsp/cadastraProduto.jsp";
	}

}
