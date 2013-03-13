package br.com.zetex.lojix.test.dao;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import br.com.zetex.lojix.dao.UsuarioDao;
import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.entity.Contato;
import br.com.zetex.lojix.entity.Endereco;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.entity.PessoaFisica;
import br.com.zetex.lojix.entity.TipoContato;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.entity.enumeration.EstadoCivilEnum;
import br.com.zetex.lojix.entity.enumeration.IdiomaEnum;
import br.com.zetex.lojix.entity.enumeration.PermissaoEnum;
import br.com.zetex.lojix.entity.enumeration.PrioridadeEnum;
import br.com.zetex.lojix.entity.enumeration.SexoEnum;
import br.com.zetex.lojix.entity.enumeration.TipoEnderecoEnum;
import br.com.zetex.lojix.entity.enumeration.TipoLogradouroEnum;
import br.com.zetex.lojix.entity.enumeration.UFEnum;
import br.com.zetex.lojix.exception.LojixException;

@SuppressWarnings("javadoc")
public class UsuarioDaoTest extends BaseTestDao {

	@SuppressWarnings("deprecation")
	private UsuarioDao dao = new UsuarioDao(getBaseDao());

	private Perfil perfil = new Perfil();
	private TipoContato tipoContato = new TipoContato();

	private Usuario usuario;

	@Test
	public void executaQuerySelectAll() throws LojixException {
		assertTrue(CollectionUtils.isEmpty(dao.selectAll()));
	}

	@Test
	public void adicionaUsuarioComDadosObrigatorio() throws LojixException, CreateEntityException{
		inicializaDependenciaPerfil();

		PessoaFisica pessoa = new PessoaFisica();
		pessoa.setNome("Rafael");
		pessoa.setCpf("123.456.789-00");
		pessoa.setDataNascimento(new Date());
		pessoa.setSexo(SexoEnum.M);

		usuario = new Usuario(pessoa);
		usuario.setEmail("eskatos@yopmail.com");
		usuario.setNome("teste");
		usuario.setSenha("12345");
		usuario.setPerfil(perfil);

		dao.persist(usuario);
		validaContemEntity(usuario);

		printEntity(usuario);

		remove(usuario);
		limpaDependenciaPerfil();
	}

	@Test
	public void adicionaUsuarioComTodosDados() throws LojixException, CreateEntityException{
		inicializaDependenciaPerfil();
		inicializaDependenciaTipoContato();

		Contato contato = new Contato();
		contato.setTipo(tipoContato);
		contato.setValor("(31)8888-9999");

		Endereco endereco = new Endereco();
		endereco.setTipoLogradouro(TipoLogradouroEnum.RUA);
		endereco.setLogradoudo("João Catarina");
		endereco.setNumero(422);
		endereco.setComplemento("Casa A");
		endereco.setBairro("Centro");
		endereco.setCidade("Ouro Branco");
		endereco.setUf(UFEnum.MG);
		endereco.setCep("36.420-000");
		endereco.setPais("BRASIL");
		endereco.setDescricao("Casa");
		endereco.setPadrao(true);
		endereco.setStatus(true);
		endereco.setTipoEndereco(TipoEnderecoEnum.RESIDENCIAL);

		PessoaFisica pessoa = new PessoaFisica();
		pessoa.setNome("Rafael");
		pessoa.setCpf("123.456.789-00");
		pessoa.setDataNascimento(new Date());
		pessoa.setSexo(SexoEnum.M);
		pessoa.setEstadoCivil(EstadoCivilEnum.SOLTEIRO);
		pessoa.setInformacao("Desenvolvedor do sistema");
		pessoa.setNomeMae("Marli");
		pessoa.setNomePai("Jorge");

		//pessoa -> contato
		pessoa.add(contato);

		// pessoa -> endereco
		pessoa.add(endereco);

		usuario = new Usuario(pessoa);
		usuario.setEmail("eskatos@yopmail.com");
		usuario.setNome("teste");
		usuario.setSenha("12345");
		usuario.setPerfil(perfil);
		usuario.setCodigoAtivacao("1234567890");
		usuario.setDataAtivacao(Calendar.getInstance().getTime());
		usuario.setIdioma(IdiomaEnum.PT_BR);
		usuario.setStatus(true);

		dao.persist(usuario);
		validaContemEntity(usuario);

		printEntity(usuario);

		remove(usuario);
		limpaDependenciaPerfil();
		limpaDependenciaTipoContato();
	}

	public void inicializaDependenciaPerfil() {
		perfil.setNome("ADMIN");
		perfil.setPermissao(PermissaoEnum.ADMIN);
		perfil.setPrioridade(PrioridadeEnum.ALTA);
		perfil.setStatus(Boolean.TRUE);
		persist(perfil);

	}

	public void inicializaDependenciaTipoContato() {
		tipoContato.setDescricao("Tipo de contato 1");
		tipoContato.setNome("TELEFONE");
		tipoContato.setPadrao(true);
		tipoContato.setParttenValidacao("(\\d\\d)\\d\\d\\d\\d-\\d\\d\\d\\d");
		persist(tipoContato);

	}

	public void limpaDependenciaPerfil() {
		remove(perfil);
	}

	public void limpaDependenciaTipoContato() {
		remove(tipoContato);
	}

}
