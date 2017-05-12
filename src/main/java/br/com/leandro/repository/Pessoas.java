package br.com.leandro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.leandro.model.Pessoa;

public class Pessoas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6053240605555286043L;

	private EntityManager entityManager;

	public Pessoas(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Pessoa porId(Long id){
		return this.entityManager.find(Pessoa.class, id);
	}

	public List<Pessoa> todas(){
		TypedQuery<Pessoa> q = entityManager.createQuery("from Pessoa", Pessoa.class);
		return q.getResultList();
	}
	
	
}
