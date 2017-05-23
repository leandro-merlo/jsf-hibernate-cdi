package br.com.leandro.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.leandro.model.Lancamento;
import br.com.leandro.repository.Lancamentos;

public class CadastroLancamentos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3074646038905083411L;
	
	@Inject
	private Lancamentos lancamentos;

	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())){
			throw new NegocioException("A data de pagamento não pod e ser uma data futura.");
		}
		this.lancamentos.guardar(lancamento);
	}
	
	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());
		if (lancamento.getDataPagamento() != null){
			throw new NegocioException("Não é possível excluir um lançamento pago!");
		}
		this.lancamentos.remover(lancamento);
	}
	
	

}
