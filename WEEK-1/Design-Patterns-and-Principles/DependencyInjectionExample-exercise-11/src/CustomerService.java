public class CustomerService {
    private final CustomerRepository customerRepository;
    
    // Constructor Injection - This is the key part of Dependency Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        System.out.println("CustomerService created with repository dependency injected");
    }
    
    public Customer getCustomerById(int id) {
        System.out.println("CustomerService: Getting customer by ID " + id);
        return customerRepository.findCustomerById(id);
    }
    
    public void createCustomer(int id, String name, String email) {
        System.out.println("CustomerService: Creating new customer");
        Customer customer = new Customer(id, name, email);
        customerRepository.saveCustomer(customer);
    }
    
    public void removeCustomer(int id) {
        System.out.println("CustomerService: Removing customer with ID " + id);
        customerRepository.deleteCustomer(id);
    }
    
    public Customer[] listAllCustomers() {
        System.out.println("CustomerService: Listing all customers");
        return customerRepository.getAllCustomers();
    }
    
    public void displayCustomerInfo(int id) {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            System.out.println("Customer Details: " + customer);
        } else {
            System.out.println("No customer found with ID: " + id);
        }
    }
}
