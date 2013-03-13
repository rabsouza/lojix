package br.com.zetex.lojix.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.zetex.lojix.dao.PerfilDao;
import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.facade.BaseFacade;
import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.Messages;
import br.com.zetex.lojix.util.PackageLog;
import br.com.zetex.lojix.util.Util;

/**
 * Classe de serviço para a entidade <strong>Perfil</strong>..
 *
 * @author Rafa
 * @since 16/03/2012
 * @version 1.0.0
 * @see BaseFacade
 *
 */
@Named
@Stateless
public class PerfilService extends BaseService {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerUtil.getLogger(PerfilService.class,
			PackageLog.SERVICE);

	@Inject
	private PerfilDao perfilDAO;

	/**
	 * Possui a função de adicionar um novo perfil.
	 *
	 * @author rabsouza
	 *
	 * @param perfil
	 *            Usuário a ser adicionado.
	 * @return true - sucesso / falha - error no dados do perfil
	 * @throws CreateEntityException
	 *             Erro ao tentar criar um perfil.
	 * @throws LojixException
	 *             Erro ao tentar adicionar novo perfil.
	 */
	public Boolean adicionaPerfil(Perfil perfil) throws CreateEntityException, LojixException {
		Boolean resultado;
		try {
			resultado = perfilDAO.persist(perfil);
			if (resultado) {
				LOGGER.info(Messages.get("msg.service.entity.add.success",
						"perfil", perfil.getNome()));
			} else {
				LOGGER.info(Messages.get("msg.service.entity.add.fail",
						"perfil", perfil.getNome()));
			}
			return resultado;
		} catch (LojixException e) {
			throw new LojixException(Messages.get("msg.service.executar.exception",
					"adicionaPerfil"));
		}
	}

	/**
	 * Possui a função de recuperar todos os perfis cadastrados.
	 *
	 * @author rabsouza
	 *
	 * @return lista com todos os perfis.
	 * @throws LojixException
	 *             Erro ao tentar recupera todos.
	 */
	public List<Perfil> recuperaTodosPerfis() throws LojixException {
		try {
			List<BaseEntity> resultados = perfilDAO.selectAll();
			return Util.convertList(resultados, Perfil.class);
		} catch (LojixException e) {
			throw new LojixException(Messages.get("msg.service.executar.exception",
					"recuperaTodosPerfis"));
		}
	}

}
