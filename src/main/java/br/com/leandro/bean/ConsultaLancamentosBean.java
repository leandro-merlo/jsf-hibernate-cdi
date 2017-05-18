package br.com.leandro.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;

import org.omnifaces.cdi.ViewScoped;

import br.com.leandro.model.Lancamento;
import br.com.leandro.repository.Lancamentos;
import br.com.leandro.util.JPAUtil;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;
	
	private Lancamentos lancamentoRepository;
	private EntityManager em = JPAUtil.getEntityManager();
	
	public ConsultaLancamentosBean() {
		lancamentoRepository = new Lancamentos(em);
	}

	public void consultar(){
		this.lancamentos = lancamentoRepository.todos();
		em.close();
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void adicionar(Lancamento lancamento){
		this.em.persist(lancamento);
	}
}
