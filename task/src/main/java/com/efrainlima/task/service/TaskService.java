package com.efrainlima.task.service;

import com.efrainlima.taskrepository.data.Task;
import com.efrainlima.taskrepository.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository tasksRepository;

    private boolean foundDuplicatedRecords(Task task){
        Optional<Task> taskFound = Optional.ofNullable(tasksRepository.findById(task.getId()));

        return taskFound.isPresent();
    }

    public boolean saveTask(Task task){
        boolean isDuplicated = foundDuplicatedRecords(task);

        if(!isDuplicated){
            tasksRepository.save(task);
            Optional<Task> taskSaved = Optional.ofNullable(tasksRepository.findById(task.getId()));
            return taskSaved.isPresent();
        }else{
            return false;
        }
    }

    public Task findTaskById(String id) throws Exception {
        Optional<Task> taskFound = Optional.ofNullable(tasksRepository.findById(id));

        if(taskFound.isPresent())
            return taskFound.get();
        else
            throw new Exception();
    }

    public List<Task> findAllTasks(){
        return tasksRepository.findAll();
    }

    public Task updateTask(Task task) throws Exception{
        this.findTaskById(task.getId());

        tasksRepository.update(task);

        return this.findTaskById(task.getId());
    }

    public boolean deleteTask(String id){
        tasksRepository.delete(id);
        try{
            Optional<Task> task = Optional.ofNullable(this.findTaskById(id));
            return !task.isPresent();
        }catch(Exception err){
            return false;
        }
    }

    public boolean closeTask(String id) {
        try{
            tasksRepository.updateClose(id);
            Optional<Task> task = Optional.ofNullable(this.findTaskById(id));

            if(!task.isPresent()) throw new Exception();

            return task.get().getIsClosed().equals("true");
        }catch(Exception err){
            return false;
        }
    }

}
