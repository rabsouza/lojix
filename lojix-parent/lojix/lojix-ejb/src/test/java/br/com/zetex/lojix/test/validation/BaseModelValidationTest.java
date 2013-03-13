package br.com.zetex.lojix.test.validation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.entity.PessoaFisica;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.entity.enumeration.PermissaoEnum;
import br.com.zetex.lojix.entity.enumeration.PrioridadeEnum;
import br.com.zetex.lojix.entity.enumeration.SexoEnum;
import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.PackageLog;

@SuppressWarnings("javadoc")
public class BaseModelValidationTest {

	private static final Logger LOGGER = LoggerUtil.getLogger(BaseModelValidationTest.class, PackageLog.TEST);

	private static EntityManagerFactory emFactory;

	private static EntityManager em;

	private static Connection connection;

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

	@Test
	public void executaTestePersistencia() {

			em.getTransaction().begin();
			PessoaFisica pessoa = new PessoaFisica();
			pessoa.setNome("Rafael");
			pessoa.setCpf("123.456.789-00");
			pessoa.setDataNascimento(new Date());
			pessoa.setSexo(SexoEnum.M);

			Usuario usuario = new Usuario(pessoa);
			usuario.setEmail("eskatos@yopmail.com");
			usuario.setNome("teste");
			usuario.setSenha("12345");

			Perfil perfil = new Perfil();
			perfil.setNome("ADMIN");
			perfil.setPermissao(PermissaoEnum.ADMIN);
			perfil.setPrioridade(PrioridadeEnum.ALTA);
			perfil.setStatus(Boolean.TRUE);
			em.persist(perfil);
			assertTrue(em.contains(perfil));
			usuario.setPerfil(perfil);

			em.persist(usuario);
			assertTrue(em.contains(usuario));

			em.flush();
			em.getTransaction().commit();

	}

	@Before
	public void setUp() throws Exception {
		try {
			LOGGER.info("Building JPA EntityManager for unit tests");
			em = emFactory.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception during JPA EntityManager instanciation.", ex);
			fail("Exception during JPA EntityManager instanciation.");
		}
	}

	@After
	public void tearDown() throws Exception {
		LOGGER.info("Shuting down Hibernate JPA layer.");
		if (em != null) {
			em.close();
		}
	}
}
