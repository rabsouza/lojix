package br.com.zetex.lojix.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.zetex.lojix.dao.BaseDao;
import br.com.zetex.lojix.dao.BaseDaoImpl;
import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.PackageLog;

@SuppressWarnings("javadoc")
public abstract class BaseTestDao {

	private static final Logger LOGGER = LoggerUtil.getLogger(BaseTestDao.class, PackageLog.TEST);

	private static EntityManagerFactory emFactory;

	private static EntityManager em;

	private static Connection connection;

	private static BaseDao baseDao;

	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUpClass() throws Exception {
		try {
			LOGGER.info("Starting in-memory HSQL database for unit tests");
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
			emFactory = Persistence.createEntityManagerFactory("lojixTestPU");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception during HSQL database startup.", ex);
			fail("Exception during HSQL database startup.");
		}

		try {
			LOGGER.info("Building JPA EntityManager for unit tests");
			em = emFactory.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception during JPA EntityManager instanciation.", ex);
			fail("Exception during JPA EntityManager instanciation.");
		}

		try {
			LOGGER.info("Initialize BaseDao");
			baseDao = new BaseDaoImpl(em);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception during Initialize BaseDao.", ex);
			fail("Exception during Initialize BaseDao.");
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		if (emFactory != null) {
			emFactory.close();
		}
		LOGGER.info("Stopping in-memory HSQL database.");
		try {
			connection.createStatement().execute("SHUTDOWN");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Erro in shutdown connection", ex);
		}
	}

	@Before
	public void before() {
		em.getTransaction().begin();
	}

	@After
	public void after() {
		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public EntityManager getEntityManager(){
		return em;
	}

	/**
	 * Possui a função adiciona nova entidade.
	 * Executa persist, flush e assert contains entidade.
	 */
	public void persist(BaseEntity entity) {
		em.persist(entity);
		validaContemEntity(entity);
		em.flush();
	}

	/**
	 * Possui a função verificar se existe entidade no entity manager.
	 */
	public void validaContemEntity(BaseEntity entity) {
		assertTrue(em.contains(entity));
	}

	/**
	 * @see javax.persistence.EntityManager#flush()
	 */
	public void flush() {
		em.flush();
	}

	/**
	 * Possui a função verificar se não existe entidade no entity manager.
	 */
	public void validaNaoContemEntity(BaseEntity entity) {
		assertFalse(em.contains(entity));
	}

	/**
	 * Possui a função imprimir toString, toMap, toXml
	 */
	public void printEntity(BaseEntity entity) throws LojixException {
		System.out.println("#####################################################################################");
		System.out.println(entity);
		System.out.println(entity.toXml());
		System.out.println(entity.toMap());
		System.out.println("#####################################################################################");
	}

	/**
	 * Possui a função remover uma entidade.
	 * Executa refresh, remove, flush e assert contains entidade
	 */
	public void remove(BaseEntity entity) {
		em.refresh(entity);
		em.remove(entity);

		validaNaoContemEntity(entity);

		em.flush();
	}

}
