package br.com.leandro.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	public List<String> descricoesQueContem(String descricao){
		List<String> ret = new ArrayList<>();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
		Root<Lancamento> root = criteria.from(Lancamento.class);
		criteria.select(root.get("descricao"));
		criteria.where(builder.like(builder.lower(root.get("descricao")), descricao.toLowerCase()));
		List<Tuple> tup = em.createQuery(criteria).getResultList();
		for (Tuple tuple : tup) {
			ret.add((String) tuple.get(0));
		}
		return ret;
	}
}
