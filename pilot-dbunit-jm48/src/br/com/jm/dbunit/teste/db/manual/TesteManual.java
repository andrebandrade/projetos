package br.com.jm.dbunit.teste.db.manual;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.jm.dbunit.modelo.Auditoria;
import br.com.jm.dbunit.modelo.Cartao;

public class TesteManual {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("cartao");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction transacao = manager.getTransaction();
		try {
			transacao.begin();
			
			Cartao cartao = new Cartao();
			cartao.setNome("Andre");
			cartao.setNumero("123456789");
			cartao.setSaldo(200);
			
			manager.persist(cartao);
			
			Auditoria auditoria = new Auditoria();
			auditoria.setCartao(cartao);
			auditoria.setData(new Date());
			auditoria.setDescricao("Gravando cartao");
			auditoria.setValor(cartao.getSaldo());
			
			manager.persist(auditoria);
			
			transacao.commit();
		} catch (Exception e) {
			transacao.rollback();
			e.printStackTrace();
		}
	}
}
