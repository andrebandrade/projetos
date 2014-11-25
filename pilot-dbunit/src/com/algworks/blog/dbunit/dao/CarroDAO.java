package com.algworks.blog.dbunit.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import com.algworks.blog.dbunit.model.Carro;

public class CarroDAO {

	private EntityManager manager;
	
	public CarroDAO(EntityManager manager) {
		this.manager = manager;
	} // fim do construtor
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarrosZero() {
		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		
		return manager.createQuery("from Carro c where c.anoFabricacao = :ano")
				.setParameter("ano", anoAtual)
				.getResultList();
	} // fim do metodo buscarCarrosZero
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarrosComIdadeInferiorA(Integer ano) {
		return manager.createQuery("from Carro c where c.anoFabricacao >= :ano")
				.setParameter("ano", ano)
				.getResultList();
	} // fim do metodo buscarCarros ComIdadeInferiorA
	
} // fim da classe
