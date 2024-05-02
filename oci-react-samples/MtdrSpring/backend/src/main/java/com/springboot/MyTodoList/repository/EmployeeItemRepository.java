package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.EmployeeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public interface EmployeeItemRepository extends JpaRepository<EmployeeItem, Integer> {

}
