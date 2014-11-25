package com.algworks.blog.dbunit.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algworks.blog.dbunit.dao.helper.DbUnitHelper;
import com.algworks.blog.dbunit.model.Carro;

public class CarroDAOTest {

	private static DbUnitHelper dbUnitHelper;
	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	private CarroDAO carroDAO;
	
	@BeforeClass
	public static void initClass() {
		dbUnitHelper = new DbUnitHelper("DbUnitXml");
		factory = Persistence.createEntityManagerFactory("artigoTesteIntegracaoDbunitPU");
	}
	
	@Before
	public void init() {
		dbUnitHelper.execute(DatabaseOperation.DELETE_ALL, "Carro.xml");
		
		dbUnitHelper.execute(DatabaseOperation.INSERT, "Carro.xml");
		
		manager = factory.createEntityManager();
		this.carroDAO = new CarroDAO(manager);
	}
	
	@After
	public void end() {
		this.manager.close();
	}
	
	@Test
	public void deveRetornarCarroZeroKm() {
		List<Carro> resultado = carroDAO.buscarCarrosZero();
		
		assertThat(resultado, hasItems(new Carro(1l), new Carro(4l)));
	}
	
	@Test
	public void deveRetornarCarrosMenosDoisAnosUso() {
		Integer doisAnosAntes = Calendar.getInstance().get(Calendar.YEAR) - 2;
		List<Carro> resultado = carroDAO.buscarCarrosComIdadeInferiorA(doisAnosAntes);
		
		assertThat(resultado, hasItems(new Carro(1l), new Carro(2l), new Carro(3l), new Carro(4l)));
	}
	
} // fim da classe CarroDAOTest
