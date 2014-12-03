package br.com.jm.dbunit.modelo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AuditoriaFacade {

	private EntityManager manager;
	
	public AuditoriaFacade(EntityManager manager) {
		this.manager = manager;
	}
	
	public void auditar(String numero, float valor, String descricao) {
		Cartao cartao = manager.find(Cartao.class, numero);
		
		Auditoria auditoria = new Auditoria();
		
		auditoria.setCartao(cartao);
		auditoria.setValor(valor);
		auditoria.setDescricao(descricao);
		auditoria.setData(new Date());
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		try {
			manager.persist(auditoria);
			transacao.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			transacao.rollback();
		}
	} // fim do metodo auditar
	
} // fim da classe AuditoriaFacade
