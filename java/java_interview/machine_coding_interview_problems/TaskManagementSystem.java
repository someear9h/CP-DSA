package java_interview.machine_coding_interview_problems;

/*
Mini Task Management System (Trello/Jira Lite)
Features:

    The system manages Users and Tasks.

    A Task has a unique ID, a title, a description, and a status (TODO, IN_PROGRESS, DONE).

    A Task can be assigned to a single User.

Requirements:

    Create a User.

    Create a Task (defaults to TODO).

    Assign a Task to a User.

    Update the Status of a Task.

    Fetch all Tasks assigned to a specific User.

*/

import java.util.*;

enum Status {
    TODO, 
    IN_PROGESS,
    DONE
}

class Task {
    private final String taskId;
    private final String title;
    private String description;
    private Status status;
    private String assigneeId;

    public Task(String taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
    }

    //-------- Getters -------------

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getAssignee() {
        return assigneeId;
    }

    //-------- Setters -------------

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignee(String userId) {
        this.assigneeId = userId;
    }
}

class User {
    private final String userId;

    public User(String userId) {
        this.userId = userId;
    }
}

class TaskService {
    // dbs
    Map<String, Task> tasksDB = new HashMap<>();
    Map<String, User> usersDB = new HashMap<>();

    // create a user
    public void createUser(String id) {
        usersDB.put(id, new User(id));
        System.out.printf("User %s created successfully\n", id);
    }

    // create a task
    public void createTask(String taskId, String title, String desc) {
        tasksDB.put(taskId, new Task(taskId, title, desc));
        System.out.printf("Task %s created successfully\n", taskId);
    }

    // assign a task to user
    public void assignTask(String userId, String taskId) {
        if(!usersDB.containsKey(userId)) {
            System.out.printf("User %s does not exist\n", userId);
            return;
        }

        if(!tasksDB.containsKey(taskId)) {
            System.out.printf("Task %s does not exist\n", taskId);
            return;
        }
        
        Task task = tasksDB.get(taskId);
        task.setAssignee(userId);

        System.out.printf("Task %s assigned to %s\n", taskId, userId);
    }

    // update status for a task
    public void updateStatus(String taskId, Status status) {
        if(!tasksDB.containsKey(taskId)) {
            System.out.printf("Task %s does not exist\n", taskId);
            return;
        }
        
        Task task = tasksDB.get(taskId);

        if(status == task.getStatus()) {
            System.out.printf("Status for task %s is already %s\n", taskId, status);
            return;
        }

        task.setStatus(status);
        System.out.printf("Status for task %s updated to %s successfully\n", taskId, status);
    }

    // get all tasks assigned to a user
    public List<Task> getAllTasks(String userId) {
        return tasksDB.values().stream()
                                .filter(t -> userId.equals(t.getAssignee()))
                                .toList();
    }
}

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskService service = new TaskService();

        service.createUser("u1");
        service.createUser("u2");

        service.createTask("t1", "intsall db", "install postgres");
        service.createTask("t2", "look above", "wihout moving you head");
        service.createTask("t3", "solve coding problems", "only above 1000 rated");

        service.assignTask("u1", "t1");
        service.assignTask("u1", "t2");
        service.assignTask("u2", "t3");

        service.updateStatus("t3", Status.DONE);
        service.updateStatus("t3", Status.DONE);
        service.updateStatus("t4", Status.DONE);

        List<Task> tasks = service.getAllTasks("u1");
        for(Task t : tasks) {
            System.out.printf("Task: %s | Status: %s\n", t.getTitle(), t.getStatus());
        }
    }
}
