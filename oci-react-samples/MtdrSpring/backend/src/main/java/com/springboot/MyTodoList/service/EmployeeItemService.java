package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.EmployeeItem;
import com.springboot.MyTodoList.repository.EmployeeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeItemService {

    @Autowired
    private EmployeeItemRepository employeeItemRepository;

    public List<EmployeeItem> findAll() {
        List<EmployeeItem> employeeItems = employeeItemRepository.findAll();
        return employeeItems;
    }

    public ResponseEntity<EmployeeItem> getItemById(int id) {
        Optional<EmployeeItem> employeeData = employeeItemRepository.findById(id);
        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public EmployeeItem addEmployeeItem(EmployeeItem employeeItem) {
        return employeeItemRepository.save(employeeItem);
    }

    public boolean deleteEmployeeItem(int id) {
        try {
            employeeItemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EmployeeItem updateEmployeeItem(int id, EmployeeItem td) {
        Optional<EmployeeItem> employeeItemData = employeeItemRepository.findById(id);
        if (employeeItemData.isPresent()) {
            EmployeeItem employeeItem = employeeItemData.get();
            employeeItem.setEmpleadoID(id);
            employeeItem.setNombre(td.getNombre());
            employeeItem.setApellido(td.getApellido());
            employeeItem.setCorreo(td.getCorreo());
            return employeeItemRepository.save(employeeItem);
        } else {
            return null;
        }
    }

}