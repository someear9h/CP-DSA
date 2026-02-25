Spring Boot is essentially built on annotations. They hide all the complex configuration so you can focus on writing your business logic—which is exactly what you want when you are racing against the clock in a hackathon or shipping a startup MVP.

Here is your essential cheat sheet for Spring Boot and Lombok annotations, breaking down what they do and exactly how to use them.

### 1. Lombok Annotations (The Boilerplate Killers)

*Note: Lombok isn't strictly Spring Boot, but they are used together in almost every modern Java app.*

**`@Builder`**

* **What it does:** Implements the Builder design pattern automatically. Instead of using a constructor with 10 parameters where you might mix up the order, it lets you construct objects step-by-step with chainable methods.
* **Code Snippet:**

```java
@Builder
public class User {
    private String username;
    private String email;
    private String role;
}

// How you use it to create an object:
User myUser = User.builder()
                  .username("samarth_dev")
                  .email("samarth@example.com")
                  .role("ADMIN")
                  .build();

```

**`@Data`**

* **What it does:** A massive shortcut. It automatically generates getters for all fields, setters for all non-final fields, and appropriate `toString()`, `equals()`, and `hashCode()` methods.

### 2. Controller Annotations (The API Gateways)

**`@RestController` & `@RequestMapping**`

* **What they do:** `@RestController` tells Spring that this class will handle web requests and automatically converts the responses to JSON (perfect for a React frontend). `@RequestMapping` sets the base URL for every endpoint in that class.

**`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping**`

* **What they do:** These route specific HTTP requests to your Java methods. Use `Get` to fetch data, `Post` to create new data, `Put` to update, and `Delete` to remove.

### 3. Request Data Extraction (Getting Data from the Client)

**`@RequestBody`**

* **What it does:** Grabs the JSON payload from the incoming HTTP POST/PUT request and automatically maps it into a Java object (like a DTO).

**`@RequestParam`**

* **What it does:** Extracts query parameters from the URL (the part after the `?`). Example URL: `/search?query=java&limit=10`.

**`@PathVariable`**

* **What it does:** Extracts dynamic values directly from the URL path itself. Example URL: `/users/123`.

**Code Snippet combining Controller & Request Annotations:**

```java
@RestController
@RequestMapping("/api/projects") // Base URL
public class ProjectController {

    // Handles: GET /api/projects/123
    @GetMapping("/{id}")
    public Project getProject(@PathVariable("id") Long projectId) {
        return projectService.findById(projectId);
    }

    // Handles: GET /api/projects/search?status=active
    @GetMapping("/search")
    public List<Project> searchProjects(@RequestParam(defaultValue = "all") String status) {
        return projectService.findByStatus(status);
    }

    // Handles: POST /api/projects (with JSON body from React)
    @PostMapping
    public Project createProject(@RequestBody ProjectDTO newProject) {
        return projectService.save(newProject);
    }
}

```

### 4. Architecture Stereotypes & Dependency Injection (The Core Concepts)

Spring uses an "Inversion of Control" container. Instead of you writing `new Service()`, Spring creates the objects (called Beans) at startup and "injects" them wherever you need them.

**`@Service`, `@Repository`, `@Component**`

* **What they do:** Tell Spring, "Hey, manage this class for me."
* Use `@Service` for classes holding your core business logic.
* Use `@Repository` for classes talking to the database.
* Use `@Component` for generic utility classes.



**`@Autowired` (Constructor Injection)**

* **What it does:** Plugs a Spring-managed Bean into another class. *Modern Best Practice:* Skip writing the `@Autowired` annotation completely and just use a constructor (or let Lombok's `@RequiredArgsConstructor` write it for you).

**Code Snippet for Injection:**

```java
@Service
@RequiredArgsConstructor // Lombok creates a constructor for all 'final' fields
public class ProjectService {
    
    // Spring automatically injects the repository here when creating the Service
    private final ProjectRepository repository; 

    public Project save(ProjectDTO dto) {
        // Business logic here
        return repository.save(dto);
    }
}

```

---