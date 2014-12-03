package br.com.jm.dbunit.excecao;

import br.com.jm.dbunit.modelo.Constantes;

public class TransacaoExcessivaExcpetion extends Exception {
	private static final long serialVersionUID = -7421857662170919860L;

	public TransacaoExcessivaExcpetion(float valor) {
		super("O valor da transa��o (" + valor + ") n�o pode exceder " +
				Constantes.VALOR_MAX_TRANSACAO);
	}
	
}
