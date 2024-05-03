package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.EmployeeItem;
import com.springboot.MyTodoList.model.ProjectItem;
import com.springboot.MyTodoList.model.ToDoItem;
import com.springboot.MyTodoList.service.EmployeeItemService;
import com.springboot.MyTodoList.service.ProjectItemService;
import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotHelper;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoItemBotController extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(ToDoItemBotController.class);

    @Autowired
    private ToDoItemService toDoItemService;

    @Autowired
    private ProjectItemService projectItemService;

    @Autowired
    private EmployeeItemService employeeItemService;

    private String botName;

    public ToDoItemBotController(String botToken, String botName, ToDoItemService toDoItemService, ProjectItemService projectItemService, EmployeeItemService employeeItemService) {
        super(botToken);
        logger.info("Bot Token: " + botToken);
        logger.info("Bot name: " + botName);
        this.toDoItemService = toDoItemService;
        this.projectItemService = projectItemService;
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            logger.error("Update, message or text is null");
            String messageTextFromTelegram = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            logger.debug("Processing message from Telegram: " + messageTextFromTelegram);

    
            if (messageTextFromTelegram.equals(BotCommands.START_COMMAND.getCommand()) // this is the command to start the bot
                    || messageTextFromTelegram.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) { // this is the label to show the main screen
                // this is the message that the bot will send to the user
                SendMessage messageToTelegram = new SendMessage();
                // this is the chat id of the user
                messageToTelegram.setChatId(chatId);
                // this is the message that the bot will send to the user
                messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());
                //messageToTelegram.setText("Please enter your email address:");
                //newItem.setNombre(messageTextFromTelegram);

                try {
                    //this is the method that sends the message to the user the method is called execute and the parameter is the message
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
    
            } else { // this is the case when the user sends an email address but
                // Process the user's email
                String userEmail = messageTextFromTelegram;
                //print the email address 
                System.out.println("Email address: " + userEmail);
                // Find the employee by email using the EmployeeItemService
                EmployeeItem employee = employeeItemService.getEmployeeItemByCorreo(messageTextFromTelegram);
          
                //logger.warn("Employee: " + employee);

                
                if (employee != null) {
                    // Employee found, proceed with further actions
                    logger.error("No employee found for email: " + messageTextFromTelegram);
                    SendMessage messageToTelegram = new SendMessage();
                    messageToTelegram.setChatId(chatId);
                    messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());
                    //this part is for the keyboard options 
                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    // Create a list of keyboard rows
                    List<KeyboardRow> keyboard = new ArrayList<>();
    
                    // Add keyboard options based on employee's role and permissions
                    // ...
                    // Create a new keyboard row
                    keyboardMarkup.setKeyboard(keyboard);
                    messageToTelegram.setReplyMarkup(keyboardMarkup);
    
                    try {
                        execute(messageToTelegram);
                    } catch (TelegramApiException e) {
                        logger.error(e.getLocalizedMessage(), e);
                        logger.error("Failed to send message on Telegram", e);

                    }
                } else {
                    // Employee not found, handle the case
                    SendMessage messageToTelegram = new SendMessage();
                    messageToTelegram.setChatId(chatId);
                    messageToTelegram.setText("Employee not found. Please enter a valid email address.");
    
                    try {
                        execute(messageToTelegram);
                    } catch (TelegramApiException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    }
                }
            }
        }
    }

            /*else { 
            String userEmail = messageTextFromTelegram;
            // Process the user's email and perform necessary actions
            // ...
            SendMessage emailReceivedMessage = new SendMessage();
            emailReceivedMessage.setChatId(chatId);
            emailReceivedMessage.setText("Thank you! Your email has been received.");
            setUserState(chatId, "MAIN_MENU");
            try {
                execute(emailReceivedMessage);
                showMainMenu(chatId);
            } catch (TelegramApiException e) {
                logger.error(e.getLocalizedMessage(), e);
            }
            break; 
        }

        }
    }
}*/
       
        /* 
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageTextFromTelegram = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageTextFromTelegram.equals(BotCommands.START_COMMAND.getCommand())
                    || messageTextFromTelegram.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) {

                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);
                messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                row.add(BotLabels.LIST_ALL_ITEMS.getLabel());
                row.add(BotLabels.ADD_NEW_ITEM.getLabel());
                keyboard.add(row);

                row = new KeyboardRow();
                row.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
                row.add(BotLabels.HIDE_MAIN_SCREEN.getLabel());
               // row.add(BotLabels.ADD_NEW_PROJECT.getLabel());
                keyboard.add(row);

                keyboardMarkup.setKeyboard(keyboard);

                messageToTelegram.setReplyMarkup(keyboardMarkup);

                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }

            } else if (messageTextFromTelegram.indexOf(BotLabels.DONE_TASK.getLabel()) != -1) {
                String done = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));
                Integer id = Integer.valueOf(done);

                try {
                    ToDoItem item = getToDoItemById(id).getBody();
                    item.setEstatus(true);
                    updateToDoItem(item, id);
                    BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DONE.getMessage(), this);
					//Log in concole Chat id in red color 
					System.out.println("\u001B[31m" + "Chat id: " + chatId);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }

            } else if (messageTextFromTelegram.indexOf(BotLabels.UNDO_TASK.getLabel()) != -1) {
                String undo = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));
                Integer id = Integer.valueOf(undo);

                try {
                    ToDoItem item = getToDoItemById(id).getBody();
                    item.setEstatus(false);
                    updateToDoItem(item, id);
                    BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_UNDONE.getMessage(), this);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }

            } else if (messageTextFromTelegram.indexOf(BotLabels.DELETE_TASK.getLabel()) != -1) {
                String delete = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));
                Integer id = Integer.valueOf(delete);

                try {
                    deleteToDoItem(id).getBody();
                    BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), this);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }

            } else if (messageTextFromTelegram.equals(BotCommands.HIDE_COMMAND.getCommand())
                    || messageTextFromTelegram.equals(BotLabels.HIDE_MAIN_SCREEN.getLabel())) {

                BotHelper.sendMessageToTelegram(chatId, BotMessages.BYE.getMessage(), this);

            } else if (messageTextFromTelegram.equals(BotCommands.TODO_LIST.getCommand())
                    || messageTextFromTelegram.equals(BotLabels.LIST_ALL_ITEMS.getLabel())
                    || messageTextFromTelegram.equals(BotLabels.MY_TODO_LIST.getLabel())) {

                List<ToDoItem> allItems = getAllToDoItems();
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow mainScreenRowTop = new KeyboardRow();
                mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
                keyboard.add(mainScreenRowTop);

                KeyboardRow firstRow = new KeyboardRow();
                firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
                keyboard.add(firstRow);

                KeyboardRow myTodoListTitleRow = new KeyboardRow();
                myTodoListTitleRow.add(BotLabels.MY_TODO_LIST.getLabel());
                keyboard.add(myTodoListTitleRow);

                List<ToDoItem> activeItems = allItems.stream().filter(item -> item.getEstatus() == false)
                        .collect(Collectors.toList());

                for (ToDoItem item : activeItems) {
                    KeyboardRow currentRow = new KeyboardRow();
                    currentRow.add(item.getDescripcion());
                    currentRow.add(item.getTareaID() + BotLabels.DASH.getLabel() + BotLabels.DONE_TASK.getLabel());
                    keyboard.add(currentRow);
                }

                List<ToDoItem> doneItems = allItems.stream().filter(item -> item.getEstatus() == true)
                        .collect(Collectors.toList());

                for (ToDoItem item : doneItems) {
                    KeyboardRow currentRow = new KeyboardRow();
                    currentRow.add(item.getDescripcion());
                    currentRow.add(item.getTareaID() + BotLabels.DASH.getLabel() + BotLabels.UNDO_TASK.getLabel());
                    currentRow.add(item.getTareaID() + BotLabels.DASH.getLabel() + BotLabels.DELETE_TASK.getLabel());
                    keyboard.add(currentRow);
                }

                KeyboardRow mainScreenRowBottom = new KeyboardRow();
                mainScreenRowBottom.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
                keyboard.add(mainScreenRowBottom);

                keyboardMarkup.setKeyboard(keyboard);

                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(chatId);
                messageToTelegram.setText(BotLabels.MY_TODO_LIST.getLabel());
                messageToTelegram.setReplyMarkup(keyboardMarkup);

                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            } else if (messageTextFromTelegram.equals(BotCommands.ADD_ITEM.getCommand())
                    || messageTextFromTelegram.equals(BotLabels.ADD_NEW_ITEM.getLabel())) {
                try {
                    SendMessage messageToTelegram = new SendMessage();
                    messageToTelegram.setChatId(chatId);
                    messageToTelegram.setText(BotMessages.TYPE_NEW_TODO_ITEM.getMessage());
                    ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove(true);
                    messageToTelegram.setReplyMarkup(keyboardMarkup);
                    execute(messageToTelegram);
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
				
            } else {
                try {
					
                    ToDoItem newItem = new ToDoItem();
                    newItem.setNombre(messageTextFromTelegram);
                    newItem.setDescripcion(messageTextFromTelegram);
                    newItem.setFechaCreacion(OffsetDateTime.now());
                    newItem.setEstatus(false);
                    newItem.setFechaLimite(OffsetDateTime.now());
                    newItem.setTipoTarea(messageTextFromTelegram);
                    ResponseEntity entity = addToDoItem(newItem);
					
                    SendMessage messageToTelegram = new SendMessage();
                    messageToTelegram.setChatId(chatId);
                    messageToTelegram.setText(BotMessages.NEW_ITEM_ADDED.getMessage());

                    execute(messageToTelegram);

                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }
    */
    
   /*/ private void showMainMenu(long chatId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showMainMenu'");
    }

    private void setUserState(long chatId, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUserState'");
    }

    private String getUserState(long chatId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserState'");
    }*/

    @Override
    public String getBotUsername() {
        return botName;
    }
 
    // ToDo Item methods

    public List<ToDoItem> getAllToDoItems() {
        return toDoItemService.findAll();
    }

    public ResponseEntity<ToDoItem> getToDoItemById(@PathVariable int id) {
        try {
            ResponseEntity<ToDoItem> responseEntity = toDoItemService.getItemById(id);
            return new ResponseEntity<ToDoItem>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity addToDoItem(@RequestBody ToDoItem todoItem) throws Exception {
        ToDoItem td = toDoItemService.addToDoItem(todoItem);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + td.getTareaID());
        responseHeaders.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    public ResponseEntity updateToDoItem(@RequestBody ToDoItem toDoItem, @PathVariable int id) {
        try {
            ToDoItem toDoItem1 = toDoItemService.updateToDoItem(id, toDoItem);
            System.out.println(toDoItem1.toString());
            return new ResponseEntity<>(toDoItem1, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> deleteToDoItem(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = toDoItemService.deleteToDoItem(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }

   
    // Project-related methods
	// empleado correo 

    public List<ProjectItem> getAllProjectItems() {
        return projectItemService.findAll();
    }

    public ResponseEntity<ProjectItem> getProjectItemById(int id) {
        try {
            ResponseEntity<ProjectItem> responseEntity = projectItemService.getProjectItemById(id);
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
		
    public ResponseEntity addProjectItem(@RequestBody ProjectItem projectItem) throws Exception {
        ProjectItem proj = projectItemService.addProjectItem(projectItem);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + proj.getProyectoID());
        responseHeaders.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    public ResponseEntity updateProjectItem(@RequestBody ProjectItem projectItem, @PathVariable int id) {
        try {
            ProjectItem projectItem1 = projectItemService.updateProjectItem(id, projectItem);
            System.out.println(projectItem1.toString());
            return new ResponseEntity<>(projectItem1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Boolean> deleteProjectItem(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = projectItemService.deleteProjectItem(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
/* 
    // Employee-related methods
	 public List<EmployeeItem> getEmployeeItemByCorreo(String correo) {
		return employeeItemService.getEmployeeItemByCorreo(correo);
	}

	//check if employeecorreo is manager or not if employeecorreo is manager then only show getEmployeeItemByProyecto  
	
	public List<EmployeeItem> getEmployeeItemByProyecto(String proyecto) {
		return employeeItemService.getEmployeeItemByProyecto(proyecto);
	}

	public List<EmployeeItem> getEmployeeItemByDepartamento(String departamento) {
		return employeeItemService.getEmployeeItemByDepartamento(departamento);
	}*/
	public List<EmployeeItem> getAllEmployeeItems() {
		return employeeItemService.findAll();  
	}

	public ResponseEntity<EmployeeItem> getEmployeeItemById(int id) {
		try {
			ResponseEntity<EmployeeItem> responseEntity = employeeItemService.getItemById(id); 
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity addEmployeeItem(@RequestBody EmployeeItem employeeItem) throws Exception {
		EmployeeItem emp = employeeItemService.addEmployeeItem(employeeItem);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("location", "" + emp.getEmpleadoID());
		responseHeaders.set("Access-Control-Expose-Headers", "location");
		return ResponseEntity.ok().headers(responseHeaders).build();
	}

	public ResponseEntity updateEmployeeItem(@RequestBody EmployeeItem employeeItem, @PathVariable int id) {
		try {
			EmployeeItem employeeItem1 = employeeItemService.updateEmployeeItem(id, employeeItem);
			System.out.println(employeeItem1.toString());
			return new ResponseEntity<>(employeeItem1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Boolean> deleteEmployeeItem(@PathVariable("id") int id) {
		Boolean flag = false;
		try {
			flag = employeeItemService.deleteEmployeeItem(id);
			return new ResponseEntity<>(flag, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
		}
	}


}