package fvarrui.sysadmin.editor.components;

import fvarrui.sysadmin.challenger.Challenge;
import fvarrui.sysadmin.challenger.Goal;
import fvarrui.sysadmin.challenger.command.Command;
import fvarrui.sysadmin.challenger.test.Test;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

<<<<<<< HEAD
=======

/**
 * Logica de negocio para crear la celda correspondiente segun el objeto selecionado.
 * @author Fran Vargas
 * @version 1.0
 *
 */
>>>>>>> 7afdb606eb5ff55c3880a5f4dbdef57dacedc15a
public class CustomTreeCell extends TreeCell<Object> {

	private static final Image CHALLENGE = new Image("/fvarrui/sysadmin/editor/ui/resources/challenge-32x32.png");
	private static final Image GOAL = new Image("/fvarrui/sysadmin/editor/ui/resources/goal-32x32.png");
	private static final Image TEST = new Image("/fvarrui/sysadmin/editor/ui/resources/test-32x32.png");
	private static final Image COMMAND = new Image("/fvarrui/sysadmin/editor/ui/resources/command-32x32.png");

	private ImageView imageView = new ImageView();
	
	@Override
	protected void updateItem(Object item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText("");
			setGraphic(null);
		} else {
			setGraphic(imageView);
			if (item instanceof Challenge) {
				setText(((Challenge) item).getName());
				imageView.setImage(CHALLENGE);
			} else if (item instanceof Goal) {
				setText(((Goal) item).getName());
				imageView.setImage(GOAL);
			} else if (item instanceof Test) {
				setText(((Test) item).getName());
				imageView.setImage(TEST);
			} else if (item instanceof Command) {
				setText(((Command) item).getCommand());
				imageView.setImage(COMMAND);
			}
			
		}
		
	}
	
}
