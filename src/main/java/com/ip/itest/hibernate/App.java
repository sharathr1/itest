package com.ip.itest.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class App {
	
	public static void main(String[] args) {
		
		CorpEmployee em1 = new CorpEmployee("Mary Smith", 25);
		CorpEmployee em2 = new CorpEmployee("John Aces", 32);
		CorpEmployee em3 = new CorpEmployee("Ian Young", 29);
		
		System.out.println(" =======CREATE =======");
		create(em1);
		create(em2);
		create(em3);
		System.out.println(" =======READ =======");
		List<CorpEmployee> ems1 = read();
		for(CorpEmployee e: ems1) {
			System.out.println(e.toString());
		}
		System.out.println(" =======UPDATE =======");
		em1.setAge(44);
		em1.setName("Mary Rose");
		update(em1);
		System.out.println(" =======READ =======");
		List<CorpEmployee> ems2 = read();
		for(CorpEmployee e: ems2) {
			System.out.println(e.toString());
		}
		System.out.println(" =======DELETE ======= ");
		delete(em2.getId());
		System.out.println(" =======READ =======");
		List<CorpEmployee> ems3 = read();
		for(CorpEmployee e: ems3) {
			System.out.println(e.toString());
		}
		System.out.println(" =======DELETE ALL ======= ");
		deleteAll();
		System.out.println(" =======READ =======");
		List<CorpEmployee> ems4 = read();
		for(CorpEmployee e: ems4) {
			System.out.println(e.toString());
		}
		System.exit(0);
	}

	public static SessionFactory getSessionFactory() {
//		Configuration configuration = new Configuration().configure();
	    // for mapping .addAnnotatedClass(CorpEmployee.class);
		Configuration configuration = new Configuration().addAnnotatedClass(CorpEmployee.class);
		
//		Configuration configuration = new Configuration(); -- look up properties file
//		Configuration configuration = new Configuration().configure(); -- look up cfg.xml file
		
		
//		return new Configuration().configure().buildSessionFactory();
//return new AnnotationConfiguration().configure().buildSessionFactory();

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static Integer create(CorpEmployee e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getId();

	}

	public static List<CorpEmployee> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<CorpEmployee> employees = session.createQuery("FROM CorpEmployee").list();
		session.close();
		System.out.println("Found " + employees.size() + " Employees");
		return employees;

	}

	public static void update(CorpEmployee e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		CorpEmployee em = (CorpEmployee) session.load(CorpEmployee.class, e.getId());
		em.setName(e.getName());
		em.setAge(e.getAge());
		session.getTransaction().commit();
		System.out.println("Successfully updated " + e.toString());

	}

	public static void delete(Integer id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		CorpEmployee e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + e.toString());

	}

	public static CorpEmployee findByID(Integer id) {
		Session session = getSessionFactory().openSession();
		CorpEmployee e = (CorpEmployee) session.load(CorpEmployee.class, id);
		session.close();
		return e;
	}
	
	public static void deleteAll() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM CorpEmployee ");
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted all CorpEmployees.");

	}
	
	/**
CREATE TABLE `company`.`CorpEmployee` (
	id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(20) default NULL,
   age INT  default NULL,
   PRIMARY KEY (id)
);
	 */

}
