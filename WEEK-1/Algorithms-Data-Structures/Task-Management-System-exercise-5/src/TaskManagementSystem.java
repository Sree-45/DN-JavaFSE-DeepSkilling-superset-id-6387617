
public class TaskManagementSystem {
    private TaskNode head;

    // Add task at end
    public void addTask(int taskId, String taskName, String status) {
        long startTime = System.nanoTime();
        TaskNode newNode = new TaskNode(taskId, taskName, status);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add task ID " + taskId + ": " + (endTime - startTime) + " ns");
    }

    // Search task by ID
    public TaskNode searchTask(int taskId) {
        long startTime = System.nanoTime();
        TaskNode temp = head;
        while (temp != null) {
            if (temp.taskId == taskId) {
                long endTime = System.nanoTime();
                System.out.println("Time to search task ID " + taskId + ": " + (endTime - startTime) + " ns");
                return temp;
            }
            temp = temp.next;
        }
        long endTime = System.nanoTime();
        System.out.println("Time to search task ID " + taskId + ": " + (endTime - startTime) + " ns");
        return null;
    }

    // Traverse tasks
    public void traverseTasks() {
        long startTime = System.nanoTime();
        TaskNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        long endTime = System.nanoTime();
        System.out.println("Time to traverse tasks: " + (endTime - startTime) + " ns");
    }

    // Delete task by ID
    public boolean deleteTask(int taskId) {
        long startTime = System.nanoTime();
        if (head == null) {
            System.out.println("Time to delete task ID " + taskId + ": " + (System.nanoTime() - startTime) + " ns");
            return false;
        }
        if (head.taskId == taskId) {
            head = head.next;
            System.out.println("Time to delete task ID " + taskId + ": " + (System.nanoTime() - startTime) + " ns");
            return true;
        }
        TaskNode prev = head;
        TaskNode curr = head.next;
        while (curr != null) {
            if (curr.taskId == taskId) {
                prev.next = curr.next;
                System.out.println("Time to delete task ID " + taskId + ": " + (System.nanoTime() - startTime) + " ns");
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        System.out.println("Time to delete task ID " + taskId + ": " + (System.nanoTime() - startTime) + " ns");
        return false;
    }

    // Main method
    public static void main(String[] args) {
        TaskManagementSystem taskList = new TaskManagementSystem();

        // Adding multiple tasks
        taskList.addTask(1, "Design UI", "Pending");
        taskList.addTask(2, "Implement Backend", "In Progress");
        taskList.addTask(3, "Testing", "Pending");
        taskList.addTask(4, "Database Setup", "Not Started");
        taskList.addTask(5, "Integration", "Pending");
        taskList.addTask(6, "Deployment", "Not Started");
        taskList.addTask(7, "Code Review", "In Progress");
        taskList.addTask(8, "Bug Fixing", "Pending");
        taskList.addTask(9, "Write Documentation", "Pending");
        taskList.addTask(10, "Final Approval", "Not Started");
        taskList.addTask(11, "Create Test Cases", "Pending");
        taskList.addTask(12, "Setup CI/CD", "In Progress");
        taskList.addTask(13, "UX Feedback Analysis", "Pending");
        taskList.addTask(14, "Project Review", "Not Started");
        taskList.addTask(15, "Sprint Planning", "Completed");

        System.out.println("\nTraversing Tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearching for Task ID 12:");
        TaskNode found = taskList.searchTask(12);
        System.out.println(found != null ? found : "Task not found");

        System.out.println("\nDeleting Task ID 3:");
        boolean deleted = taskList.deleteTask(3);
        System.out.println(deleted ? "Deleted successfully" : "Task not found");

        System.out.println("\nTraversing Tasks After Deletion:");
        taskList.traverseTasks();
    }
}
