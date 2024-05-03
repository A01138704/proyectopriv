package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.DepartmentItem;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public interface DepartmentItemRepository extends JpaRepository<DepartmentItem, Integer> {
    List<DepartmentItem> findByDepartamentoID(String departamentoID);
   // List<DepartmentItem> findByProyectoID(String proyectoID);

}
