package br.com.zetex.lojix.exception;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class LojixRuntimeExceptionTest {

	@Test(expected = LojixRuntimeException.class)
	public void deveRetornaLojixRuntimeException() throws LojixRuntimeException {
		throw new LojixRuntimeException();
	}

	@Test(expected = LojixRuntimeException.class)
	public void deveRetornaLojixRuntimeExceptionQdoParametroExcecao()
			throws LojixRuntimeException {
		Exception exception = new Exception();
		throw new LojixRuntimeException(exception);
	}

	@Test(expected = LojixRuntimeException.class)
	public void deveRetornaLojixRuntimeExceptionQdoParametroString()
			throws LojixRuntimeException {
		throw new LojixRuntimeException("Teste");
	}

	@Test(expected = LojixRuntimeException.class)
	public void deveRetornaLojixRuntimeExceptionQdoParametroStringEExcecao()
			throws LojixRuntimeException {
		throw new LojixRuntimeException("Teste", new Exception());
	}

}
