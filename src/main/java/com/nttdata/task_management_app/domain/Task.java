package com.nttdata.task_management_app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Integer estimatedHours;
    private Integer completedHours;
    private Integer remainingEffort;
    private String estimationAccuracy;

    @ManyToOne
    @JoinColumn(name = "own_id", nullable = false)
    @JsonManagedReference
    private Owner owner;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    public Task() {
    }

    public Task(String title, String description, Integer estimatedHours, Integer completedHours, Integer remainingEffort, String estimationAccuracy) {
        this.title = title;
        this.description = description;
        this.estimatedHours = estimatedHours;
        this.completedHours = completedHours;
        this.remainingEffort = remainingEffort;
        this.estimationAccuracy = estimationAccuracy;
    }

    public String computeEstimationAccuracy() {
        if (estimatedHours == null || estimatedHours == 0) {
            return "NOT_ESTIMATED";
        }
        if (completedHours == null) {
            return "NOT_ESTIMATED";
        }
        if (completedHours >= 0.9 * estimatedHours && completedHours <= 1.1 * estimatedHours) {
            return "ACCURATELY_ESTIMATED";
        }
        if (completedHours < 0.9 * estimatedHours) {
            return "OVER_ESTIMATED";
        }
        if (completedHours > 1.1 * estimatedHours) {
            return "UNDER_ESTIMATED";
        }
        return "UNKNOWN";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Integer getCompletedHours() {
        return completedHours;
    }

    public void setCompletedHours(Integer completedHours) {
        this.completedHours = completedHours;
    }

    public Integer getRemainingEffort() {
        return remainingEffort;
    }

    public void setRemainingEffort(Integer remainingEffort) {
        this.remainingEffort = remainingEffort;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getEstimationAccuracy() {
        return estimationAccuracy;
    }

    public void setEstimationAccuracy(String estimationAccuracy) {
        this.estimationAccuracy = estimationAccuracy;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setTask(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setTask(null);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", estimatedHours=" + estimatedHours +
                ", completedHours=" + completedHours +
                ", remainingEffort=" + remainingEffort +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

