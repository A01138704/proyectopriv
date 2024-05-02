package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.ProjectItem;
import com.springboot.MyTodoList.model.ToDoItem;
import com.springboot.MyTodoList.repository.ProjectItemRepository;
import com.springboot.MyTodoList.repository.ToDoItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectItemService {

    @Autowired
    private ProjectItemRepository projectRepository;

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    public List<ProjectItem> findAll() {
        List<ProjectItem> proyectoItems = projectRepository.findAll();
        return proyectoItems;
    }

    public ResponseEntity<ProjectItem> getProjectItemById(int id) {
        Optional<ProjectItem> projectData = projectRepository.findById(id);
        if (projectData.isPresent()) {
            return new ResponseEntity<>(projectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ProjectItem addProjectItem(ProjectItem projectItem) {
        return projectRepository.save(projectItem);
    }

    public boolean deleteProjectItem(int id) {

        try {
            List<ToDoItem> tasksToDelete = toDoItemRepository.findByProyectoID(id);

            for (ToDoItem item : tasksToDelete) {
                toDoItemRepository.deleteById(item.getTareaID());
            }
            projectRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public ProjectItem updateProjectItem(int id, ProjectItem td) {
        Optional<ProjectItem> toDoItemData = projectRepository.findById(id);
        if (toDoItemData.isPresent()) {
            ProjectItem projectItem = toDoItemData.get();
            projectItem.setProyectoID(id);
            projectItem.setNombre(td.getNombre());
            projectItem.setFechaInicio(td.getFechaInicio());
            projectItem.setFechaFin(td.getFechaFin());
            projectItem.setEstatus(td.getEstatus());
            projectItem.setDepartmentoID(td.getDepartmentoID());

            return projectRepository.save(projectItem);
        } else {
            return null;
        }
    }

}