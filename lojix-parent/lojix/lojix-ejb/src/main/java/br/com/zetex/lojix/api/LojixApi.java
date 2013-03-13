package br.com.zetex.lojix.api;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;

import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.entity.Usuario;

/**
 * LojixApi classe que possui todos os métodos expostos via ws/ejb.
 * 
 * @author rabsouza
 * @version 1.0.0
 * @since 11/03/2013
 * @see Serializable
 * 
 */
@WebService(
		name = "LojixServiceType",
		targetNamespace = "http://lojix.zetex.com.br")
@Remote
public interface LojixApi extends Serializable {
	
	/**
	 * @author rabsouza
	 * 
	 * @param perfil
	 *            Dados do perfil.
	 * @return true - perfil adicionar com sucesso/ false - erro ao tentar adicionar um perfil.
	 * @throws SOAPException
	 *             Exceção do soap
	 * @throws RemoteException
	 *             Exceção do ejb.
	 * @see br.com.zetex.lojix.facade.PerfilFacade#adicionaPerfil(br.com.zetex.lojix.entity.Perfil)
	 */
	@WebMethod
	public Boolean adicionaPerfil(Perfil perfil) throws SOAPException, RemoteException;
	
	/**
	 * @author rabsouza
	 * 
	 * @param usuario
	 *            Dados do usuário.
	 * @return true - usuário adicionar com sucesso/ false - erro ao tentar adicionar um usuário.
	 * @throws SOAPException
	 *             Exceção do soap
	 * @throws RemoteException
	 *             Exceção do ejb.
	 * @see br.com.zetex.lojix.facade.UsuarioFacade#adicionaUsuario(br.com.zetex.lojix.entity.Usuario)
	 */
	@WebMethod
	public Boolean adicionaUsuario(Usuario usuario) throws SOAPException, RemoteException;
	
	/**
	 * @author rabsouza
	 * 
	 * @return lista com todos os perfis.
	 * @throws SOAPException
	 *             Exceção do soap
	 * @throws RemoteException
	 *             Exceção do ejb.
	 * @see br.com.zetex.lojix.facade.PerfilFacade#recuperaTodosPerfis()
	 */
	public List<Perfil> recuperaTodosPerfis() throws SOAPException, RemoteException;
	
	/**
	 * @author rabsouza
	 * 
	 * @param usuario
	 *            Dados do usuário.
	 * @return true - usuário logado com sucesso/ false - falha ao logar usuário.
	 * @throws SOAPException
	 *             Exceção do soap
	 * @throws RemoteException
	 *             Exceção do ejb.
	 * @see br.com.zetex.lojix.facade.UsuarioFacade#verificarLogin(br.com.zetex.lojix.entity.Usuario)
	 */
	@WebMethod
	public Boolean verificarLogin(Usuario usuario) throws SOAPException, RemoteException;
	
}
