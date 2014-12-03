package br.com.jm.dbunit.excecao;

public class SaldoInsuficienteExcpetion extends Exception {
	private static final long serialVersionUID = -5636444972930469962L;

	public SaldoInsuficienteExcpetion(float valor) {
		super("O valor da transa��o (" + valor + ") n�o pode exceder o saldo");
	}
	
}
