package br.com.zetex.lojix.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import br.com.zetex.lojix.util.UtilCryption;

@SuppressWarnings("javadoc")
@RunWith(value = Enclosed.class)
public class UtilCryptionTest {

	public static class CriptografaNormal {
		@Test
		public void deveCriptografa() {
			String valor = "rafael";
			assertThat(valor, is(not(equalTo(UtilCryption.encryption(valor)))));
		}

		@Test
		public void deveRetornarNuloQuandoNulo() {
			String valor = null;
			assertThat(valor, is(UtilCryption.encryption(valor)));
		}

		@Test
		public void deveRetornarPadraoQuandoVazio() {
			String valor = "";
			assertThat(UtilCryption.FLAG_CRYPTION + "0", is(UtilCryption.encryption(valor)));
		}
	}

	public static class CriptografaMD5 {

		@Test
		public void deveCriptografa() {
			String valor = "rafael";
			assertThat(valor, is(not(equalTo(UtilCryption.encryptionToMd5(valor)))));
		}

		@Test
		public void deveRetornarNuloQuandoNulo() {
			String valor = null;
			assertThat(valor, is(UtilCryption.encryptionToMd5(valor)));
		}

		@Test
		public void deveRetornarNuloQuandoVazio() {
			String valor = "";
			assertThat(null, is(UtilCryption.encryptionToMd5(valor)));
		}
	}

	public static class DescriptografaNormal {
		@Test
		public void deveDescriptografa() {
			String valor = "311433883102338831013432.6";
			assertThat(valor, is(not(equalTo(UtilCryption.decryption(valor)))));
		}

		@Test
		public void deveDescriptografaSemFlag() {
			String valor = "311433883102338831013432";
			assertThat(valor, is(not(equalTo(UtilCryption.decryption(valor)))));
		}

		@Test
		public void deveRetornarNuloQuandoNulo() {
			String valor = null;
			assertThat(valor, is(UtilCryption.decryption(valor)));
		}

		@Test
		public void deveRetornarNuloQuandoVazio() {
			String valor = "";
			assertThat(null, is(UtilCryption.decryption(valor)));
		}
	}

}
