package br.com.zetex.lojix.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.service.UsuarioService;

/**
 * UsuarioFacade responsável por implementar a fachada para o serviço usuário.
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 10/03/2013
 * @see BaseFacade
 *
 */
@Named
@Stateless
public class UsuarioFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	/**
	 * @author rabsouza
	 *
	 * @param usuario
	 * @return
	 * @throws LojixException
	 * @see br.com.zetex.lojix.service.UsuarioService#verificarLogin(br.com.zetex.lojix.entity.Usuario)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boolean verificarLogin(Usuario usuario) throws LojixException {
		return usuarioService.verificarLogin(usuario);
	}

	/**
	 * @author rabsouza
	 *
	 * @param usuario
	 * @return
	 * @throws CreateEntityException
	 * @throws LojixException
	 * @see br.com.zetex.lojix.service.UsuarioService#adicionaUsuario(br.com.zetex.lojix.entity.Usuario)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boolean adicionaUsuario(Usuario usuario) throws CreateEntityException, LojixException {
		return usuarioService.adicionaUsuario(usuario);
	}

}
