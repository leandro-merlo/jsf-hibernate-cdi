package br.com.leandro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.com.leandro.model.Lancamento;
import br.com.leandro.model.Pessoa;
import br.com.leandro.model.TipoLancamento;
import br.com.leandro.repository.Pessoas;
import br.com.leandro.service.CadastroLancamentos;
import br.com.leandro.service.NegocioException;

@Named
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1451339560895814199L;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Pessoas pessoas;
	
	public void preparaCadastro() {
		this.todasPessoas = pessoas.todas();
	}

	public void salvar() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			ctx.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			ctx.addMessage(null, msg);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}

	public TipoLancamento[] getTiposLancamento() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo: " + event.getOldValue());
		System.out.println("Novo valor: " + event.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
	}

}
