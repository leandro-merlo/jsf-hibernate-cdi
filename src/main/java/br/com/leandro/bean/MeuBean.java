package br.com.leandro.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@RequestScoped
public class MeuBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 846784341004920949L;
	private String nome;
	private int quantidadeCaracteres = 0;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void transformar(){
		this.nome = this.nome.toUpperCase();
		this.quantidadeCaracteres = this.nome.length();
	}
	
	public int getQuantidadeCaracteres() {
		return quantidadeCaracteres;
	}
	
}
