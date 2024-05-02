package com.springboot.MyTodoList.model;

import javax.persistence.*;

/*
    representation of the TODOITEM table that exists already
    in the autonomous database
 */
@Entity
@Table(name = "DEPARTAMENT")
public class DepartmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int departamentoID;
    @Column(name = "nombre")
    String nombre;

    public DepartmentItem() {

    }

    public DepartmentItem(int departamentoID, String nombre) {
        this.departamentoID = departamentoID;
        this.nombre = nombre;
    }

    public int getDepartamentoID() {
        return departamentoID;
    }

    public void setDepartamentoID(int departamentoID) {
        this.departamentoID = departamentoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "ID=" + departamentoID +
                ", name='" + nombre +
                '}';
    }
}
