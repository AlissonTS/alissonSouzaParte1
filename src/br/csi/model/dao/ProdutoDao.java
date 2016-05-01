package br.csi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

import br.csi.model.Fornecedor;
import br.csi.model.Produto;
import br.csi.model.util.ConectarPostGresFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
	create table produto ( 
		codigo serial not null, 
		descricao varchar(40) not null, 
		preco float not null, 
		primary key (codigo));

	create table fornecedor ( 
		codigo serial not null, 
		razaosocial varchar(40) not null, 
		primary key (codigo));

	create table fornecprod (
		codigoprod integer, 
		codigofornec integer, 
		primary key (codigoprod, codigofornec), 
		FOREIGN KEY(codigoprod) REFERENCES produto(codigo), 
		FOREIGN KEY(codigofornec) REFERENCES fornecedor(codigo));
		
		insert into produto (descricao, preco) values ('vinho tinto cabernet sauvignon', 21.75);
		insert into produto (descricao, preco) values ('vinho tinto carmenere', 13.40);
		insert into produto (descricao, preco) values ('carvão 5kg', 8.50);
		insert into produto (descricao, preco) values ('carvão 3kg', 5.70);
		
		insert into fornecedor (razaosocial) values ('FBI bebidas');
		insert into fornecedor (razaosocial) values ('Carva Fornos LTDA');
		
		insert into fornecprod (codigoprod, codigofornec) values (1,1);
		insert into fornecprod (codigoprod, codigofornec) values (2,1);
		insert into fornecprod (codigoprod, codigofornec) values (3,2);
		insert into fornecprod (codigoprod, codigofornec) values (4,2);

*/
public class ProdutoDao {
			
			/* ------------------------------------------------------------------------------------------------- */
			public Produto getProduto(Long codigo){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do getProduto()");
				
				Produto u = new Produto();
				
				try{
					
					PreparedStatement stmt =  
							ConectarPostGresFactory
								.getConexao()
									.prepareStatement("select * from produto where codigo = ?");
					stmt.setLong(1, codigo);
					ResultSet rs = stmt.executeQuery();
					System.out.println("--------------------------------------------------------------------------------------");
					while(rs.next()){				
						u.setCodigo(rs.getLong("codigo"));
						u.setDescricao(rs.getString("descricao"));
						u.setPreco(rs.getFloat("preco"));
						System.out.println("produto: "+u.getCodigo());
					}
					
					u = getFornecedores(u);
					
					for(int i=0; i<u.getFornecedores().size(); i++){
						Fornecedor fornecedor = u.getFornecedores().get(i);
						System.out.println("Fornecedor do Array SQL: "+fornecedor.getCodigo());  
					}
					System.out.println("--------------------------------------------------------------------------------------");
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return u;
			}
			

			public Produto getFornecedores(Produto p){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do getFornecedores(p) para pegar a Lista de Fornecedores do Produto");
				
				ArrayList<Fornecedor> k = new ArrayList<Fornecedor>();
				
				try{
					PreparedStatement stmt = ConectarPostGresFactory
							.getConexao()
							.prepareStatement("select * from fornecprod where codigoprod = ?");
					stmt.setLong(1, p.getCodigo());
					
					ResultSet f = stmt.executeQuery();
					
					while(f.next()){
						Fornecedor fornece = new Fornecedor();
						fornece.setCodigo(f.getInt("codigofornec"));
						k.add(fornece);
					}
					
					p.setFornecedores(k);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return p;
			}
			
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public boolean remover(Long codigo){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do remover()");
				
				boolean retorno = false;
				
				String sql;
				PreparedStatement stmt = null;
				Connection c = ConectarPostGresFactory.getConexao();		
				
				try {
					sql = "delete from fornecprod where codigoprod=?";
					stmt = c.prepareStatement(sql);
					stmt.setLong(1, codigo);
					stmt.execute();
					
					sql = "delete from Produto where codigo = ?";
					stmt = c.prepareStatement(sql);
					stmt.setLong(1, codigo);
					stmt.execute();
					
					retorno = true;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				
				return retorno;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public List<Produto> getProdutos(){
				ArrayList<Produto> produtos = new ArrayList<Produto>();
				
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do getProdutos()");
				
				try{
						
					PreparedStatement stmt =  
							ConectarPostGresFactory.getConexao().prepareStatement("select * from Produto");
					
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){
						Produto t = new Produto();
						t.setCodigo(rs.getLong("codigo"));
						t.setDescricao(rs.getString("descricao"));
						t.setPreco(rs.getFloat("preco"));
						System.out.println("Código do Produto: "+t.getCodigo());
						produtos.add(t);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return produtos;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public boolean adiciona(Produto t){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do adiciona()");
				
				Connection c = null;
				PreparedStatement stmt = null;
				boolean retorno = false;
				try {

					c = ConectarPostGresFactory.getConexao();
					String sql = "";
					
					if(t.getCodigo()<= 0){
						System.out.println("......... vai adicionar .............");
						
						sql = "INSERT INTO PRODUTO (DESCRICAO, PRECO) "
								+ " values (?,?)";
						stmt = c.prepareStatement(sql);	
						stmt.setString(1, t.getDescricao());
						stmt.setFloat(2, t.getPreco());
						
						stmt.execute();	
						
						long ret = pesquisaProduto();
						
						System.out.println("--------------------------------------------------------------------------------------");
						for(int i=0; i<t.getFornecedores().size(); i++){
							Fornecedor fornecedor = t.getFornecedores().get(i);
							
							sql = "insert into fornecprod (codigoprod, codigofornec) values (?,?)";
							stmt = c.prepareStatement(sql);	
							stmt.setLong(1, ret);
							stmt.setLong(2, fornecedor.getCodigo());
							
							System.out.println("Código do produto: "+ret+", Código do Fornecedor: "+fornecedor.getCodigo());
							
							stmt.execute();	
						}
						System.out.println("--------------------------------------------------------------------------------------");
						
					}else{
						System.out.println("......... vai alterar .............");
						sql = "UPDATE PRODUTO SET DESCRICAO =?, PRECO=?  "
								+ " WHERE codigo =?";
						stmt = c.prepareStatement(sql);								
						stmt.setString(1, t.getDescricao());
						stmt.setFloat(2, t.getPreco());
						stmt.setLong(3, t.getCodigo());
						
						stmt.execute();
						
						sql = "delete from fornecprod where codigoprod=?";
						stmt = c.prepareStatement(sql);
						stmt.setLong(1, t.getCodigo());
						
						stmt.execute();
						
						System.out.println("--------------------------------------------------------------------------------------");
						for(int i=0; i<t.getFornecedores().size(); i++){
							Fornecedor fornecedor = t.getFornecedores().get(i);
							
							sql = "insert into fornecprod (codigoprod, codigofornec) values (?,?)";
							stmt = c.prepareStatement(sql);	
							stmt.setLong(1, t.getCodigo());
							stmt.setLong(2, fornecedor.getCodigo());
							
							System.out.println("Código do produto: "+t.getCodigo()+", Código do Fornecedor: "+fornecedor.getCodigo());
							
							stmt.execute();	
						}
						System.out.println("--------------------------------------------------------------------------------------");
						
					}
					
					stmt.close();
					retorno = true;
					
				} catch (Exception e) {
					e.printStackTrace();
					return retorno;
					
				}	
				return retorno;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public List<Fornecedor> getFornecedores(){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do getFornecedores()");
				
				ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
				try{
						
					PreparedStatement stmt =  
							ConectarPostGresFactory.getConexao().prepareStatement("select * from Fornecedor");
					
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){
						Fornecedor t = new Fornecedor();
						t.setCodigo(rs.getLong("codigo"));
						t.setRazaoS(rs.getString("razaosocial"));
						System.out.println("Código do Fornecedor: "+t.getCodigo());
						fornecedores.add(t);
					}
					System.out.println("--------------------------------------------------------------------------------------");
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return fornecedores;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public long pesquisaProduto() throws ClassNotFoundException, SQLException{
				Connection c = ConectarPostGresFactory.getConexao();
				
				String sql = "select MAX(codigo) as id from produto";
				PreparedStatement stmtPre = c.prepareStatement(sql);
				ResultSet rs= stmtPre.executeQuery();
				
				rs.next();
				long idProd = rs.getInt("id");
				
				return idProd;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
}
