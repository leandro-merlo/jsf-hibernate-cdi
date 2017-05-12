package br.com.leandro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.leandro.model.Lancamento;

public class Lancamentos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833140213755228075L;
	private EntityManager em;

	public Lancamentos(EntityManager em) {
		this.em = em;
	}
	
	public List<Lancamento> todos(){
		TypedQuery<Lancamento> query = em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}

}
