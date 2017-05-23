package br.com.leandro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.omnifaces.cdi.ViewScoped;

import br.com.leandro.model.Lancamento;
import br.com.leandro.repository.Lancamentos;
import br.com.leandro.service.CadastroLancamentos;
import br.com.leandro.service.NegocioException;
import br.com.leandro.util.JPAUtil;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	private Lancamentos lancamentoRepository;
	private EntityManager em = JPAUtil.getEntityManager();
	
	private Lancamento lancamentoSelecionado;

	public ConsultaLancamentosBean() {
		lancamentoRepository = new Lancamentos(em);
	}

	
	public void consultar(){
		this.lancamentos = lancamentoRepository.todos();
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void adicionar(Lancamento lancamento){
		this.em.persist(lancamento);
	}
	

	@Transactional
	public void excluir(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			ctx.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(null, msg);
		}		
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}	
	
	
}
