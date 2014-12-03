package br.com.jm.dbunit.excecao;

import br.com.jm.dbunit.modelo.Constantes;

public class CargaExcessivaExcpetion extends Exception {
	private static final long serialVersionUID = 7087867143068654869L;

	public CargaExcessivaExcpetion(float valor) {
		super("O valor da carga(" + valor + ") não pode exceder" +
				Constantes.VALOR_MAX_CARGA);
	}
}
