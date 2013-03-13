package br.com.zetex.lojix.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.exception.LojixException;

/**
 * <strong>UsuarioDao</strong> possui as funções para gerenciar/manipular a
 * entidade <strong>Usuario</strong>.
 * 
 * @author rabsouza
 * @version 1.0
 * @since 05/05/2011
 * @see {@link CacheDao}
 */
@Named
@ApplicationScoped
public class UsuarioDao extends CacheDao {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor para a classe <strong>UsuarioDao</strong>
	 */
	public UsuarioDao() {
		super();
	}
	
	/**
	 * Contrustor para a classe UsuarioDao
	 * 
	 * @param dao
	 *            BaseDao
	 */
	@Deprecated
	public UsuarioDao(BaseDao dao) {
		super(dao);
	}
	
	@Override
	public Boolean isEmpty() throws LojixException {
		return super.isEmpty(Usuario.class);
	}
	
	@Override
	public List<BaseEntity> selectAll() throws LojixException {
		return super.selectAll(Usuario.class);
	}
	
	@Override
	public List<BaseEntity> selectAll(int maxResults, int firstResult) throws LojixException {
		return super.selectAll(Usuario.class, maxResults, firstResult);
	}
	
	@Override
	public Integer size() throws LojixException {
		return super.size(Usuario.class);
	}
	
}
