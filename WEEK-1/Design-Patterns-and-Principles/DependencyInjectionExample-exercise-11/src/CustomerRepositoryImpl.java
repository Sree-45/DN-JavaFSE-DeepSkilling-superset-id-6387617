import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Map<Integer, Customer> customers;
    
    public CustomerRepositoryImpl() {
        customers = new HashMap<>();
        // Initialize with some sample data
        customers.put(1, new Customer(1, "sreeshanth", "sreeshanthkandagatla2005@gmail.com"));
        customers.put(2, new Customer(2, "priya", "lakshmipriyaposhala87@gmail.com"));
        customers.put(3, new Customer(3, "harsha", "naniharsha96@gmail.com"));
    }
    
    @Override
    public Customer findCustomerById(int id) {
        System.out.println("Searching for customer with ID: " + id);
        Customer customer = customers.get(id);
        if (customer == null) {
            System.out.println("Customer not found with ID: " + id);
        }
        return customer;
    }
    
    @Override
    public void saveCustomer(Customer customer) {
        System.out.println("Saving customer: " + customer.getName());
        customers.put(customer.getId(), customer);
    }
    
    @Override
    public void deleteCustomer(int id) {
        System.out.println("Deleting customer with ID: " + id);
        customers.remove(id);
    }
    
    @Override
    public Customer[] getAllCustomers() {
        System.out.println("Retrieving all customers");
        return customers.values().toArray(new Customer[0]);
    }
}
