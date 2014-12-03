package br.com.jm.dbunit.teste.db;

import static org.dbunit.PropertiesBasedJdbcDatabaseTester.*;

import java.io.File;
import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

public abstract class TesteDB extends DBTestCase {

	public TesteDB(String name) {
		super(name);
		System.setProperty(DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
		System.setProperty(DBUNIT_CONNECTION_URL, 
				"jdbc:postgresql://localhost:5432/jm48");
		System.setProperty(DBUNIT_USERNAME, "postgres");
		System.setProperty(DBUNIT_PASSWORD, "postgres");
	}
	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(new FileInputStream("registros.xml"));
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
		//return DatabaseOperation.DELETE_ALL;
		return DatabaseOperation.NONE;
	}
	
	protected void verificarBanco(String arquivoDadosEsperados, String... tabelas) {
		try {
			for (String tabela : tabelas) {
				// dados do banco
				IDataSet dataSetBanco = getConnection().createDataSet();
				ITable dadosBanco = dataSetBanco.getTable(tabela);
				
				// carrega os dados esperados de um dataset XML
				IDataSet dataSetEsperado = new FlatXmlDataSet(new File(
						arquivoDadosEsperados));
				ITable dadosEsperados = dataSetEsperado.getTable(tabela);

				// filtra as colunas para corresponder ao XML
				ITable dadosFiltrados = DefaultColumnFilter
						.includedColumnsTable(dadosBanco, dadosEsperados
								.getTableMetaData().getColumns());
				
				// se a tabela estiver vazia compara apenas quantidade
				if (dadosEsperados.getRowCount() == 0) {
					assertEquals(dadosFiltrados.getRowCount(), 0);
				} else {
					// verifica se os dados esperados correspondem aos do banco
					Assertion.assertEquals(dadosEsperados, dadosFiltrados);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
