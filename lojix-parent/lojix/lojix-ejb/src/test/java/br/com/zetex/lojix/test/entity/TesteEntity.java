package br.com.zetex.lojix.test.entity;

import java.util.Map;

import br.com.zetex.lojix.entity.BaseEntityImpl;
import br.com.zetex.lojix.exception.LojixException;

@SuppressWarnings("javadoc")
public class TesteEntity extends BaseEntityImpl {

	private static final long serialVersionUID = 1L;

	private Object obj;

	public TesteEntity() {
		super();
	}

	public TesteEntity(Map<String, Object> map) throws LojixException {
		super(map);
	}

	private Boolean teste;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void getA() {
		getInnerA();
	}

	public String getB() {
		return null;
	}

	private void getInnerA() {

	}

	public Boolean isTeste() {
		return teste;
	}

	public void setTeste(Boolean teste) {
		this.teste = teste;
	}

}
