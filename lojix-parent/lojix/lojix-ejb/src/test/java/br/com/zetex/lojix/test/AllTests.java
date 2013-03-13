package br.com.zetex.lojix.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.zetex.lojix.test.dao.UsuarioDaoTest;
import br.com.zetex.lojix.test.util.MessagesTest;
import br.com.zetex.lojix.test.util.UtilTest;
import br.com.zetex.lojix.test.validation.BaseModelValidationTest;

@SuppressWarnings("javadoc")
@RunWith(Suite.class)
@SuiteClasses({ UtilTest.class, BaseModelValidationTest.class, MessagesTest.class, UsuarioDaoTest.class })
public class AllTests {

}
