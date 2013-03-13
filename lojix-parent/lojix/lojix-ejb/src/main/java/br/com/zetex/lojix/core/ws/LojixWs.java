package br.com.zetex.lojix.core.ws;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import br.com.zetex.lojix.api.LojixApi;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.facade.PerfilFacade;
import br.com.zetex.lojix.facade.UsuarioFacade;

/**
 * LojixWs classe que possui todos os metodos que serão acessados via webService.
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 11/03/2013
 * @see BaseWs, LojixApi
 *
 */

@WebService(serviceName = "LojixService",
		name = "LojixService",
		endpointInterface = "br.com.zetex.lojix.api.LojixApi",
		portName = "LojixServicePort",
		targetNamespace = "http://lojix.zetex.com.br")
@Stateless
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LojixWs extends BaseWs implements LojixApi {

	private static final long serialVersionUID = 1L;

	@Inject
	private PerfilFacade perfilFacade;

	@Inject
	private UsuarioFacade usuarioFacade;

	@Override
	@WebMethod
	public Boolean adicionaPerfil(Perfil perfil) throws SOAPException, RemoteException {
		try {
			return perfilFacade.adicionaPerfil(perfil);
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	@WebMethod
	public Boolean adicionaUsuario(Usuario usuario) throws SOAPException {
		try {
			return usuarioFacade.adicionaUsuario(usuario);
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	@WebMethod
	public List<Perfil> recuperaTodosPerfis() throws SOAPException, RemoteException {
		try {
			return perfilFacade.recuperaTodosPerfis();
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}

	@Override
	@WebMethod
	public Boolean verificarLogin(Usuario usuario) throws SOAPException {
		try {
			return usuarioFacade.verificarLogin(usuario);
		} catch (Exception e) {
			throw new SOAPException(e.getMessage(), e.getCause());
		}
	}
}
