package com.ehabibov.springtest.tests;

import com.ehabibov.springtest.config.TestConfig;
import com.ehabibov.springtest.resource.CustomerResource;
import com.ehabibov.springtest.bindings.CustomerResponseBinding;
import com.ehabibov.springtest.bindings.UpdateCustomerRequestBinding;
import com.ehabibov.springtest.bindings.CreateCustomerRequestBinding;
import com.ehabibov.springtest.bindings.EmptyBinding;
import com.ehabibov.springtest.tests.extensions.CustomersDataloadExtension;
import com.ehabibov.springtest.tests.extensions.CustomersCleanupExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class PhoneBookCrudIT {

    @RegisterExtension @Order(1)
    static CustomersCleanupExtension customersCleanup = new CustomersCleanupExtension();

    @RegisterExtension @Order(2)
    static CustomersDataloadExtension customersDataload = new CustomersDataloadExtension();

    @Autowired
    private CustomerResource customerResource;

    @Test
    public void get_all_contacts() {
        String name = "Jack";
        String telNo1 = "+13975559101";
        String telNo2 = "+13975559102";

        ResponseEntity<List<CustomerResponseBinding>> response = customerResource.getCustomers();
        List<CustomerResponseBinding> customers = response.getBody();

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CustomerResponseBinding customer = CustomerResource.findCustomerWithName(customers, name);

        assertNotNull(customer);
        assertTrue(customer.getPhones().contains(telNo1));
        assertTrue(customer.getPhones().contains(telNo2));
    }

    @Test
    public void get_exact_contact() {
        String name = "Mark";
        String telNo1 = "+13975554101";
        String telNo2 = "+13975554102";

        ResponseEntity<Set> getResponse = customerResource.getCustomer(name);

        assertEquals(getResponse.getStatusCode(), HttpStatus.OK);

        Set<String> numbers = getResponse.getBody();

        assertNotNull(numbers);
        assertTrue(numbers.contains(telNo1));
        assertTrue(numbers.contains(telNo2));
    }

    @Test
    public void create_contact_with_number(){
        String name = "Jill";
        String telNo1 = "+13975554201";

        CreateCustomerRequestBinding contact = new CreateCustomerRequestBinding.Builder().setName(name).setPhone(telNo1).build();
        ResponseEntity postResponse = customerResource.createCustomer(contact);

        assertEquals(postResponse.getStatusCode(), HttpStatus.CREATED);

        ResponseEntity<List<CustomerResponseBinding>> getResponse = customerResource.getCustomers();
        List<CustomerResponseBinding> customers = getResponse.getBody();
        CustomerResponseBinding customer = CustomerResource.findCustomerWithName(customers, name);

        assertNotNull(customer);
        assertTrue(customer.getPhones().contains(telNo1));
    }

    @Test
    public void create_contact_with_already_reserved_number(){
        String name = "John";
        String telNo1 = "+13975554301";

        CreateCustomerRequestBinding contact = new CreateCustomerRequestBinding.Builder().setName(name).setPhone(telNo1).build();
        assertThrows(HttpServerErrorException.class, () -> {
                customerResource.createCustomer(contact);
        });
    }

    @Test
    public void create_contact_with_already_assigned_number(){
        String name = "Nicole";
        String telNo = "+13975554401";

        CreateCustomerRequestBinding contact2 = new CreateCustomerRequestBinding.Builder().setName(name).setPhone(telNo).build();
        assertThrows(HttpServerErrorException.class, () -> {
            customerResource.createCustomer(contact2);
        });
    }

    @Test
    public void update_existing_contact_with_new_number(){
        String name = "Sarah";
        String telNo1 = "+13975554501";
        String telNo2 = "+13975554502";

        UpdateCustomerRequestBinding update = new UpdateCustomerRequestBinding.Builder().setPhone(telNo2).build();

        ResponseEntity putResponse = customerResource.updateCustomer(name, update);
        assertEquals(putResponse.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Set> getResponse = customerResource.getCustomer(name);
        assertEquals(getResponse.getStatusCode(), HttpStatus.OK);

        Set<String> numbers = getResponse.getBody();
        assertNotNull(numbers);
        assertTrue(numbers.contains(telNo1));
        assertTrue(numbers.contains(telNo2));
    }

    @Test
    public void update_existing_contact_with_existing_number(){
        String name = "Lucy";
        String telNo1 = "+13975554601";

        UpdateCustomerRequestBinding update = new UpdateCustomerRequestBinding.Builder().setPhone(telNo1).build();

        ResponseEntity putResponse = customerResource.updateCustomer(name, update);
        assertEquals(putResponse.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Set> getResponse = customerResource.getCustomer(name);
        assertEquals(getResponse.getStatusCode(), HttpStatus.OK);

        Set<String> numbers = getResponse.getBody();

        assertNotNull(numbers);
        assertTrue(numbers.contains(telNo1));
        assertEquals(numbers.size(),1);
    }

    @Test
    public void update_not_existing_contact(){
        String name = "Matthew";
        String telNo1 = "+13975554701";

        UpdateCustomerRequestBinding update = new UpdateCustomerRequestBinding.Builder().setPhone(telNo1).build();
        assertThrows(HttpServerErrorException.class, () -> {
            customerResource.updateCustomer(name, update);
        });
    }

    @Test
    public void delete_existing_contact() {
        String name = "Rachel";
        String telNo = "+13975554801";

        CreateCustomerRequestBinding contact = new CreateCustomerRequestBinding.Builder().setName(name).setPhone(telNo).build();

        ResponseEntity deleteResponse = customerResource.deleteCustomer(name, contact);
        assertEquals(deleteResponse.getStatusCode(), HttpStatus.OK);

        ResponseEntity<List<CustomerResponseBinding>> getResponse2 = customerResource.getCustomers();
        List<CustomerResponseBinding> customers2 = getResponse2.getBody();
        CustomerResponseBinding customer2 = CustomerResource.findCustomerWithName(customers2, name);

        assertNull(customer2);
    }

    @Test
    public void delete_not_existing_contact() {
        String name = "not_existing_name";

        assertThrows(HttpServerErrorException.class, () -> {
            customerResource.deleteCustomer(name, EmptyBinding.class);
        });
    }

}