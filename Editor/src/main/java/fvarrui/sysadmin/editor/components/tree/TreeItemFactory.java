package fvarrui.sysadmin.editor.components.tree;

import fvarrui.sysadmin.challenger.model.Challenge;
import fvarrui.sysadmin.challenger.model.Goal;
import fvarrui.sysadmin.challenger.model.command.Command;
import fvarrui.sysadmin.challenger.model.test.CommandTest;
import fvarrui.sysadmin.challenger.model.test.Test;
import fvarrui.sysadmin.challenger.model.test.TestGroup;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TreeItem;


/**
 * Logica de negocio para crear los distintos tipos de Items y el arbol inicial de la aplicacion..
 * @author Fran Vargas.
 * @version 1.0
 *
 */
public class TreeItemFactory {
	


	/**
	 * 
	 * @param challenge un chellenge
	 * @return un item
	 */
	
	public static TreeItem<Object> createChallengeTreeItem(Challenge challenge) {
		TreeItem<Object> challengeItem = new TreeItem<Object>();
		challengeItem.setExpanded(true);
		challengeItem.setValue(challenge);
		for (Goal goal : challenge.getGoals()) {
			challengeItem.getChildren().add(createGoalTreeItem(goal));
		}
		challenge.goalsProperty().addListener(new ListChangeListener<Goal>() {
			public void onChanged(Change<? extends Goal> c) {
				while (c.next()) {
					c.getAddedSubList().stream().forEach(g -> challengeItem.getChildren().add(createGoalTreeItem(g)));
					c.getRemoved().stream().forEach(g -> {
						TreeItem<Object> item = challengeItem.getChildren().stream().filter(i -> i.getValue().equals(g)).findFirst().get();
						challengeItem.getChildren().remove(item);
					});
				}
			}
		});
		return challengeItem;
	}
	
	 
	/**
	 * 
	 * @param goal un goal
	 * @return un item de tipo goal
	 */

	public static TreeItem<Object> createGoalTreeItem(Goal goal) {
		TreeItem<Object> goalItem = new TreeItem<Object>();
		goalItem.setExpanded(true);
		goalItem.setValue(goal);
		if (goal.getTest() != null) { 
			goalItem.getChildren().add(createTestTreeItem(goal.getTest()));
		}
		goal.testProperty().addListener((o, ov, nv) -> {
			goalItem.getChildren().clear();
			if (nv != null) {
				goalItem.getChildren().add(createTestTreeItem(nv));
			}
		});
		return goalItem;
	}

	
	/**
	 * 
	 * @param test un test
	 * @return un item de tipo test
	 */
	public static TreeItem<Object> createTestTreeItem(Test test) {
		TreeItem<Object> testItem = new TreeItem<Object>();
		testItem.setExpanded(true);
		testItem.setValue(test);
		if (test instanceof TestGroup) {
			
			TestGroup ct = (TestGroup) test;
			for (Test t : ct.getTests()) {
				testItem.getChildren().add(createTestTreeItem(t));
			}
			ct.testsProperty().addListener(new ListChangeListener<Test>() {
				public void onChanged(Change<? extends Test> c) {
					while (c.next()) {
						c.getAddedSubList().stream().forEach(g -> testItem.getChildren().add(createTestTreeItem(g)));
						c.getRemoved().stream().forEach(g -> {
							TreeItem<Object> item = testItem.getChildren().stream().filter(i -> i.getValue().equals(g)).findFirst().get();
							testItem.getChildren().remove(item);
						});
					}
				}
			});
			
		} else if (test instanceof CommandTest) {
			
			CommandTest ct = (CommandTest) test;
			if (ct.getCommand() != null) {
				testItem.getChildren().add(createCommandTreeItem(ct.getCommand()));
			}
			ct.commandProperty().addListener((o, ov, nv) -> {
				if (nv != null) {
					testItem.getChildren().add(createCommandTreeItem(ct.getCommand()));
				} else {
					testItem.getChildren().clear();
				}
			});
			
		}
		return testItem;
	}

	
   /**
    * 
    * @param command un comando
    * @return un item de tipo comando
    */
	public static TreeItem<Object> createCommandTreeItem(Command command) {
		TreeItem<Object> commandItem = new TreeItem<Object>();
		commandItem.setExpanded(true);
		commandItem.setValue(command);
		return commandItem;
	}
	
}
