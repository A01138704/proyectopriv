package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

/*
    representation of the TODOITEM table that exists already
    in the autonomous database
 */
@Entity
@Table(name = "PROYECTO")
public class ProjectItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int proyectoID;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "fechaInicio")
    OffsetDateTime fechaInicio;
    @Column(name = "fechaFin")
    OffsetDateTime fechaFin;
    @Column(name = "estatus")
    boolean estatus;

    @ManyToOne
    @JoinColumn(name = "departmentoID")
    private DepartmentItem departmentoID;

    public ProjectItem() {

    }

    public ProjectItem(int proyectoID, String nombre, OffsetDateTime fechaInicio, OffsetDateTime fechaFin,
            boolean estatus,
            DepartmentItem departmentoID) {
        this.proyectoID = proyectoID;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estatus = estatus;
        this.departmentoID = departmentoID;

    }

    public int getProyectoID() {
        return proyectoID;
    }

    public void setProyectoID(int proyectoID) {
        this.proyectoID = proyectoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public OffsetDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(OffsetDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public DepartmentItem getDepartmentoID() {
        return departmentoID;
    }

    public void setDepartmentoID(DepartmentItem departmentoID) {
        this.departmentoID = departmentoID;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "ID=" + proyectoID +
                ", name='" + nombre + '\'' +
                ", start_ts=" + fechaInicio +
                ", end_ts=" + fechaFin +
                ", done=" + estatus +
                ", departmentID=" + departmentoID +
                '}';
    }
}
