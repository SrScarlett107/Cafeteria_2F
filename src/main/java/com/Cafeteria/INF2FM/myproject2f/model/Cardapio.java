package com.Cafeteria.INF2FM.myproject2f.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cardapios")
public class Cardapio {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
	private String descricao;
	private double valor;
	private LocalDateTime dataDoCadastro;

	
	public void setId(Long id) {
		this.id = id;
	}
	public long getId() {
		return id;
		
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDateTime getDataDoCadastro() {
		return dataDoCadastro;
	}
	public void setDataDoCadastro(LocalDateTime dataDoCadastro) {
		this.dataDoCadastro = dataDoCadastro;
	}
}
