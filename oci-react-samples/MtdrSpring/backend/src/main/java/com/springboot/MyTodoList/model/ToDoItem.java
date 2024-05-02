package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

/*
    representation of the TODOITEM table that exists already
    in the autonomous database
    CREATE TABLE TODOUSER.tarea (tareaID NUMBER GENERATED ALWAYS AS IDENTITY, nombre VARCHAR2(4000) NOT NULL, descripcion VARCHAR2(4000) NOT NULL, fechaCreacion TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, estatus NUMBER(1,0), fechaLimite TIMESTAMP WITH TIME ZONE NOT NULL, tipoTarea VARCHAR2(4000) NOT NULL, empleadoID NUMBER, PRIMARY KEY (tareaID), FOREIGN KEY(empleadoID) references todouser.empleado(empleadoID));

 */
@Entity
@Table(name = "TAREA")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tareaID;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "descripcion")
    String descripcion;
    @Column(name = "fechaCreacion")
    OffsetDateTime fechaCreacion;
    @Column(name = "estatus")
    boolean estatus;
    @Column(name = "fechaLimite")
    OffsetDateTime fechaLimite;
    @Column(name = "tipoTarea")
    String tipoTarea;

    @ManyToOne
    @JoinColumn(name = "empleadoID")
    private EmployeeItem empleadoID;
    @ManyToOne
    @JoinColumn(name = "proyectoID")
    private ProjectItem proyectoID;

    public ToDoItem() {

    }

    public ToDoItem(int tareaID, String nombre, String descripcion, OffsetDateTime fechaCreacion, boolean estatus,
            OffsetDateTime fechaLimite, String tipoTarea, EmployeeItem empleadoID, ProjectItem proyectoID) {
        this.tareaID = tareaID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estatus = estatus;
        this.fechaLimite = fechaLimite;
        this.tipoTarea = tipoTarea;
        this.empleadoID = empleadoID;
        this.proyectoID = proyectoID;
    }

    public int getTareaID() {
        return tareaID;
    }

    public void setTareaID(int tareaID) {
        this.tareaID = tareaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    public OffsetDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(OffsetDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public OffsetDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(OffsetDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public EmployeeItem getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(EmployeeItem empleadoID) {
        this.empleadoID = empleadoID;
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
                "ID=" + tareaID +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion +
                ", fechaCreacion=" + fechaCreacion +
                ", estatus=" + estatus +
                ", fechaLimite=" + fechaLimite +
                ", tipoTarea='" + tipoTarea +
                ", empleadoID=" + empleadoID +
                ", proyectoID=" + proyectoID +
                '}';
    }
}
