# PRA06: Spring Boot Conference Management with Vaadin

## CIFO La Violeta - FullStack IFCD0021-24 MF04-05

In this practical exercise, you will enhance the `SpringConference` project by implementing a more complex data model and creating Vaadin views for CRUD operations.

### Objectives

- Improve the data model with `Conference` and `Speaker` entities
- Implement <mark>OneToMany and ManyToOne relationships</mark>
- Use SQL data loading on first app start
- Create `Vaadin` views for entities
- Implement CRUD operations in `Vaadin` views

### Project Base

- Existing Repository: [Spring Conference](https://github.com/AlbertProfe/SpringConference)
- References:
  - [Spring Boot Lab 9.1](https://albertprofe.dev/springboot/sblab9-1.html)
  - [Spring Boot Lab 8.2](https://albertprofe.dev/springboot/sblab8-2.html)

### Tasks

1. Enhance Data Model
   
   - Create `Conference` and `Speaker` entities
   - Implement OneToMany and ManyToOne relationships
   - Use `Conference` as a join table

2. Configure Data Loading
   
   - Create `data.sql` file for initial data load
   - Configure `application.properties` to load data only on first start
   - Remember to use SQL data loading: `spring.sql.init.mode=always` in `application.properties` just the first time, then `never`.
   

3. Create Vaadin Views
   
   - Implement `MainLayout`
   - Create one view per entity (`Conference`, `Speaker`)
   - Use Vaadin components: `Avatar`, `ComboBox`, `DatePicker`

4. Implement CRUD Operations
   
   - <mark>Bind views to repositories</mark>
   - Implement **Create, Read, Update, and Delete** operations in views

5. Styling and Layout
   
   - Ensure responsive design
   - Apply consistent styling across views

### Example Code Structure

```java
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;

    @OneToMany(mappedBy = "conference")
    private List<Speaker> speakers;
    // Getters and setters
}

@Entity
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
    // Getters and setters
}
```

### Vaadin View Example

```java
@Route(value = "conferences", layout = MainLayout.class)
public class ConferenceView extends VerticalLayout {
    private final ConferenceRepository repository;
    private Grid<Conference> grid;
    private TextField nameField;
    private DatePicker datePicker;

    public ConferenceView(ConferenceRepository repository) {
        this.repository = repository;
        createGrid();
        createForm();
        add(grid, createFormLayout());
    }

    private void createGrid() {
        grid = new Grid<>(Conference.class);
        grid.setColumns("name", "date");
        updateList();
    }

    private void createForm() {
        nameField = new TextField("Name");
        datePicker = new DatePicker("Date");
        Button saveButton = new Button("Save", e -> saveConference());
        // Add form components and logic
    }

    private void saveConference() {
        Conference conference = new Conference();
        conference.setName(nameField.getValue());
        conference.setDate(datePicker.getValue());
        repository.save(conference);
        updateList();
        clearForm();
    }

    private void updateList() {
        grid.setItems(repository.findAll());
    }

    private void clearForm() {
        nameField.clear();
        datePicker.clear();
    }
}
```

### Submission Guidelines

- Fork the existing Spring Conference repository and clone to your local environment.
- Create a new branch named `PRA06-YourName`
- Implement the required changes
- Commit your changes with clear, descriptive messages
- Push your branch to your forked repository
- Create a <mark>pull request to the `AlbertProfe` repository</mark> with a summary of your changes with title:
  `PRA06-YourName-ConferenceManagement`

### Evaluation Criteria

- Correct implementation of entity relationships
- Proper configuration of initial data loading
- Functional Vaadin views with `CRUD` operations
- Effective use of Vaadin components (`Avatar`, `ComboBox`, `DatePicker`)
- Code clarity and documentation quality
- Adherence to Spring Boot and Vaadin best practices

Remember to focus on creating a clean, well-structured application that demonstrates good `Spring Boot` and `Vaadin` integration. Good luck!
