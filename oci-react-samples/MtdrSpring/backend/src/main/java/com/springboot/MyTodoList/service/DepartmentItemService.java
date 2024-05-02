package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.DepartmentItem;
import com.springboot.MyTodoList.repository.DepartmentItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentItemService {

    @Autowired
    private DepartmentItemRepository departmentItemRepository;

    public List<DepartmentItem> findAll() {
        List<DepartmentItem> departmentItems = departmentItemRepository.findAll();
        return departmentItems;
    }

    public ResponseEntity<DepartmentItem> getItemById(int id) {
        Optional<DepartmentItem> departmentData = departmentItemRepository.findById(id);
        if (departmentData.isPresent()) {
            return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}