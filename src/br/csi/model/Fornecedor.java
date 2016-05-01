package br.csi.model;

import java.util.ArrayList;

public class Fornecedor {
		
	private long codigo;
	private String razaoS;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getRazaoS() {
		return razaoS;
	}
	public void setRazaoS(String razaoS) {
		this.razaoS = razaoS;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
}
