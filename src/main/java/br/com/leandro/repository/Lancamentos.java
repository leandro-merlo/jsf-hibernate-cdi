package br.com.leandro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.leandro.model.Lancamento;

public class Lancamentos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833140213755228075L;
	private EntityManager em;

	@Inject
	public Lancamentos(EntityManager em) {
		this.em = em;
	}
	
	public List<Lancamento> todos(){
		TypedQuery<Lancamento> query = em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}
	
	public void adicionar(Lancamento lancamento){
		this.em.persist(lancamento);
	}

}
