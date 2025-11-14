package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
    public static void main(String[] args) {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session session = sf.openSession();
            System.out.println("✅ Hibernate conectou ao MySQL!");
            session.close();
            sf.close();
        } catch (Exception e) {
            System.out.println("❌ Hibernate NÃO conectou!");
            e.printStackTrace();
        }
    }
}
