package fvarrui.sysadmin.challenger.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

import fvarrui.sysadmin.challenger.model.test.Test;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Clase Modelo que representa un objetivo
 * @author Fran Vargas,Ricardo Vargas
 * @version 1.0
 * 
 */
@XmlType
public class Goal {

	private StringProperty name;
	private StringProperty description;
	private ObjectProperty<Test> test;

	@XmlTransient
	private ReadOnlyBooleanWrapper achieved;

	/**
	 * Constructor por defecto
	 */
	public Goal() {
		this(null);
	}

	/**
	 * Constructor
	 * @param name nombre del objetivo
	 */
	public Goal(String name) {
		this(name, null);
	}


	/**
	 * 
	 * @param name nombre del objetivo
	 * @param description del objetivo
	 */
	public Goal(String name, String description) {
		super();
		this.name = new SimpleStringProperty(this, "name", name);
		this.description = new SimpleStringProperty(this, "description", description);
		this.test = new SimpleObjectProperty<>(this, "test");
		this.achieved = new ReadOnlyBooleanWrapper(this, "achieved", false);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	@XmlID
	@XmlAttribute
	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty descriptionProperty() {
		return this.description;
	}

	@XmlElement
	public final String getDescription() {
		return this.descriptionProperty().get();
	}

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}

	public final ObjectProperty<Test> testProperty() {
		return this.test;
	}

	@XmlElement
	public final Test getTest() {
		return this.testProperty().get();
	}

	public final void setTest(final Test test) {
		this.testProperty().set(test);
	}

	public final ReadOnlyBooleanProperty achievedProperty() {
		return this.achieved.getReadOnlyProperty();
	}
	
	public final boolean isAchieved() {
		return this.achievedProperty().get();
	}
	
	/**
	 * metodo que comprueba que se haya completado un objetivo
	 * @return si el objetivo se a alcanzado
	 */
	public boolean verify() {
		if (!isAchieved()) {
			this.achieved.set(getTest().verify());
		}
		return isAchieved();
	}
	
	public String toString(int spaces) {
		return StringUtils.repeat("-", spaces) + " (" + (achieved.get() ? "+" : "-") + ") [goal] " + getDescription() + ((getTest() != null ) ? "\n" + getTest().toString(11) : "");
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
