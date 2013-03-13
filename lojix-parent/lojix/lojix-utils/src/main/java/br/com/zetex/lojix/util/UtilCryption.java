package br.com.zetex.lojix.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * <strong>UtilCryption</strong> possui a função de realizar a criptografia e a
 * descriptografia de uma string.
 *
 * @author rafael.batista
 * @version 1.0
 * @since 28/12/2010
 */
public class UtilCryption {

	private UtilCryption() {
	}

	/**
	 * Flag utilizada na criptografia e descriptografia de uma string.
	 */
	public static final String FLAG_CRYPTION = ".";

	private static final Logger LOGGER = LoggerUtil.getLogger(
			UtilCryption.class, PackageLog.UTIL);
	private static final int numberFactor = 3;

	/**
	 * Método <strong>decryptionString(str)</strong> possui a função de realizar
	 * descriptografia de uma {@link String}.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param str
	 *            {@link String} que será descriptografada.
	 * @return <strong>result</strong> {@link String} resultado da
	 *         descriptografia.
	 */
	public static String decryption(String str) {
		LOGGER.debug(String.format("Descriptografando [%s]", str));
		if (StringUtils.isBlank(str)) {
			return null;
		}

		final int sizeStrObj;
		final StringBuilder result = new StringBuilder();

		if (str.contains(FLAG_CRYPTION)) {
			sizeStrObj = Integer.parseInt(str.substring(str
					.indexOf(FLAG_CRYPTION) + 1));
			str = str.substring(0, str.indexOf(FLAG_CRYPTION));

		} else {
			sizeStrObj = str.length();
		}

		final String[] strCodex = splitMatchesCodex(str);
		for (int i = 0; i < strCodex.length; i++) {
			final int codex = Integer.parseInt(strCodex[i]);
			final int temp = ((i * numberFactor) % sizeStrObj) + 1;
			final int intCh = codex / temp;

			result.append(Character.toString((char) intCh));
		}
		LOGGER.debug(String.format("Resultado descriptografia [%s]", result.toString()));
		return result.toString();

	}

	/**
	 * Método <strong>encryptionString(str)</strong> possui a função de realizar
	 * criptografia de uma {@link String}.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param str
	 *            {@link String} que será criptografada.
	 * @return <strong>result</strong> {@link String} resultado da criptografia.
	 */
	public static String encryption(String str) {
		LOGGER.debug(String.format("Criptografando [%s]", str));
		if (str == null) {
			return null;
		}

		final int sizeStrObj = str.length();
		final StringBuilder result = new StringBuilder();

		for (int i = 0; i < sizeStrObj; i++) {
			final int intCh = str.charAt(i);
			final int temp = ((i * numberFactor) % sizeStrObj) + 1;
			final int codexCh = temp * intCh;

			result.append(String.valueOf(codexCh).length());
			result.append(codexCh);
		}
		LOGGER.debug(String.format("Resultado criptografia [%s]", result.toString()));
		result.append(FLAG_CRYPTION).append(sizeStrObj);
		return result.toString();

	}

	/**
	 * Método <strong>convertToMd5(str)</strong> possui a função de criptografar
	 * para md5 uma <em>{@link String}</em>
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param str
	 *            {@link String} que será criptografada.
	 * @return <strong>result</strong> {@link String} resultado da criptografia.
	 */
	public static String encryptionToMd5(String str) {
		LOGGER.debug(String.format("Criptografando md5 [%s]", str));
		if (StringUtils.isBlank(str)) {
			return null;
		}

		String result = "";
		MessageDigest md = null;
		try {

			md = MessageDigest.getInstance("MD5");
			final BigInteger hash = new BigInteger(1, md.digest(str.getBytes()));
			result = hash.toString(16);
		} catch (final NoSuchAlgorithmException e) {
			LOGGER.error("Erro ao tentar aplicar criptografia MD5!");
		}
		LOGGER.debug(String.format("Resultado criptografia md5 [%s]", result));
		return result;

	}

	/**
	 * Método <strong>splitMatchesCodex(str)</strong> possui a função de
	 * retornar String[] da {@link String} criptografada.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param str
	 *            {@link String} que foi criptografada.
	 * @return str[] vetores para realizar a descriptografia.
	 */
	private static String[] splitMatchesCodex(String str) {

		final String strTemp = str;
		final List<String> listStr = new ArrayList<String>();

		int pos = 0;
		int size = Integer.parseInt(Character.toString(strTemp.charAt(pos)));
		try {

			while (pos < strTemp.length()) {
				listStr.add(strTemp.substring(++pos, pos += size));
				if (pos < strTemp.length()) {
					size = Integer.parseInt(Character.toString(strTemp
							.charAt(pos)));
				} else {
					break;
				}
			}
		} catch (final Exception e) {
			return null;

		}
		return listStr.toArray(new String[0]);

	}

}
