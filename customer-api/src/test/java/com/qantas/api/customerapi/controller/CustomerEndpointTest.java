package com.qantas.api.customerapi.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.qantas.api.customerapi.model.Address;
import com.qantas.api.customerapi.model.Customer;
import com.qantas.api.customerapi.model.HomeAddress;
import com.qantas.api.customerapi.model.OfficeAddress;
import com.qantas.api.customerapi.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerEndpointTest extends BaseEndpointTest {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerService customerRepository;

	
	private Customer testCustomer;
	private Date dob;
	private String postEndPoint = "/customerapi/customers";
	
    @Before
    public void setup() throws Exception {
    	super.setup();

    	dob = new Date();

    	// create test persons
    	customerService.create(createCustomerData("Shriram", "Devanathan", dob, createAddress()));
		customerService.create(createCustomerData("Peter", "Pettigrew",dob, createAddress()));
		customerService.create(createCustomerData("Harry", "Potter",dob, createAddress()));
		customerService.create(createCustomerData("Severus", "Snape",dob, createAddress()));


    	List<Customer> customers = customerService.getAll();
		assertNotNull(customers);
		assertEquals(customers.size(), 4);
		
		testCustomer = customers.get(0);
		
//		entityManager.refresh(testCustomer);
    }

    @Test
    public void getCustomerById() throws Exception {
    	Long id = testCustomer.getId();
    	
    	MvcResult result = mockMvc.perform(get("/customerapi/customers/{id}", id))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(JSON_MEDIA_TYPE))
    	.andExpect(jsonPath("$.id", is(id.intValue())))
    	.andExpect(jsonPath("$.firstName", is(testCustomer.getFirstName())))
    	.andExpect(jsonPath("$.lastName", is(testCustomer.getLastName())))
		.andExpect(jsonPath("$.addresses.length()", is(2) ))
    	.andReturn()
    	;
    	
    	logger.debug("content="+ result.getResponse().getContentAsString());
    }

	/**
	 * This is a test for post(customer creation)
	 * @throws Exception
	 */
	@Test
    public void createCustomer2() throws Exception {

    	Customer person = createCustomerData("ShriramTest", "DevanathanTest", dob, createAddress());
		//Some issue deserializing address bcos of address_type, so hardcoding the value for now
    	//String content = json(person);
		String content = jsonHardCoded();

		mockMvc.perform(post(postEndPoint)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", Matchers.is(person.getFirstName())))
		;
    }
	public void createCustomerDummy() throws Exception {

		Customer person = createCustomerData("ShriramTest", "DevanathanTest", dob, createAddress());
		//Some issue deserializing address bcos of address_type, so hardcoding the value for now
		//String content = json(person);
		String content = jsonHardCoded();

		mockMvc.perform(post(postEndPoint)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", Matchers.is(person.getFirstName())))

		;

	}

	/**
	 * This is a test of update
	 * @throws Exception
	 */
    @Test
    public void createCustomer1() throws Exception {
		createCustomerDummy();
		Customer person = createCustomerData("ShriramTest", "DevanathanTest", dob, createAddress());
		person.setId(1L);
    	String content = jsonHardCodedWithId(1L);

    	mockMvc.perform(
    			put(postEndPoint)
    			.accept(JSON_MEDIA_TYPE)
    			.content(content)
    			.contentType(JSON_MEDIA_TYPE))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.id", isA(Number.class)))
    	.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
    	.andExpect(jsonPath("$.lastName", is(person.getLastName())))
    	;

    }

	private Customer createCustomerData(String first, String last, Date dob, List<Address> address) {
		Customer customer = new Customer(first, last, dob, address);
		return customer;
	}
	
	private List<Address> createAddress(){
    	List<Address> addresses = new ArrayList<Address>();
    	OfficeAddress add = new OfficeAddress();
    	add.setAddress_type("Office");
    	add.setCity("North Sydney");
    	add.setCountry("Australia");
    	add.setPostalCode("2000");
    	add.setStreet("Miller Street");
    	add.setComplexName("Chifley Square");
    	addresses.add(add);

		HomeAddress homeAdd = new HomeAddress();
		homeAdd.setAddress_type("Home");
		homeAdd.setCity("Parramatta");
		homeAdd.setCountry("Australia");
		homeAdd.setPostalCode("2150");
		homeAdd.setStreet("Great Western Highway");
		homeAdd.setUnitNumber("20");
		homeAdd.setApartmentNumber("62");
		addresses.add(homeAdd);
		return addresses;
	}
}