package br.com.zetex.lojix.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.zetex.lojix.util.Constant;

@SuppressWarnings("javadoc")
public class ConstantTest {

	@Test
	public void acessaInterfaceFactoryConstante(){
		Object obj = Constant.FACTORY.MSG_ERRO_FACTORY;
		assertNotNull(obj);
	}

	@Test
	public void acessaInterfaceXmlConstante(){
		Object obj = Constant.XML.ID_NODE_XML;
		assertNotNull(obj);
	}
	@Test
	public void acessaInterfaceLoggerConstante(){
		Object obj = Constant.LOGGER.ENCODING_LOGGER;
		assertNotNull(obj);
	}

}
