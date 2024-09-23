package com.Cafeteria.INF2FM.myproject2f.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String id_formaPagamento;
	private double valor;
	
	@ElementCollection
    private List<Pagamento> produtos;

	public List<Pagamento> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Pagamento> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getId_formaPagamento() {
		return id_formaPagamento;
	}

	public void setId_formaPagamento(String id_formaPagamento) {
		this.id_formaPagamento = id_formaPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	

}
