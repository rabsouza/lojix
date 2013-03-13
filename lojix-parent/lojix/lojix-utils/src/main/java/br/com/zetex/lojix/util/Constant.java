package br.com.zetex.lojix.util;

/**
 * Classe responsável por amarzenar todas as constante do sistema.
 *
 * @author rabsouza
 * @since 20/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
public class Constant {

	public interface XML {
		String ID_NODE_XML = "uiId";
		String IMPL_PACKAGE_NAME = "";
		String PREFIX_NAME_GET_METHOD = "get";
		String PREFIX_NAME_IS_METHOD = "is";
		String PREFIX_NAME_SET_METHOD = "set";
		String SPEC_PACKAGE_NAME = "";
		String SUFIX_CLASS_NAME = "";
		String VERSAO_NODE_XML = "versao";
		String XML_NODO_CLASS = "class";
		String XML_NODO_FIELD = "field";
		String XML_NODO_NAME = "name";
	}

	public interface FACTORY {
		String MSG_ERRO_FACTORY = "Erro ao tentar criar uma instancia para ";
	}

	public interface LOGGER {
		String ENCODING_LOGGER = "iso-8859-1";
	}

}
