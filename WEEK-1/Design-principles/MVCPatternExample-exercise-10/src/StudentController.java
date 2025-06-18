public class StudentController {
    private Student model;
    private StudentView view;
    
    // Constructor
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    
    // Methods to update model data
    public void setStudentName(String name) {
        model.setName(name);
    }
    
    public void setStudentId(String id) {
        model.setId(id);
    }
    
    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }
    
    // Methods to get model data
    public String getStudentName() {
        return model.getName();
    }
    
    public String getStudentId() {
        return model.getId();
    }
    
    public String getStudentGrade() {
        return model.getGrade();
    }
    
    // Method to update view
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}