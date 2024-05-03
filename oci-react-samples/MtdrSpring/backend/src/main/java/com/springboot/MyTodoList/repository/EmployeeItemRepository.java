package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.EmployeeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public interface EmployeeItemRepository extends JpaRepository<EmployeeItem, Integer> {
  /* List<EmployeeItem> findByDepartamentoID(String departamentoID);
   List<EmployeeItem> findByProyectoID(String proyectoID);
   List<EmployeeItem> findByCorreo(String correo);
   EmployeeItem findByEmployeeCorreo(String correo);
   */
  //@Query("SELECT e FROM Empleado e WHERE e.correo = :correo")

   EmployeeItem findByCorreo(String correo);
}
