package com.biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Teste {

	public static void main(String[] args) {
		//OBTER CONEXAO
		EntityManager em = Persistence.createEntityManagerFactory("bibliotecaPU").createEntityManager();
		
		Categoria categoria = new Categoria();
		categoria.setDescriacao("Inform�tica");
		
		Livro livro = new Livro();
		livro.setAutor("Outro");
		livro.setTitulo("Hibernate");
		livro.setCategoria(categoria);
		
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		
		//FECHAR CONEXAO
		em.close();

	}

}
