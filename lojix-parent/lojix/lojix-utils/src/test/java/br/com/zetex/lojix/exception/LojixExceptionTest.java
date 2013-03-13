package br.com.zetex.lojix.exception;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class LojixExceptionTest {

	@Test(expected = LojixException.class)
	public void deveRetornaLojixException() throws LojixException {
		throw new LojixException();
	}

	@Test(expected = LojixException.class)
	public void deveRetornaLojixExceptionQdoParametroExcecao()
			throws LojixException {
		Exception exception = new Exception();
		throw new LojixException(exception);
	}

	@Test(expected = LojixException.class)
	public void deveRetornaLojixExceptionQdoParametroString()
			throws LojixException {
		throw new LojixException("Teste");
	}

	@Test(expected = LojixException.class)
	public void deveRetornaLojixExceptionQdoParametroStringEExcecao()
			throws LojixException {
		throw new LojixException("Teste", new Exception());
	}

}
