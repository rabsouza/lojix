package br.com.zetex.lojix.core.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.xml.soap.SOAPException;

import br.com.zetex.lojix.api.LojixApi;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.facade.PerfilFacade;
import br.com.zetex.lojix.facade.UsuarioFacade;

/**
 * LojixEjb classe que possui todos os metodos que serão acessados via ejb.
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 11/03/2013
 * @see BaseEjb, LojixApi
 *
 */
@Stateless(name = "LojixEjb", mappedName = "LojixEjb")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LojixEjb extends BaseEjb implements LojixApi {

	private static final long serialVersionUID = 1L;

	@Inject
	private PerfilFacade perfilFacade;

	@Inject
	private UsuarioFacade usuarioFacade;

	@Override
	public Boolean adicionaPerfil(Perfil perfil) throws SOAPException, RemoteException {
		try {
			return perfilFacade.adicionaPerfil(perfil);
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean adicionaUsuario(Usuario usuario)
			throws SOAPException, RemoteException {
		try {
			return usuarioFacade.adicionaUsuario(usuario);
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Perfil> recuperaTodosPerfis() throws SOAPException, RemoteException {
		try {
			return perfilFacade.recuperaTodosPerfis();
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean verificarLogin(Usuario usuario)
			throws SOAPException, RemoteException {
		try {
			return usuarioFacade.verificarLogin(usuario);
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

}
