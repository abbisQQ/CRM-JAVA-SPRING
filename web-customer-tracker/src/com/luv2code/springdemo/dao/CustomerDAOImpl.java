package com.luv2code.springdemo.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//inject the hibernate factory
	
	@Autowired
	private SessionFactory SessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		Session currentSession =SessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
		
		
		
		
		
		
		
		
		
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//writing to database
		
		Session currentSession = SessionFactory.getCurrentSession();
		
		//save oe update the customer
		currentSession.saveOrUpdate(theCustomer);
		
		
	}


	@Override
	public Customer getCustomers(int theId) {
		//get the current hibernate session
		Session currentSession = SessionFactory.getCurrentSession();
		
		//retrieve from the database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		
		
		
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
				Session currentSession = SessionFactory.getCurrentSession();
				
				//delete from the database using the primary key
				Query query = currentSession.createQuery("delete from Customer where id=:customerId");
				
				query.setParameter("customerId", theId);
				
				query.executeUpdate();
				

	}

}
