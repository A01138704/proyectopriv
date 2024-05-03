package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.EmployeeItem;
import com.springboot.MyTodoList.service.EmployeeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EmployeeItemController {
    @Autowired
    private EmployeeItemService employeeItemService;

    /* 
    // @CrossOrigin 
    @GetMapping(value = "/empladodepartamento/{departamento}")
    public List<EmployeeItem> getEmployeeItemByDepartamento(@PathVariable String departamento) {
        return employeeItemService.getEmployeeItemByDepartamento(departamento);
    }

    // @CrossOrigin
    @GetMapping(value = "/empleadoprojecto/{proyecto}")    
    public List<EmployeeItem> getEmployeeItemByProyecto(@PathVariable String proyecto) {
        return employeeItemService.getEmployeeItemByProyecto(proyecto); 
    }
*/
    // @CrossOrigin 
    @GetMapping(value = "/empleadoCorreo/{correo}") 
    public EmployeeItem getEmployeeItemByCorreo(@PathVariable String correo) {
        return employeeItemService.getEmployeeItemByCorreo(correo);
    }çç
    // @CrossOrigin
    @GetMapping(value = "/empleadolist")
    public List<EmployeeItem> getAllToDoItems() {
        return employeeItemService.findAll(); 
    }

    
    // @CrossOrigin
    @GetMapping(value = "/empleadolist/{id}")
    public ResponseEntity<EmployeeItem> getEmployeeItemById(@PathVariable int id) {
        try {
            ResponseEntity<EmployeeItem> responseEntity = employeeItemService.getItemById(id);
            return new ResponseEntity<EmployeeItem>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin
    @PostMapping(value = "/empleadolist")
    public ResponseEntity addEmployeeItem(@RequestBody EmployeeItem employeeItem) throws Exception {
        EmployeeItem td = employeeItemService.addEmployeeItem(employeeItem);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + td.getEmpleadoID());
        responseHeaders.set("Access-Control-Expose-Headers", "location");
        // URI location = URI.create(""+td.getID())

        return ResponseEntity.ok()
                .headers(responseHeaders).build();
    }

    //Get id of the employee by the mail 
    /* *
    @GetMapping(value = "/empleadolist/{correo}")
    public ResponseEntity<EmployeeItem> getEmployeeItemByCorreo(@PathVariable String correo) {
        try {
            ResponseEntity<EmployeeItem> responseEntity = employeeItemService.getItemById(correo);
            return new ResponseEntity<EmployeeItem>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
*/
    // @CrossOrigin
    @PutMapping(value = "empleadolist/{id}")
    public ResponseEntity updateEmployeeItem(@RequestBody EmployeeItem employeeItem, @PathVariable int id) {
        try {
            EmployeeItem employeeItem1 = employeeItemService.updateEmployeeItem(id, employeeItem);
            System.out.println(employeeItem1.toString());
            return new ResponseEntity<>(employeeItem1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin
    @DeleteMapping(value = "empleadolist/{id}")
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
