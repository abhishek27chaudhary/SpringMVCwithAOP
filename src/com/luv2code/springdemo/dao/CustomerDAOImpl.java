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
						Customer.class);
		
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
		currentSession.save(theCustomer);
		
		//close the current Session
		//currentSession.close();
	}

}
