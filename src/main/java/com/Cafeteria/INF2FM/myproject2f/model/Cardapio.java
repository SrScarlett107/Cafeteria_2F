package com.Cafeteria.INF2FM.myproject2f.model;

import java.time.LocalDate;


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
    private String foto;
	private String descricao;
	private double valor;
	private LocalDate dataDoCadastro;
	private boolean codStatusCardapio;
	

	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
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
	public LocalDate getDataDoCadastro() {
		return dataDoCadastro;
	}
	public void setDataDoCadastro(LocalDate dataDoCadastro) {
		this.dataDoCadastro = dataDoCadastro;
	}
	public boolean getCodStatusCardapio() {
		return codStatusCardapio;
	}
	public void setCodStatusCardapio(boolean codStatusCardapio) {
		this.codStatusCardapio = codStatusCardapio;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
