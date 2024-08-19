package com.Cafeteria.INF2FM.myproject2f.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double valor;
	private Long cardapioId;
    private Integer quantidade;
	private Long id_pagamento;
	private Long id_cardapio;
	private String nomePedido;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Long getId_pagamento() {
		return id_pagamento;
	}
	public void setId_pagamento(Long id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	public Long getId_cardapio() {
		return id_cardapio;
	}
	public void setId_cardapio(Long id_cardapio) {
		this.id_cardapio = id_cardapio;
	}
	public String getNomePedido() {
		return nomePedido;
	}
	public void setNomePedido(String nomePedido) {
		this.nomePedido = nomePedido;
	}
	public Long getCardapioId() {
		return cardapioId;
	}
	public void setCardapioId(Long cardapioId) {
		this.cardapioId = cardapioId;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	

}
