package br.com.jm.dbunit.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.jm.dbunit.excecao.CargaExcessivaExcpetion;
import br.com.jm.dbunit.excecao.SaldoInsuficienteExcpetion;
import br.com.jm.dbunit.excecao.TransacaoExcessivaExcpetion;

public class Facade {

	private EntityManagerFactory factory;
	private EntityManager manager;
	private CartaoFacade cartaoFacade;
	private AuditoriaFacade auditoriaFacade;
	
	public Facade() {
		factory = Persistence.createEntityManagerFactory("cartao");
		manager = factory.createEntityManager();
		cartaoFacade = new CartaoFacade(manager);
		auditoriaFacade = new AuditoriaFacade(manager);
	} // fim do construtor
	
	public void finalizar() {
		manager.close();
		factory.close();
	} // fim do metodo finalizar
	
	public void carregar(String numero, float valor) {
		try {
			cartaoFacade.carregar(numero, valor);
			
			if (valor > Constantes.VALOR_MIN_AUDIT) {
				auditoriaFacade.auditar(numero, valor, Constantes.AUDIT_CARGA);
			}
		} catch (CargaExcessivaExcpetion e) {
			auditoriaFacade.auditar(numero, valor, Constantes.AUDIT_CARGA_EXCESSIVA);
		}
	} // fim do metodo carregar
	
	public void comprar(String numero, float valor) {
		try {
			cartaoFacade.comprar(numero, valor);
			if (valor > Constantes.VALOR_MIN_AUDIT) {
				auditoriaFacade.auditar(numero, valor, Constantes.AUDIT_COMPRA);
			}
		} catch (SaldoInsuficienteExcpetion e) {
			auditoriaFacade.auditar(numero, valor, Constantes.AUDIT_SALDO_INSUFICIENTE);
		} catch (TransacaoExcessivaExcpetion e) {
			auditoriaFacade.auditar(numero, valor, Constantes.AUDIT_TRANSACAO_EXCESSIVA);
		}
	}
	
} // fim da classe
