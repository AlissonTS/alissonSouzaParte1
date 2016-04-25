package br.csi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

import br.csi.model.Produto;
import br.csi.model.util.ConectarPostGresFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
		
			public Produto getProduto(Long codigo){
				Produto u = new Produto();
				
				try{
					
					PreparedStatement stmt =  
							ConectarPostGresFactory
								.getConexao()
									.prepareStatement("select * from produto where codigo = ?");
					stmt.setLong(1, codigo);
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){				
						u.setCodigo(rs.getLong("codigo"));
						u.setDescricao(rs.getString("descricao"));
						u.setPreco(rs.getFloat("preco"));
						System.out.println("produto: "+u.getCodigo());
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				return u;
			}
			
			public boolean remover(Long codigo){
				boolean retorno = false;
				
				String sql = "delete from Produto where codigo = ?";
				Connection c = ConectarPostGresFactory
						.getConexao();
				PreparedStatement stmt = null;
				
				try {
					stmt = c.prepareStatement(sql);
					stmt.setLong(1, codigo);
					stmt.execute();
					retorno = true;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				
				return retorno;
			}
			
			public List<Produto> getProdutos(){
				ArrayList<Produto> produtos = new ArrayList<Produto>();
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
						System.out.println("Codigo: "+t.getCodigo());
						produtos.add(t);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return produtos;
			}
			
			public boolean adiciona(Produto t){
				
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
						
						
					}else{
						System.out.println("......... vai alterar .............");
						sql = "UPDATE PRODUTO SET DESCRICAO =?, PRECO=?  "
								+ " WHERE id =?";
						stmt = c.prepareStatement(sql);								
						stmt.setString(1, t.getDescricao());
						stmt.setFloat(2, t.getPreco());
						stmt.setLong(3, t.getCodigo());
						
					}
					
							
										
					stmt.execute();			
					stmt.close();
					retorno = true;
					
				} catch (Exception e) {
					e.printStackTrace();
					return retorno;
					
				}	
				return retorno;
			}
}
