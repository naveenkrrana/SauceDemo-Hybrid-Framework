package models;

public class Employee {

    // These perfectly match the 3 columns in our SQL Database
    private int id;
    private String name;
    private String role;

    // Empty Constructor required for mapping
    public Employee() {
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // The toString method allows us to easily print the entire object to the console
    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", Role=" + role + "]";
    }
}