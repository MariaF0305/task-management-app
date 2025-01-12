package com.nttdata.task_management_app.bootstrap;

import com.nttdata.task_management_app.domain.Owner;
import com.nttdata.task_management_app.domain.Task;
import com.nttdata.task_management_app.repositories.OwnerRepository;
import com.nttdata.task_management_app.repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final OwnerRepository ownerRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public BootStrapData(OwnerRepository ownerRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        taskRepository.deleteAll();
        ownerRepository.deleteAll();

        // Creating Owners with encoded passwords
        Owner maria = new Owner("Maria", "Fintineanu", "fintineanu.maria@gmail.com", 9443823433L, passwordEncoder.encode("password123"));
        Task task1 = new Task("task1", "This is the first task you have to finish", 8, 3, 6, "");
        maria.getTasks().add(task1);
        task1.setOwner(maria);

        ownerRepository.save(maria);
        taskRepository.save(task1);

        Owner joudi = new Owner("Joudi", "Schmidt", "schmidt.joudi@gmail.com", 3372721111L, passwordEncoder.encode("password456"));
        Task task2 = new Task("task2", "This is the second task you have to finish", 30, 16, 15, "");
        joudi.getTasks().add(task2);
        task2.setOwner(joudi);

        ownerRepository.save(joudi);
        taskRepository.save(task2);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Tasks: " + taskRepository.count());

        System.out.println("Task1 Estimation Accuracy: " + task1.computeEstimationAccuracy());
        System.out.println("Task2 Estimation Accuracy: " + task2.computeEstimationAccuracy());

    }
}
