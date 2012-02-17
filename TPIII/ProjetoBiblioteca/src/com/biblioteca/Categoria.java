package com.biblioteca;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codCategoria;
	private String descriacao;
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	public String getDescriacao() {
		return descriacao;
	}
	public void setDescriacao(String descriacao) {
		this.descriacao = descriacao;
	}
	
	

}
