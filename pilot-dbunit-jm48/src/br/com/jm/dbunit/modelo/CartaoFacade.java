package br.com.jm.dbunit.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jm.dbunit.excecao.CargaExcessivaExcpetion;
import br.com.jm.dbunit.excecao.SaldoInsuficienteExcpetion;
import br.com.jm.dbunit.excecao.TransacaoExcessivaExcpetion;

public class CartaoFacade {

	private EntityManager manager;
	
	public CartaoFacade(EntityManager manager) {
		this.manager = manager;
	}
	
	public void carregar(String numero, float valor) throws CargaExcessivaExcpetion {
		if (valor > Constantes.VALOR_MAX_CARGA) {
			throw new CargaExcessivaExcpetion(valor);
		}
		
		Cartao cartao = manager.find(Cartao.class, numero);
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		try {
			cartao.carregar(valor);
			
			manager.persist(cartao);
			
			transacao.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			transacao.rollback();
		}
	} // fim do metodo carregar
	
	public void comprar(String numero, float valor)
			throws SaldoInsuficienteExcpetion, TransacaoExcessivaExcpetion {
		Cartao cartao = manager.find(Cartao.class, numero);
		
		if (valor > cartao.getSaldo()) {
			throw new SaldoInsuficienteExcpetion(valor);
		}
		
		if (valor > Constantes.VALOR_MAX_TRANSACAO) {
			throw new TransacaoExcessivaExcpetion(valor);
		}
		
		EntityTransaction transacao = manager.getTransaction();
		
		transacao.begin();
		
		try {
			cartao.comprar(valor);
			
			manager.persist(cartao);
			
			transacao.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			transacao.rollback();
		}
	} // fim do método comprar
	
} // fim da classe CartaoFacade
