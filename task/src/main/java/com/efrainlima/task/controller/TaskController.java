package com.efrainlima.task.controller;

import com.efrainlima.task.data.TaskData;
import com.efrainlima.task.service.TaskService;
import com.efrainlima.taskrepository.data.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String taskRegistration(Model model) {

        model.addAttribute("title", "Task Registration");

        return "tasks";
    }

    @GetMapping("/add-tasks")
    public String addTask(Model model) {

        model.addAttribute("title", "(Add)Task Registration");
        model.addAttribute("taskData", new TaskData());

        return "add-tasks";
    }

    @GetMapping("/task-viewer")
    public String getTaskViewer(Model model) {

        List<Task> tasks = taskService.findAllTasks();

        model.addAttribute("title", "(Viewer) Task Registration");
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskData", new TaskData());

        return "task-viewer";
    }

    @PostMapping("/add-tasks")
    public String addNewTask(@Valid @ModelAttribute TaskData data, BindingResult bindingResult){

        Task task = Task.builder().
                id(UUID.randomUUID().toString()).
                taskName(data.getTaskName()).
                taskDescription(data.getTaskDescription()).
                initDate(data.getStartDate()).
                isClosed("false").
                build();

        boolean isSaved = taskService.saveTask(task);

        return (bindingResult.hasErrors() || !isSaved)? "tasks": "redirect:/task-viewer";
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam("taskId") String taskId){

        taskService.deleteTask(taskId);

        return "redirect:/task-viewer";
    }

    @PostMapping("/close-task")
    public String closeTask(@RequestParam("taskId") String taskId){

        taskService.closeTask(taskId);

        return "redirect:/task-viewer";
    }

}
