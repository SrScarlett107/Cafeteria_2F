package com.Cafeteria.INF2FM.myproject2f.model;

public class Cardapio {

	private Long id;
	private Long codCardapio;
	private String descricao;
	private double valor;
	
	public void setId(Long id) {
		this.id = id;
	}
	public long getId() {
		return id;
		
	}
	public void setCodCardapio(Long codCardapio) {
		this.codCardapio = codCardapio;
	}
	public long getCodCardapio() {
		return codCardapio;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValor() {
		return valor;
		
	}
}
