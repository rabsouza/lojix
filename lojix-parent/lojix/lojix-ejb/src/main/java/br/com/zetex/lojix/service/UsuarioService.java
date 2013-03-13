package br.com.zetex.lojix.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.zetex.lojix.dao.UsuarioDao;
import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.facade.BaseFacade;
import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.Messages;
import br.com.zetex.lojix.util.PackageLog;

/**
 * Classe de serviço para a entidade <strong>Usuario</strong>..
 *
 * @author Rafa
 * @since 16/03/2012
 * @version 1.0.0
 * @see BaseFacade
 *
 */
@Named
@Stateless
public class UsuarioService extends BaseService {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerUtil.getLogger(UsuarioService.class,
			PackageLog.SERVICE);

	@Inject
	private UsuarioDao usuarioDAO;

	/**
	 * Possui a função de verificar se o usuário existe.
	 *
	 * @author rabsouza
	 *
	 * @param usuario
	 *            Usuário a ser logado.
	 * @return true - existe / false - senha inválida.
	 * @throws LojixException
	 *             Exceção ao verificar se o usuário existe.
	 */
	public Boolean verificarLogin(Usuario usuario) throws LojixException {
		try {
			Usuario other = (Usuario) usuarioDAO.get(usuario);
			if (other.getSenha().equals(usuario.getSenha())) {
				LOGGER.info(Messages.get("msg.service.usuario.login.success",
						usuario.getNome()));
				return true;
			} else {
				LOGGER.info(Messages.get("msg.service.usuario.login.fail",
						usuario.getNome()));
				return false;
			}
		} catch (LojixException e) {
			String msgUsuarioInexistente = Messages.get("msg.service.usuario.exception.login",
					usuario.getNome());
			LOGGER.error(msgUsuarioInexistente);
			throw new LojixException(msgUsuarioInexistente);
		}
	}

	/**
	 * Possui a função de adicionar um novo usuário.
	 *
	 * @author rabsouza
	 *
	 * @param usuario
	 *            Usuário a ser adicionado.
	 * @return true - sucesso / falha - error no dados do usuário
	 * @throws CreateEntityException
	 *             Erro ao tentar criar um usuário.
	 * @throws LojixException
	 *             Erro ao tentar executar.
	 */
	public Boolean adicionaUsuario(Usuario usuario) throws CreateEntityException, LojixException {
		Boolean resultado;
		try {
			resultado = usuarioDAO.persist(usuario);
			if (resultado) {
				LOGGER.info(Messages.get("msg.service.entity.add.success",
						"usuario", usuario.getNome()));
			} else {
				LOGGER.info(Messages.get("msg.service.usuario.add.fail",
						"usuario", usuario.getNome()));
			}
			return resultado;
		} catch (LojixException e) {
			throw new LojixException(Messages.get("msg.service.executar.exception",
					usuario.getNome()));
		}
	}

}
