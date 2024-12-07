package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure and build SessionFactory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Open session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Update operation using HQL with positional parameters
            String hqlUpdate = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            int updatedEntities = session.createQuery(hqlUpdate)
                                         .setParameter(1, "New Department Name")
                                         .setParameter(2, "New Location")
                                         .setParameter(3, 1) // Assuming departmentId = 1
                                         .executeUpdate();

            System.out.println("Number of updated records: " + updatedEntities);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
