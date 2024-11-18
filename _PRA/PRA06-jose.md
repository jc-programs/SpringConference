# PRA06

## Remarks

- Created LayoutRender for Book and Speaker
- Binder working with SelectBox. Definition of equals and hashCode of Book and Speaker
- Used ComponentRender in SelectBox for Book and Speaker
- Used ComponentRender in Grid for Book and Speaker


## Tasks

1. [x] Enhance Data Model

    - Create `Conference` and `Speaker` entities
    - Implement OneToMany and ManyToOne relationships
    - Use `Conference` as a join table

1. [x] Configure Data Loading

    - Create `data.sql` file for initial data load
    - Configure `application.properties` to load data only on first start
    - Remember to use SQL data loading: `spring.sql.init.mode=always` in `application.properties` just the first time, then `never`.

1. [x] Create Vaadin Views

    - Implement `MainLayout`
    - Create one view per entity (`Conference`, `Speaker`)
    - Use Vaadin components: `Avatar`, `ComboBox`, `DatePicker`

1. [x] Implement CRUD Operations

    - <mark>Bind views to repositories</mark>
    - Implement **Create, Read, Update, and Delete** operations in views

1. [x] Styling and Layout

    - Ensure responsive design
    - Apply consistent styling across views

