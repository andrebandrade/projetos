package br.com.jm.dbunit.teste.db;
/* http://www.devmedia.com.br/artigo-java-magazine-48-iniciando-com-o-dbunit/8535 */

import br.com.jm.dbunit.modelo.Facade;

public class TesteFacadeDB extends TesteDB {

	private Facade facade;

	public TesteFacadeDB(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		facade = new Facade();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		facade.finalizar();
	}

	public void testCargaValida() {
		facade.carregar("1111111111111111", 120);
		verificarBanco("registrosCargaValida.xml", "cartao", "auditoria");
	}

	public void testCargaExcessiva() {
		facade.carregar("1111111111111111", 550);
		verificarBanco("registrosCargaExcessiva.xml", "cartao", "auditoria");
	}

	public void testCompraValida() {
		facade.comprar("1111111111111111", 90);
		verificarBanco("registrosCompraValida.xml", "cartao", "auditoria");
	}

	public void testCompraSaldoInsuficiente() {
		facade.comprar("1111111111111111", 140);
		verificarBanco("registrosCompraSaldoInsuficiente.xml", "cartao", "auditoria");
	}

	public void testCompraTransacaoExcessiva() {
		facade.carregar("1111111111111111", 200);
		facade.comprar("1111111111111111", 160);
		verificarBanco("registrosCompraTransacaoExcessiva.xml", "cartao", "auditoria");			
	}	

}
