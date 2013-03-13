package br.com.zetex.lojix.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import br.com.zetex.lojix.util.PackageLog;

@SuppressWarnings("javadoc")
public class PackageLogTest {

	@Test
	public void deveRetornaLabel() {
		assertTrue(StringUtils.isNotBlank(PackageLog.DEFAULT.getLabel()));
	}

	@Test
	public void deveRetornaValor() {
		PackageLog packageLog = PackageLog.DEFAULT;
		assertThat(packageLog, is(PackageLog.get(packageLog.getLabel())));
	}

}
