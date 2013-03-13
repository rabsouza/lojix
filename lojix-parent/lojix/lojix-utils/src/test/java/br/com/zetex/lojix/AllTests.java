package br.com.zetex.lojix;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.zetex.lojix.exception.LojixExceptionTest;
import br.com.zetex.lojix.exception.LojixRuntimeExceptionTest;
import br.com.zetex.lojix.utils.ConstantTest;
import br.com.zetex.lojix.utils.FactoryTest;
import br.com.zetex.lojix.utils.LoggerUtilTest;
import br.com.zetex.lojix.utils.PackageLogTest;
import br.com.zetex.lojix.utils.PropertiesTest;
import br.com.zetex.lojix.utils.UtilCryptionTest;

@SuppressWarnings("javadoc")
@RunWith(Suite.class)
@SuiteClasses({ LojixExceptionTest.class, LojixRuntimeExceptionTest.class, FactoryTest.class,
		LoggerUtilTest.class, PackageLogTest.class, PropertiesTest.class,
		UtilCryptionTest.class, ConstantTest.class })
public class AllTests {

}
