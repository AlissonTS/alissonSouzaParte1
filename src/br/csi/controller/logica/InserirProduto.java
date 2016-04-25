package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.Produto;
import br.csi.model.dao.ProdutoDao;

public class InserirProduto implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		
			System.out.println(".......... dentro de inserir InserirProduto");
			String descricao = rq.getParameter("descricao");
			String preco = rq.getParameter("preco");
			String codigo = rq.getParameter("codigo");
			System.out.println("id ..."+codigo);
			
			float preco1;
			
			preco1 = Float.parseFloat(preco);
			
			Produto u = new Produto();
			u.setDescricao(descricao);
			u.setPreco(preco1);
			
			if(codigo == null){
				System.out.println("novo produto");
			}else{
				u.setCodigo(Long.parseLong(codigo));
			}
			ProdutoDao pD = new ProdutoDao();
			
			String pagina = "/WEB-INF/jsp/cadastraProduto.jsp";
			
			try {
				
				boolean retorno = pD.adiciona(u);
				if(retorno){
					rq.setAttribute("produtos", pD.getProdutos());
				}else{
					rq.setAttribute("msg", "Problemas ao inserir Produto");
				}
				
			} catch (Exception e) {		
				e.printStackTrace();
				rq.setAttribute("msg", "Problemas ao inserir Produto");
				return pagina;
			}
	
	
				return pagina;//
	}

}
