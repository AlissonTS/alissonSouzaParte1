package br.csi.model;

import java.util.ArrayList;

public class Produto {
		
		private long codigo;
		private String descricao;
		private float preco;
		private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		public long getCodigo() {
			return codigo;
		}
		public void setCodigo(long codigo) {
			this.codigo = codigo;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public float getPreco() {
			return preco;
		}
		public void setPreco(float preco) {
			this.preco = preco;
		}
		
		public ArrayList<Fornecedor> getFornecedores() {
			return fornecedores;
		}
		public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
			this.fornecedores = fornecedores;
		}
}
