package br.com.zetex.lojix.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.entity.BaseEntityImpl;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.exception.LojixRuntimeException;

/**
 * <strong>Util</strong> possui a função de criar as funções que serão uteis em
 * todos os projetos.
 *
 * @author rafael.batista
 * @version 1.0
 * @since 28/12/2010
 */
public class Util {

	private static final Logger LOGGER = LoggerUtil.getLogger(Util.class,
			PackageLog.UTIL);

	private static XMLOutputter out = new XMLOutputter();

	private Util() {
	}

	/**
	 * Método<strong>fillBeanFromMap(mapValue, obj)</strong> possui a função de
	 * preencher os atributos de um <em>{@link BaseEntity}</em> a partir de um <em>{@link Map}</em>.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param mapValue
	 *            {@link Map} contendo os valores dos atributos que serão
	 *            preenchidos automaticamente do {@link BaseEntity}.
	 * @param obj
	 *            {@link BaseEntity} que será preenchido pelo {@link Map}.
	 * @throws LojixException
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void fillBeanFromMap(Map<String, Object> mapValue,
			BaseEntity obj) throws LojixException {

		if ((mapValue == null) || (obj == null)) {
			throw new LojixException(
					"Os paramêtros mapValue e/ou obj não pode ser nulos!");
		}
		LOGGER.info("Preenchendo o obj=" + obj.getClass().getSimpleName()
				+ " através do map=" + String.valueOf(mapValue));

		Class<? extends BaseEntity> objClass = obj.getClass();

		while (true) {

			Method[] listMethods = objClass.getDeclaredMethods();
			for (Method method : listMethods) {
				if (method.getName()
						.startsWith(Constant.XML.PREFIX_NAME_SET_METHOD)) {
					String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_SET_METHOD);

					try {
						if (mapValue.containsKey(nameAttr)) {
							method.invoke(obj, mapValue.get(nameAttr));
						} else {
							method.invoke(obj);
						}
					} catch (Exception e) {
					}
				}
			}
			objClass = (Class<? extends BaseEntity>) objClass.getSuperclass();
			if (objClass.isInstance(new BaseEntityImpl())
					|| objClass.isInstance(new Object())) {
				break;
			}
		}

		obj.setUiId((Long) mapValue.get(Constant.XML.ID_NODE_XML));
		obj.setVersao((Integer) mapValue.get(Constant.XML.VERSAO_NODE_XML));

	}

	private static void fillToStringAttr(Class<? extends BaseEntity> objClass, String nameAttr, StringBuilder strObj, Method method, Object obj) {
		try {
			Field field = objClass.getDeclaredField(nameAttr);
			if ((field == null) || field.isAnnotationPresent(NoSerializable.class)) {
				return;
			}

			try {
				strObj.append(nameAttr).append("=")
						.append((method).invoke(obj));
				strObj.append(", ");
			} catch (Exception e) {
				LOGGER.warn(String.format("Aviso no toString do field[%s], class[%s].", nameAttr, objClass.getSimpleName()));
				strObj.append(nameAttr).append("=").append("null");
			}
		} catch (NoSuchFieldException e) {
			LOGGER.warn(String.format("Erro no toString do field[%s], class[%s].", nameAttr, objClass.getSimpleName()), e);
			return;
		} catch (Exception e) {
			LOGGER.error(String.format("Erro no toString do field[%s], class[%s].", nameAttr, objClass.getSimpleName()), e);
		}
	}

	/**
	 * Método<strong>getElementContent(obj)</strong> possui a função de retorna
	 * o nodo xml para um <em>{@link BaseEntity}</em>
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param obj
	 *            {@link BaseEntity} que será convertido no formato XML.
	 * @return <strong>element</strong> {@link Element} que representa um nodo
	 *         XML.
	 */
	@SuppressWarnings("unchecked")
	private static Element getElementContent(BaseEntity obj) {

		if (obj == null) {
			return null;
		}

		Class<? extends BaseEntity> objClass = obj.getClass();

		Element classNodo = new Element(Constant.XML.XML_NODO_CLASS,
				Constant.XML.XML_NODO_CLASS);
		classNodo.setAttribute(Constant.XML.XML_NODO_NAME, objClass.getSimpleName()
				.replaceAll(Constant.XML.SUFIX_CLASS_NAME, "").toUpperCase());

		Element id = new Element(Constant.XML.ID_NODE_XML, Constant.XML.XML_NODO_FIELD);
		id.setText(String.valueOf(obj.getUiId()));
		classNodo.addContent(id);

		while (true) {
			Method[] listMethods = objClass.getDeclaredMethods();
			for (Method method : listMethods) {

				if (!method.isAnnotationPresent(Transient.class) || method.isAnnotationPresent(NoSerializable.class)) {
					if (method.getName().startsWith(
							Constant.XML.PREFIX_NAME_GET_METHOD)) {
						String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_GET_METHOD);

						addNovoElementXml(obj, classNodo, method, nameAttr, objClass);

					} else if (method.getName().startsWith(
							Constant.XML.PREFIX_NAME_IS_METHOD)) {

						String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_IS_METHOD);
						addNovoElementXml(obj, classNodo, method, nameAttr, objClass);

					}
				}
			}

			objClass = (Class<? extends BaseEntity>) objClass.getSuperclass();
			if (objClass.isInstance(new BaseEntityImpl())
					|| objClass.isInstance(new Object())) {
				break;
			}

		}

		return classNodo;
	}

	/**
	 * Possui a função de adicionar um novo elemento ao xml
	 *
	 * @author rabsouza
	 *
	 * @param obj
	 *            Objeto a ser preenchido
	 * @param mapValue
	 *            Map do objeto
	 * @param objClass
	 *            Classe do objeto
	 * @param method
	 *            Metodo que está sendo executado
	 * @param nameAttr
	 *            Nome do atributo.
	 */
	private static void addNovoElementXml(BaseEntity obj, Element classNodo, Method method, String nameAttr, Class<? extends BaseEntity> objClass) {
		Element element = new Element(nameAttr,
				Constant.XML.XML_NODO_FIELD);
		try {
			Field field = objClass.getDeclaredField(nameAttr);
			if ((field == null) || field.isAnnotationPresent(NoSerializable.class)) {
				return;
			}

			Object newObj = method.invoke(obj);
			if (newObj instanceof BaseEntity) {
				element.addContent(getElementContent((BaseEntity) newObj));
			} else {
				element.setText(String.valueOf(method
						.invoke(obj)));
			}
		} catch (NoSuchFieldException e) {
			LOGGER.warn(String.format("Erro no toString do field[%s], class[%s].", nameAttr, objClass.getSimpleName()), e);
			return;
		} catch (Exception e) {
			element.setText(null);
		}
		classNodo.addContent(element);
	}

	/**
	 * Possui a função normalizar o nome do metodo para padrão de nome de atributo.
	 *
	 * @author rabsouza
	 *
	 * @param method
	 *            Metodo a ser normalizado.
	 * @param prefixMethod
	 *            prefix do metodo.
	 * @return nameAttr Nome do atributo.
	 */
	private static String normalizaNomeMetodoParaNomeAttr(Method method, String prefixMethod) {
		String nameAttr = method.getName().substring(prefixMethod.length());
		String inicialName = String.valueOf(nameAttr.charAt(0))
				.toLowerCase();
		nameAttr = inicialName + nameAttr.substring(1);
		return nameAttr;
	}

	/**
	 * Método<strong>toMap(obj)</strong> possui a função de retorna uma <em>{@link Map}</em> que representa o <em>{@link BaseEntity}</em>.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param obj
	 *            {@link BaseEntity} que será convertido para {@link Map}.
	 * @return <strong>map</strong> {@link Map} contendo os valores dos
	 *         atributos do {@link BaseEntity}.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(BaseEntity obj) {

		LOGGER.info("Convertendo para map o obj=" + String.valueOf(obj));
		Map<String, Object> mapValue = new HashMap<String, Object>();
		if (obj == null) {
			return mapValue;
		}

		mapValue.put(Constant.XML.ID_NODE_XML, obj.getUiId());
		mapValue.put(Constant.XML.VERSAO_NODE_XML, obj.getVersao());

		Class<? extends BaseEntity> objClass = obj.getClass();

		while (true) {
			Method[] listMethods = objClass.getDeclaredMethods();
			for (Method method : listMethods) {

				if (!method.isAnnotationPresent(Transient.class)) {
					if (method.getName().startsWith(
							Constant.XML.PREFIX_NAME_GET_METHOD)) {

						String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_GET_METHOD);
						preencheMapValue(obj, mapValue, objClass, method, nameAttr);
					} else if (method.getName().startsWith(
							Constant.XML.PREFIX_NAME_IS_METHOD)) {

						String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_IS_METHOD);
						preencheMapValue(obj, mapValue, objClass, method, nameAttr);
					}
				}
			}

			objClass = (Class<? extends BaseEntity>) objClass.getSuperclass();
			if (objClass.isInstance(new BaseEntityImpl())
					|| objClass.isInstance(new Object())) {
				break;
			}

		}
		return mapValue;
	}

	/**
	 * Possui a função de preencher o map com o nome do atributo e o valor.
	 *
	 * @author rabsouza
	 *
	 * @param obj
	 *            Objeto a ser preenchido
	 * @param mapValue
	 *            Map do objeto
	 * @param objClass
	 *            Classe do objeto
	 * @param method
	 *            Metodo que está sendo executado
	 * @param nameAttr
	 *            Nome do atributo.
	 */
	private static void preencheMapValue(BaseEntity obj, Map<String, Object> mapValue, Class<? extends BaseEntity> objClass, Method method, String nameAttr) {
		try {
			Field field = objClass.getDeclaredField(nameAttr);
			if ((field == null) || field.isAnnotationPresent(NoSerializable.class)) {
				return;
			}
			mapValue.put(nameAttr, method.invoke(obj));
		} catch (NoSuchFieldException e) {
			LOGGER.warn(String.format("Erro no toString do field[%s], class[%s].", nameAttr, objClass.getSimpleName()), e);
			return;
		} catch (Exception e) {
			mapValue.put(nameAttr, null);
		}
	}

	/**
	 * Método<strong>toString(obj)</strong> possui a função de retorna uma <em>{@link String}</em> que representa o <em>{@link BaseEntity}</em>.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param obj
	 *            {@link BaseEntity} que será convertido para {@link String}.
	 * @return <strong>str</strong> {@link String} contendo os valores dos
	 *         atributos do {@link BaseEntity}.
	 */
	@SuppressWarnings("unchecked")
	public static String toString(BaseEntity obj) {

		if (obj == null) {
			return "";
		}
		LOGGER.info("Convertendo para toString o obj=" + obj.getClass().getName());

		Class<? extends BaseEntity> objClass = obj.getClass();
		StringBuilder strObj = new StringBuilder();

		strObj.append(
				objClass.getSimpleName().replaceAll(Constant.XML.SUFIX_CLASS_NAME,
						"")).append("[");
		strObj.append("uiId=").append(obj.getUiId()).append(", ");
		strObj.append("pk=").append(obj.getPk()).append(", ");

		while (true) {
			Method[] listMethods = objClass.getDeclaredMethods();
			for (Method method : listMethods) {

				if (!method.isAnnotationPresent(Transient.class) && !method.isAnnotationPresent(NoSerializable.class)) {
					if (method.getName().startsWith(
							Constant.XML.PREFIX_NAME_GET_METHOD)) {

						String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_GET_METHOD);
						fillToStringAttr(objClass, nameAttr, strObj, method, obj);

					} else if (method.getName().startsWith(
							Constant.XML.PREFIX_NAME_IS_METHOD)) {

						String nameAttr = normalizaNomeMetodoParaNomeAttr(method, Constant.XML.PREFIX_NAME_IS_METHOD);
						fillToStringAttr(objClass, nameAttr, strObj, method, obj);
					}
				}
			}
			objClass = (Class<? extends BaseEntity>) objClass.getSuperclass();
			if (objClass.isInstance(new BaseEntityImpl())
					|| objClass.isInstance(new Object())) {
				break;
			}
		}

		if (strObj.charAt(strObj.length() - 2) == ',') {
			strObj.delete(strObj.length() - 2, strObj.length());
		}
		strObj.append("]");

		return strObj.toString();
	}

	/**
	 * Metodo <b>toXml(obj)</b> possui a função de retorna uma <em>{@link String}</em> que representa o {@link BaseEntity} no formato
	 * XML.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param obj
	 *            {@link BaseEntity} que será convertido para {@link String} no
	 *            formato XML.
	 * @return <strong>xml</strong> {@link String} contendo os valores dos
	 *         atributos do {@link BaseEntity} no formato XML.
	 */
	public static String toXml(BaseEntity obj) {
		LOGGER.info("Convertendo para xml o obj=" + String.valueOf(obj));

		Document doc = new Document();
		if (obj != null) {
			doc.addContent(getElementContent(obj));
		}
		return out.outputString(doc);
	}

	/**
	 * Possui a função de converte uma lista do tipo BaseEntity para um tipo especifico.
	 *
	 * @author rabsouza
	 *
	 * @param list
	 *            Lista a ser convertida.
	 * @param clazz
	 *            Classe da lista convertida.
	 * @return lista convertida.
	 */
	@SuppressWarnings("unchecked")
	public static <Type extends BaseEntity> List<Type> convertList(List<BaseEntity> list, Class<Type> clazz) {
		if ((list == null) || (clazz == null)) {
			return null;
		}
		try{
		return new ArrayList<Type>(CollectionUtils.typedCollection(list, clazz));
		}catch(IllegalArgumentException e){
			throw new LojixRuntimeException(String.format("Erro ao tentar converte lista para o tipo[%s].", clazz.getSimpleName()), e.getCause());
		}

	}
}
