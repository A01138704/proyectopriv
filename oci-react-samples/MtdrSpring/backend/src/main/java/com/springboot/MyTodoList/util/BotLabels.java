package com.springboot.MyTodoList.util;

import javax.ws.rs.DELETE;

public enum BotLabels {

	SHOW_MAIN_SCREEN("Show Main Screen"),
	HIDE_MAIN_SCREEN("Hide Main Screen"),
	LIST_ALL_ITEMS("List All Items"),
	ADD_NEW_ITEM("Add New Item"),
	ADD_NEW_PROJECT("Add New Project"),
	DONE_TASK("TASK DONE"),
	UNDO_TASK("UNDO TASK"),
	DELETE_TASK("DELETE TASK"),
	MY_TODO_LIST("MY TODO LIST"),
	PROJECT_DEVELOPERS("List All Project Developers"),
	PROJECT_TASKS("List All Project Tasks"),
	DELETE_PROJECT("Delete Project"),
	DASH("-");

	private String label;

	BotLabels(String enumLabel) {
		this.label = enumLabel;
	}

	public String getLabel() {
		return label;
	}

}