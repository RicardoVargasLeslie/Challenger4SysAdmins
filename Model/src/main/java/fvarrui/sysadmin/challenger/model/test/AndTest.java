package fvarrui.sysadmin.challenger.model.test;

import javax.xml.bind.annotation.XmlType;

/**
 * Clase modelo representa un test de tipo 'and'.
 * 
 * @author Fran Vargas
 * @version 1.0
 *
 */
@XmlType
public class AndTest extends TestGroup {

	/**
	 * Constructor por defecto
	 */
	public AndTest() {
		super(null);
	}


	public AndTest(String name, Test... tests) {
		super(name, tests);
	}

	@Override
	public Boolean verify() {
		verified.set(getTests().stream().allMatch(t -> t.verify()));
		return verified.get();
	}

}
