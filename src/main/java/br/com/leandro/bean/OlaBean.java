package br.com.leandro.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="ola")public class OlaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1801784691180578683L;
	private String nome;
	private String sobrenome;
	private String nomeCompleto;

	public void dizerOla() {
		this.nomeCompleto = this.nome.toUpperCase() + " " + this.sobrenome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

}
