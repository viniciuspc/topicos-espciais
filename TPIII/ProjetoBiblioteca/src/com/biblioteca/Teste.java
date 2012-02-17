package com.biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Teste {

	public static void main(String[] args) {
		//OBTER CONEXAO
		EntityManager em = Persistence.createEntityManagerFactory("bibliotecaPU").createEntityManager();
		Livro livro = new Livro();
		livro.setAutor("Fulano");
		livro.setTitulo("JPA");
		
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		
		//FECHAR CONEXAO
		em.close();

	}

}
