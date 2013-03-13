package br.com.zetex.lojix.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.Messages;
import br.com.zetex.lojix.util.PackageLog;

/**
 * CLasse com os métodos utilitários que serão utilizados nos serviços.
 *
 * @author Rafa
 * @since 16/03/2012
 * @version 1.0.0
 * @see BaseFacade
 *
 */
public abstract class BaseService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerUtil.getLogger(BaseService.class,
			PackageLog.SERVICE);

	/**
	 * Chamado quando criar uma nova instância do service.
	 *
	 * @author rabsouza
	 *
	 */
	@PostConstruct
	public void create() {
		LOGGER.debug(Messages.get("msg.service.start",
				this.getClass().getSimpleName()));
	}

}
