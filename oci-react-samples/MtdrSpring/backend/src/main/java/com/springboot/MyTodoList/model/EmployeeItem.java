package com.springboot.MyTodoList.model;

import javax.persistence.*;

/*
    representation of the TODOITEM table that exists already
    in the autonomous database
 */
@Entity
@Table(name = "EMPLEADO", schema = "TODOUSER")
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

  //  @Column(name = "proyectoid")
   // private int proyectoID; 
   @ManyToOne
   @JoinColumn(name = "PROYECTOID", referencedColumnName = "PROYECTOID" ) //
   private ProjectItem proyectoID;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTOID", referencedColumnName = "DEPARTAMENTOID" ) // 
    private DepartmentItem departamentoID;

    public EmployeeItem() {

    }

    public EmployeeItem(int empleadoID, String nombre, String apellido, String correo, String celular, String direccion,
            boolean estatus, ProjectItem proyectoID, DepartmentItem departamentoID) {
        this.empleadoID = empleadoID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.direccion = direccion;
        this.estatus = estatus;
        this.proyectoID = proyectoID; // esto es nuevo 
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

    
    public ProjectItem getProyectoID() {
        return proyectoID;
    }

    public void setProyectoID(ProjectItem proyectoID) {
        this.proyectoID = proyectoID;
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
                ", proyectoID=" + proyectoID + // esto es nuevo porque no estaba en la db 
                ", departmentID=" + departamentoID +
            
                '}';
    }
}

