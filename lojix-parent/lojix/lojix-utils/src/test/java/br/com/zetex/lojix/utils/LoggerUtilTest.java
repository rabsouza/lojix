package br.com.zetex.lojix.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.PackageLog;

@SuppressWarnings("javadoc")
public class LoggerUtilTest {

	@Test
	public void deveAddMDCLogger() {
		LoggerUtil.putMDC("teste", "teste");
	}

	@Test
	public void deveLimparTodosAppenders() {
		LoggerUtil.getLogger(LoggerUtilTest.class, PackageLog.TEST);
		LoggerUtil.clear();
		assertTrue(LoggerUtil.getAppenders().isEmpty());
	}

	@Test
	public void deveRemoveMDCLogger() {
		LoggerUtil.removeMDC("teste");
	}

	@Test
	public void deveRetornaLogger() {
		assertNotNull(LoggerUtil.getLogger(LoggerUtilTest.class,
				PackageLog.TEST));
	}

}
