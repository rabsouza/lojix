package br.com.zetex.lojix.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.exception.LojixRuntimeException;
import br.com.zetex.lojix.service.PerfilService;
import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.Messages;
import br.com.zetex.lojix.util.PackageLog;

/**
 * PerfilFacade responsável por implementar a fachada para perfil.
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 10/03/2013
 * @see BaseFacade
 *
 */
@Named
@Stateless
public class PerfilFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerUtil.getLogger(PerfilFacade.class,
			PackageLog.SERVICE);

	@Inject
	private PerfilService perfilService;

	/**
	 * @author rabsouza
	 *
	 * @return lista com todos os perfis.
	 * @throws LojixException
	 *             Erro ao tentar recupera todos.
	 * @see br.com.zetex.lojix.service.PerfilService#recuperaTodosPerfis()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Perfil> recuperaTodosPerfis() throws LojixException {
		LOGGER.info(Messages.get("msg.service.facade.logger.method", "recuperaTodosPerfis", ""));
		return perfilService.recuperaTodosPerfis();
	}

	/**
	 * @author rabsouza
	 *
	 * @param perfil
	 *            Dados do perfil
	 * @return true - perfil adicionar com sucesso/ false - erro ao tentar adicionar um perfil.
	 * @throws LojixException
	 *             Exceção ao tentar adicionar um novo perfil.
	 * @see br.com.zetex.lojix.service.PerfilService#adicionaPerfil(br.com.zetex.lojix.entity.Perfil)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boolean adicionaPerfil(Perfil perfil) throws LojixException {
		LOGGER.info(Messages.get("msg.service.facade.logger.method", "adicionaPerfil", "perfil"));

		if (perfil == null) {
			throw new LojixRuntimeException(Messages.get("msg.service.field.required", perfil));
		}
		try {
			return perfilService.adicionaPerfil(perfil);
		} catch (CreateEntityException e) {
			throw new LojixException(e.getMessage(), e.getCause());
		}
	}

}
