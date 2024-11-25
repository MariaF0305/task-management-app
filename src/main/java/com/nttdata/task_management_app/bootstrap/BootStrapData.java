package com.nttdata.task_management_app.bootstrap;

import com.nttdata.task_management_app.domain.Owner;
import com.nttdata.task_management_app.domain.Task;
import com.nttdata.task_management_app.repositories.OwnerRepository;
import com.nttdata.task_management_app.repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final OwnerRepository ownerRepository;
    private final TaskRepository taskRepository;

    public BootStrapData(OwnerRepository ownerRepository, TaskRepository taskRepository) {
        this.ownerRepository = ownerRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
/*
        taskRepository.deleteAll();
        ownerRepository.deleteAll();

        Owner maria = new Owner("Maria", "Fintineanu", "fintineanu.maria@gmail.com", 9443823433L);
        Task task1 = new Task("task1", "This is the first task you have to finish",8, 3, 6);
        maria.getTasks().add(task1);
        task1.setOwner(maria);

        ownerRepository.save(maria);
        taskRepository.save(task1);

        Owner joudi = new Owner("Joudi", "Schmidt", "schmidt.joudi@gmail.com", 3372721111L);
        Task task2 = new Task("task2", "This is the second task you have to finish",30, 16, 15);
        joudi.getTasks().add(task2);
        task2.setOwner(joudi);

        ownerRepository.save(joudi);
        taskRepository.save(task2);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Tasks: " + taskRepository.count());*/
    }
}
