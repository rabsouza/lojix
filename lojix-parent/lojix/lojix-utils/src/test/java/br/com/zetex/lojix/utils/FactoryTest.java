package br.com.zetex.lojix.utils;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.log4j.Level;
import org.junit.Test;

import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.util.Factory;
import br.com.zetex.lojix.util.Properties;

@SuppressWarnings("javadoc")
public class FactoryTest {

	@Test
	public void deveCriaApenderLog() throws IOException {
		assertNotNull(Factory.createAppender("teste.log", Level.ERROR,
				"%d{dd/MM/yyyy HH\\:mm\\:ss} %5p [%c{3}\\:%L] - %m%n"));
	}

	@Test
	public void deveRetornaInstancia() throws LojixException {
		assertNotNull(Factory.create(String.class));
	}

	@Test
	public void deveRetornaObjectInterface() throws LojixException {
		assertNotNull(Factory.create(Cloneable.class));
	}

	@Test
	public void deveRetornaObjectNaoConstrutorPadrao() throws LojixException {
		assertNotNull(Factory.create(Properties.class));
	}

	@Test(expected = LojixException.class)
	public void deveRetornaNullo() throws LojixException {
		Factory.create(null);
	}

}
