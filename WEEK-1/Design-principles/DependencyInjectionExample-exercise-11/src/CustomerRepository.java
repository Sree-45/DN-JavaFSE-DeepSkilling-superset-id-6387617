public interface CustomerRepository {
    Customer findCustomerById(int id);
    void saveCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer[] getAllCustomers();
}
