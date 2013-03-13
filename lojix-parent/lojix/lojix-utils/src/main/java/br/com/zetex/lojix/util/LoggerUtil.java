package br.com.zetex.lojix.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

/**
 * Classe responsável por criar os logs da aplicação.
 *
 * @author Rafa
 * @since 15/03/2012
 * @version 1.0.0
 * @see Logger
 *
 */
public class LoggerUtil extends Logger {

	private static final Map<PackageLog, Appender> appenders = new HashMap<PackageLog, Appender>();

	private static final String CONST_PARSE_NAME_LOG = "#package#";
	private static Appender defaultAppender;
	private static Level level;

	private static final Logger LOGGER = Logger.getLogger(LoggerUtil.class);
	private static final String nameLog = "lojix_" + CONST_PARSE_NAME_LOG
			+ ".log";

	static {
		String fileName = nameLog.replace("_" + CONST_PARSE_NAME_LOG, "");
		String conversionPattern = Properties.get("logger.partten."
				+ PackageLog.DEFAULT);
		try {
			defaultAppender = Factory.createAppender(fileName, getLevelLogger(),
					conversionPattern);
		} catch (IOException e) {
			LOGGER.error("Erro ao tentar criar logger!", e);
		}
	}

	private LoggerUtil() {
		super(null);
	}

	/**
	 * Construtor para <em>LoggerUtil</em>
	 *
	 */
	private LoggerUtil(String name) {
		super(name);
	}

	/**
	 *
	 * @see java.util.Map#clear()
	 */
	public static void clear() {
		appenders.clear();
	}

	/**
	 * @return appenders
	 */
	public static Map<PackageLog, Appender> getAppenders() {
		return appenders;
	}

	/**
	 * Retorna o log da aplicação por modulo.
	 *
	 * @param clazz
	 *            Class do logger
	 * @param packageLog
	 *            Modulo do log.
	 * @return logger
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clazz, PackageLog packageLog) {

		Logger logger = Logger.getLogger(clazz);
		logger.setLevel(getLevelLogger());
		logger.addAppender(defaultAppender);

		level = Level.toLevel(Properties.get("logger.level"));
		logger.setLevel(level);

		Appender appender;
		if (appenders.containsKey(packageLog)) {
			appender = appenders.get(packageLog);
		} else {
			String fileName = nameLog.replace(CONST_PARSE_NAME_LOG,
					packageLog.toString());
			String conversionPattern = Properties.get("logger.partten."
					+ packageLog);
			try {
				appender = Factory.createAppender(fileName, level,
						conversionPattern);
				appenders.put(packageLog, appender);
			} catch (IOException e) {
				logger.fatal("erro ao tentar criar um appender", e);
				return logger;
			}
		}

		if (logger.getAppender(appender.getName()) == null) {
			logger.addAppender(appender);
		}

		return logger;

	}

	private static Level getLevelLogger() {
		Level level;
		String strLevel = Properties.get("logger.level");
		if (StringUtils.isBlank(strLevel)) {
			level = Level.INFO;
		} else {
			level = Level.toLevel(strLevel);
		}
		return level;
	}

	/**
	 * Add um MDC para o logger.
	 *
	 * @param key
	 *            Chave do MDC
	 * @param value
	 *            Valor da chave.
	 */
	public static void putMDC(String key, Object value) {
		MDC.put(key, value);
	}

	/**
	 * Remove um MDC para o logger.
	 *
	 * @param key
	 *            Chave do MDC
	 */
	public static void removeMDC(String key) {
		MDC.remove(key);
	}

}
