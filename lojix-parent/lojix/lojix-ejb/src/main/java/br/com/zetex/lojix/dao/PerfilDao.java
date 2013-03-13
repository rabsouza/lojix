package br.com.zetex.lojix.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.exception.LojixException;

/**
 * <strong>PerfilDao</strong> possui as funções para gerenciar/manipular a
 * entidade <strong>Perfil</strong>.
 *
 * @author rabsouza
 * @version 1.0
 * @since 05/05/2011
 * @see {@link CacheDao}
 */
@Named
@ApplicationScoped
public class PerfilDao extends CacheDao {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para a classe <strong>PerfilDao</strong>
	 */
	public PerfilDao() {
		super();
	}

	/**
	 * Contrustor para a classe PerfilDao
	 *
	 * @param dao
	 *            BaseDao
	 */
	@Deprecated
	public PerfilDao(BaseDao dao) {
		super(dao);
	}

	@Override
	public Boolean isEmpty() throws LojixException {
		return super.isEmpty(Perfil.class);
	}

	@Override
	public List<BaseEntity> selectAll() throws LojixException {
		return super.selectAll(Perfil.class);
	}

	@Override
	public List<BaseEntity> selectAll(int maxResults, int firstResult) throws LojixException {
		return super.selectAll(Perfil.class, maxResults, firstResult);
	}

	@Override
	public Integer size() throws LojixException {
		return super.size(Perfil.class);
	}

}
