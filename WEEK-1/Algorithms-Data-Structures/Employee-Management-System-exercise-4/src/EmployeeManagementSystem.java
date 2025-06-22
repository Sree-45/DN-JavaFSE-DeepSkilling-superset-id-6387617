class EmployeeManagementSystem {
    private static class Employee {
        int employeeId;
        String name;
        String position;
        double salary;

        Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        void display() {
            System.out.println("ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary);
        }
    }

    private Employee[] employees;
    private int count;

    // Stats
    private int searchCount = 0;
    private int deleteCount = 0;
    private int addCount = 0;

    EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public void addEmployee(int employeeId, String name, String position, double salary) {
        long startTime = System.nanoTime();

        if (count >= employees.length) {
            System.out.println("Cannot add more employees. Array is full.");
        } else if (internalSearch(employeeId) != -1) {
            System.out.println("Employee ID already exists!");
        } else {
            employees[count++] = new Employee(employeeId, name, position, salary);
            addCount++;
        }

        long endTime = System.nanoTime();
        System.out.println("Add operation took " + (endTime - startTime) + " ns");
    }

    public Employee searchEmployee(int employeeId) {
        long startTime = System.nanoTime();
        searchCount++;

        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                long endTime = System.nanoTime();
                System.out.println("Search operation took " + (endTime - startTime) + " ns");
                return employees[i];
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Search operation took " + (endTime - startTime) + " ns");
        return null;
    }

    private int internalSearch(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                return i;
            }
        }
        return -1;
    }

    public void deleteEmployee(int employeeId) {
        long startTime = System.nanoTime();
        int index = internalSearch(employeeId);

        if (index != -1) {
            for (int i = index; i < count - 1; i++) {
                employees[i] = employees[i + 1];
            }
            employees[--count] = null;
            deleteCount++;
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }

        long endTime = System.nanoTime();
        System.out.println("Delete operation took " + (endTime - startTime) + " ns");
    }

    public void displayAllEmployees() {
        for (int i = 0; i < count; i++) {
            employees[i].display();
        }
    }

    public void updateSalary(int employeeId, double newSalary) {
        Employee emp = searchEmployee(employeeId);
        if (emp != null) {
            emp.salary = newSalary;
            System.out.println("Salary updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void listByPosition(String position) {
        System.out.println("\nEmployees with position: " + position);
        for (int i = 0; i < count; i++) {
            if (employees[i].position.equalsIgnoreCase(position)) {
                employees[i].display();
            }
        }
    }

    public void showStats() {
        System.out.println("\n--- Statistics ---");
        System.out.println("Total Employees Added: " + addCount);
        System.out.println("Total Searches Made: " + searchCount);
        System.out.println("Total Deletions Made: " + deleteCount);
        System.out.println("Current Employees in System: " + count);
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(100);

        ems.addEmployee(101, "Sreeshanth Kandagtla", "Developer", 60000);
        ems.addEmployee(102, "Rohit Sharma", "Manager", 85000);
        ems.addEmployee(103, "Priya Reddy", "Designer", 55000);
        ems.addEmployee(104, "Ananya Rao", "HR", 50000);
        ems.addEmployee(105, "Vikram Singh", "Developer", 62000);
        ems.addEmployee(106, "Kavya Patel", "QA Engineer", 53000);
        ems.addEmployee(107, "Arjun Das", "Manager", 87000);

        System.out.println("\nAll Employees:");
        ems.displayAllEmployees();

        System.out.println("\nSearching for Employee with ID 102:");
        Employee emp = ems.searchEmployee(102);
        if (emp != null) emp.display();

        System.out.println("\nUpdating Salary of Employee ID 103:");
        ems.updateSalary(103, 58000);

        System.out.println("\nList Developers:");
        ems.listByPosition("Developer");

        System.out.println("\nDeleting Employee with ID 104:");
        ems.deleteEmployee(104);

        System.out.println("\nAll Employees After Deletion:");
        ems.displayAllEmployees();

        ems.showStats();
    }
}
