package br.com.zetex.lojix.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação NoSerializable para exibir field no toString, toMap, toXml
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 24/02/2013
 *
 */
@Target(value = { ElementType.METHOD, ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NoSerializable {

}
