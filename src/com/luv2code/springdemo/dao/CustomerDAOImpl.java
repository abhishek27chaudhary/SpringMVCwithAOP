package com.luv2code.springdemo.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFact;
	
	//@Transactional  // removing becasuse we have added Service layer
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFact.getCurrentSession();
		
		//create a query 
		//sort by lastname - just update the sql
		Query theQuery = 
				currentSession.createQuery("from Customer order by lastName",
						Customer.class); //add two parameters
		
		//execute query
		List<Customer> cust = theQuery.getResultList();
		
		System.out.println(cust);
		//return the result
		
		
		return cust;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get the current hibernate session
		Session currentSession = sessionFact.openSession();
		
		
		//save the customer
		//currentSession.save(theCustomer);
		//we will use saveOrUpdate method to do this
		//System.out.println(theCustomer.getId()+" "+theCustomer.getFirstName());
		currentSession.saveOrUpdate("NewOrUpdatedCustomer",theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
	
		Session current = sessionFact.openSession();
		
		Customer theCustomer = current.get(Customer.class, theId);
		
		return theCustomer;
		
	}

	@Override
	public void deleteCustomer(int theId) {
		Session current = sessionFact.openSession();
//		Customer cust = current.get(Customer.class, theId);
//		current.delete(cust);
		
		
		Query theQuery =
				current.createQuery("delete from customer where id=:customerId",Customer.class);
		
		theQuery.setParameter("customerId",theId);
		
		theQuery.executeUpdate();
		
	
	}

}
