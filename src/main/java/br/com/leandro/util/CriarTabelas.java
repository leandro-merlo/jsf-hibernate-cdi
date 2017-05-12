package br.com.leandro.util;

import javax.persistence.Persistence;

public class CriarTabelas {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("financeiroPU");
		System.exit(0);
	}
}
