package com.springboot.MyTodoList.model;

import javax.persistence.*;

/*
    representation of the TODOITEM table that exists already
    in the autonomous database
 */
@Entity
@Table(name = "EMPLEADO")
public class EmployeeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int empleadoID;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "apellido")
    String apellido;
    @Column(name = "correo")
    String correo;
    @Column(name = "celular")
    String celular;
    @Column(name = "direccion")
    String direccion;
    @Column(name = "estatus")
    boolean estatus;

    @ManyToOne
    @JoinColumn(name = "departamentoID")
    private DepartmentItem departamentoID;

    public EmployeeItem() {

    }

    public EmployeeItem(int empleadoID, String nombre, String apellido, String correo, String celular, String direccion,
            boolean estatus, DepartmentItem departamentoID) {
        this.empleadoID = empleadoID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.direccion = direccion;
        this.estatus = estatus;
        this.departamentoID = departamentoID;
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public DepartmentItem getDepartmentoID() {
        return departamentoID;
    }

    public void setDepartmentoID(DepartmentItem departamentoID) {
        this.departamentoID = departamentoID;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "ID=" + empleadoID +
                ", name='" + nombre +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo +
                ", celular='" + celular +
                ", direccion='" + direccion +
                ", status=" + estatus +
                ", departmentID=" + departamentoID +
                '}';
    }
}
