package br.com.zetex.lojix.test.util;

import static org.junit.Assert.assertTrue;

import java.util.MissingResourceException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import br.com.zetex.lojix.util.Messages;

@SuppressWarnings("javadoc")
public class MessagesTest {

	private static final String TESTE = "teste";
	private static final String TESTE1 = "teste1";
	private static final String TESTE2 = "teste2";

	@Test(expected = MissingResourceException.class)
	public void deveRetornaErroQdoNaoExistePropriedade() {
		Messages.get(TESTE2);
	}

	@Test
	public void deveRetornaPropriedade() {
		assertTrue(StringUtils.isNotBlank(Messages.get(TESTE)));
	}

	@Test
	public void deveRetornaPropriedadeQdoEnviadoParametro() {
		assertTrue(StringUtils
				.isNotBlank(Messages.get(TESTE1, TESTE)));
	}

}
