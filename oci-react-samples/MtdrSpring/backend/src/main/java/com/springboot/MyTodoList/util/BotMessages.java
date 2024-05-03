package com.springboot.MyTodoList.util;

public enum BotMessages {

	HELLO_MYTODO_BOT(
			"¡Bienvenido al Chat Bot de Oracle! \nEscribe tu correo:"),
	INITIAL_MESSAGE("Escribe una nueva tarea a continuación y envia el boton o selecciona una opción a continuacion:"),

	BOT_REGISTERED_STARTED("¡Se registró correctamente el Bot!"),
	ITEM_DONE(
			"¡Tarea lista! Selecciona /todolist para desplegar todas las tareas o selecciona /start para ir a la pantalla principal."),
	ITEM_UNDONE(
			"¡Tarea deshecha! Selecciona /todolist para desplegar todas las tareas o selecciona /start para ir a la pantalla principal."),
	ITEM_DELETED(
			"¡Tarea eliminada! Selecciona /todolist para desplegar todas las tareas o selecciona /start para ir a la pantalla principal."),
	TYPE_NEW_TODO_ITEM("Escribe una nueva tarea y presiona el botón para agregarla a la lista de tareas."),
	NEW_ITEM_ADDED(
			"¡Nueva tarea agregada! Selecciona /todolist para desplegar todas las tareas o selecciona /start para ir a la pantalla principal."),
	NEW_PROJECT_ADDED(
			"¡Nuevo proyecto agregado! Selecciona /start para ir a la pantalla principal."),
	PROJECT_DONE("¡Proyecto listo! Selecciona /start para ir a la pantalla principal."),
	PROJECT_UNDONE("¡Proyecto deshecho! Selecciona /start para ir a la pantalla principal."),
	PROJECT_DELETED("¡Proyecto eliminado! Selecciona /start para ir a la pantalla principal."),
	EMPLOYEE_PROJECT("Visualiza los empleados del proyecto. Selecciona /start para ir a la pantalla principal."),
	BYE("¡Hasta pronto! Selecciona /start para empezar de nuevo.");

	private String message;

	BotMessages(String enumMessage) {
		this.message = enumMessage;
	}

	public String getMessage() {
		return message;
	}

}