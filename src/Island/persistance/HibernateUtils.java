package Island.persistance;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;

    public static synchronized SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getSession()
    {
        return getSessionFactory().openSession();
    }

    public static void shutdown()
    {
        getSessionFactory().close();
    }
}
