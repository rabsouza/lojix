package br.com.zetex.lojix.test.util;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.entity.BaseEntityImpl;
import br.com.zetex.lojix.entity.Perfil;
import br.com.zetex.lojix.entity.PessoaFisica;
import br.com.zetex.lojix.entity.Usuario;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.exception.LojixRuntimeException;
import br.com.zetex.lojix.test.entity.TesteEntity;
import br.com.zetex.lojix.util.Util;

@SuppressWarnings("javadoc")
@RunWith(value = Enclosed.class)
public class UtilTest {

	@RunWith(value = Enclosed.class)
	public static class TesteToStringToXmlToMap {

		public static class ClassTeste {

			@Test
			public void retornaToStringParaTesteEntity() {
				TesteEntity teste = new TesteEntity();
				System.out.println(teste.toString());
				assertTrue(StringUtils.isNotBlank(teste.toString()));
			}

			@Test
			public void retornaToMapParaTesteEntity() {
				TesteEntity teste = new TesteEntity();
				System.out.println(teste.toMap());
				assertTrue(MapUtils.isNotEmpty(teste.toMap()));
			}

			@Test
			public void retornaToXmlParaTesteEntity() {
				TesteEntity teste = new TesteEntity();
				System.out.println(teste.toXml());
				assertTrue(StringUtils.isNotBlank(teste.toXml()));
			}
		}

		public static class ClassBaseEntity {

			@Test
			public void retornaToStringParaBaseEntityImpl() {
				BaseEntity teste = new BaseEntityImpl();
				System.out.println(teste.toString());
				assertTrue(StringUtils.isNotBlank(teste.toString()));
			}

			@Test
			public void retornaToMapParaBaseEntityImpl() throws LojixException {
				BaseEntity teste = new BaseEntityImpl();
				System.out.println(teste.toMap());
				assertTrue(MapUtils.isNotEmpty(teste.toMap()));
			}

			@Test
			public void retornaToXmlParaBaseEntityImpl() throws LojixException {
				BaseEntity teste = new BaseEntityImpl();
				System.out.println(teste.toXml());
				assertTrue(StringUtils.isNotBlank(teste.toXml()));
			}
		}

		public static class ObjNulo {

			@Test
			public void retornaToStringParaObjNulo() {
				assertTrue(StringUtils.isBlank(Util.toString(null)));
			}

			@Test
			public void retornaToMapParaObjNulo() {
				assertTrue(MapUtils.isEmpty(Util.toMap(null)));
			}

			@Test
			public void retornaToXmlParaObjNulo() {
				assertTrue(StringUtils.isNotBlank(Util.toXml(null)));
			}
		}

		public static class ClassPerfil {

			@Test
			public void retornaToStringParaPerfil() {
				Perfil perfil = new Perfil();
				System.out.println(perfil.toString());
				assertTrue(StringUtils.isNotBlank(perfil.toString()));
			}

			@Test
			public void retornaToMapParaPerfil() {
				Perfil perfil = new Perfil();
				System.out.println(perfil.toMap());
				assertTrue(MapUtils.isNotEmpty(perfil.toMap()));
			}

			@Test
			public void retornaToXmlParaPerfil() {
				Perfil perfil = new Perfil();
				System.out.println(perfil.toXml());
				assertTrue(StringUtils.isNotBlank(perfil.toXml()));
			}
		}

		public static class ClassUsuario {

			@Test
			public void retornaToStringParaUsuario() {
				Usuario usuario = getPessoa();
				System.out.println(usuario.toString());
				assertTrue(StringUtils.isNotBlank(usuario.toString()));
			}

			public Usuario getPessoa() {
				PessoaFisica pessoa = new PessoaFisica();
				Usuario usuario = new Usuario(pessoa);
				usuario.setPerfil(new Perfil());
				return usuario;
			}

			@Test
			public void retornaToMapParaUsuario() {
				Usuario usuario = getPessoa();
				System.out.println(usuario.toMap());
				assertTrue(MapUtils.isNotEmpty(usuario.toMap()));
			}

			@Test
			public void retornaToXmlParaUsuario() {
				Usuario usuario = getPessoa();
				System.out.println(usuario.toXml());
				assertTrue(StringUtils.isNotBlank(usuario.toXml()));
			}
		}
	}

	@RunWith(value = Enclosed.class)
	public static class TesteFill {

		public static class ClassTeste {

			@Test
			public void preencheMapParaTesteEntity() throws LojixException {
				TesteEntity teste = new TesteEntity();
				new TesteEntity(teste.toMap());
			}
		}

		public static class ClassBaseEntity {

			@Test
			public void preencheMapParaBaseEntityImpl() throws LojixException {
				BaseEntity teste = new BaseEntityImpl();
				new BaseEntityImpl(teste.toMap());
			}
		}

		public static class ObjNulo {

			@Test(expected = LojixException.class)
			public void preencheMapParaBaseEntityNulo() throws LojixException {
				BaseEntity teste = null;
				Map<String, Object> map = new HashMap<String, Object>();
				Util.fillBeanFromMap(map, teste);
			}

			@Test(expected = LojixException.class)
			public void preencheMapParaMapNulo() throws LojixException {
				BaseEntity teste = new BaseEntityImpl();
				Map<String, Object> map = null;
				Util.fillBeanFromMap(map, teste);
			}

			@Test(expected = LojixException.class)
			public void preencheMapParaObjNulo() throws LojixException {
				Map<String, Object> map = new HashMap<String, Object>();
				Util.fillBeanFromMap(map, null);
			}

			@Test(expected = LojixException.class)
			public void preencheMapParaObjNuloMapNulo() throws LojixException {
				Util.fillBeanFromMap(null, null);
			}

		}

		public static class ClassPerfil {

			@Test
			public void preencheMapParaPerfil() throws LojixException {
				Perfil perfil = new Perfil();
				new Perfil(perfil.toMap());
			}

		}

		public static class ClassUsuario {

			public Usuario getPessoa() {
				PessoaFisica pessoa = new PessoaFisica();
				Usuario usuario = new Usuario(pessoa);
				usuario.setPerfil(new Perfil());
				return usuario;
			}

			@Test
			public void preencheMapParaUsuario() throws LojixException {
				Usuario usuario = getPessoa();
				new Usuario(usuario.toMap());
			}
		}
	}

	public static class TesteConvertList {

		@Test
		public void converteListaParaTipoDefinido() {
			Perfil perfil = new Perfil();
			perfil.setNome("perfil");

			List<BaseEntity> listaSeraConvertida = new ArrayList<BaseEntity>();
			listaSeraConvertida.add(perfil);

			List<Perfil> listaConvertida = Util.convertList(listaSeraConvertida, Perfil.class);
			assertTrue(CollectionUtils.isNotEmpty(listaConvertida));
			assertTrue(StringUtils.isNotBlank(listaConvertida.get(0).getNome()));
		}

		@Test(expected = LojixRuntimeException.class)
		public void recebeExcecaoParaTipoErrado() {
			Perfil perfil = new Perfil();
			perfil.setNome("perfil");

			List<BaseEntity> listaSeraConvertida = new ArrayList<BaseEntity>();
			listaSeraConvertida.add(perfil);

			Util.convertList(listaSeraConvertida, Usuario.class);
		}

		@Test
		public void retornaVazioParaListaVazia() {
			List<BaseEntity> listaSeraConvertida = new ArrayList<BaseEntity>();

			List<Perfil> listaConvertida = Util.convertList(listaSeraConvertida, Perfil.class);
			assertTrue(CollectionUtils.isEmpty(listaConvertida));
		}

		@Test
		public void retornaNuloParaTipoNulo() {
			assertNull(Util.convertList(new ArrayList<BaseEntity>(), null));
		}

		@Test
		public void retornaNuloParaListaNula() {
			assertNull(Util.convertList(null, BaseEntity.class));
		}

		@Test
		public void retornaNuloParaListaETipoNula() {
			assertNull(Util.convertList(null, null));
		}

	}

}
