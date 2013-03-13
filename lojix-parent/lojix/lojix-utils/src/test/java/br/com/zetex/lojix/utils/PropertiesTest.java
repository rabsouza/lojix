package br.com.zetex.lojix.utils;

import static org.junit.Assert.assertTrue;

import java.util.MissingResourceException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import br.com.zetex.lojix.util.Properties;

@SuppressWarnings("javadoc")
@RunWith(Enclosed.class)
public class PropertiesTest {

	public static class UtilizaMetodoGet {
		private static final String TESTE = "teste";
		private static final String TESTE1 = "teste1";
		private static final String TESTE2 = "teste2";

		@Test(expected = MissingResourceException.class)
		public void deveRetornaErroQdoNaoExistePropriedade() {
			Properties.get(TESTE2);
		}

		@Test
		public void deveRetornaPropriedade() {
			assertTrue(StringUtils.isNotBlank(Properties.get(TESTE)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametro() {
			assertTrue(StringUtils
					.isNotBlank(Properties.get(TESTE1, TESTE)));
		}
	}

	public static class UtilizaMetodoLoadConfigProperties {
		private static final String TESTE1 = "teste1";
		private static final String TESTE = "teste";
		private static final String TESTE2 = "teste2";

		@Test(expected = MissingResourceException.class)
		public void deveRetornaErroQdoNaoExistePropriedade() {
			Properties.get(TESTE2);
		}

		@Test
		public void deveRetornaPropriedade() {
			assertTrue(StringUtils.isNotBlank(Properties
					.loadConfigProperties(TESTE)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametro() {
			assertTrue(StringUtils.isNotBlank(Properties.loadConfigProperties(
					TESTE1, TESTE)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametroNulo() {
			Object obj = null;
			assertTrue(StringUtils.isNotBlank(Properties.loadConfigProperties(
					TESTE1, obj)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametroArrayNulo() {
			Object[] obj = null;
			assertTrue(StringUtils.isNotBlank(Properties.loadConfigProperties(
					TESTE1, obj)));
		}
	}

	public static class UtilizaMetodoLoadMsgProperties {
		private static final String TESTE1 = "teste1";
		private static final String TESTE = "teste";
		private static final String TESTE2 = "teste2";

		@Test(expected = MissingResourceException.class)
		public void deveRetornaErroQdoNaoExistePropriedade() {
			Properties.get(TESTE2);
		}

		@Test
		public void deveRetornaPropriedade() {
			assertTrue(StringUtils.isNotBlank(Properties
					.loadMsgProperties(TESTE)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametro() {
			assertTrue(StringUtils.isNotBlank(Properties.loadMsgProperties(
					TESTE1, TESTE)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametroNulo() {
			Object obj = null;
			assertTrue(StringUtils.isNotBlank(Properties.loadMsgProperties(
					TESTE1, obj)));
		}

		@Test
		public void deveRetornaPropriedadeQdoEnviadoParametroArrayNulo() {
			Object[] obj = null;
			assertTrue(StringUtils.isNotBlank(Properties.loadMsgProperties(
					TESTE1, obj)));
		}
	}

}
