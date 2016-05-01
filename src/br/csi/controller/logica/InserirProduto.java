package br.csi.controller.logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.Fornecedor;
import br.csi.model.Produto;
import br.csi.model.dao.ProdutoDao;

public class InserirProduto implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		
			rq.setAttribute("fornecedores", new ProdutoDao().getFornecedores());
		
			System.out.println("\n--------------------------------------------------------------------------------------");
			System.out.println(".......... dentro de inserir InserirProduto");
			String descricao = rq.getParameter("descricao");
			String preco = rq.getParameter("preco");
			String codigo = rq.getParameter("codigo");
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("id parameter ..."+codigo);
			String[] fornecedores = rq.getParameterValues("fornecedores");
			
			ProdutoDao pD = new ProdutoDao();
			
			String pagina = "/WEB-INF/jsp/cadastraProduto.jsp";
			
			if(fornecedores==null){
				rq.setAttribute("produtos", pD.getProdutos());
				rq.setAttribute("msg", "Problemas ao Inserir");
				return pagina;
			}
			
			Produto u = new Produto();
			ArrayList<Fornecedor> f = new ArrayList<Fornecedor>();
			
			System.out.println("--------------------------------------------------------------------------------------");
			for(String forn : fornecedores){
				Fornecedor fornece = new Fornecedor();
				fornece.setCodigo(Long.parseLong(forn));
				f.add(fornece);
				System.out.println("Fornecedor:"+forn);
			}
			
			u.setFornecedores(f);
			
			System.out.println("--------------------------------------------------------------------------------------");
			for(int i=0; i<f.size(); i++){
				Fornecedor fornecedor = f.get(i);
				System.out.println("Fornecedor do Array: "+fornecedor.getCodigo());  
			}
			System.out.println("--------------------------------------------------------------------------------------");
			
			
			float preco1;
			
			preco1 = Float.parseFloat(preco);
			
			u.setDescricao(descricao);
			u.setPreco(preco1);
			
			
			if(codigo == null){
				System.out.println("Novo produto a ser criado");
				System.out.println("--------------------------------------------------------------------------------------");
			}else{
				u.setCodigo(Long.parseLong(codigo));
			}
			
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
