package Island.persistance;


import Island.Results;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResultsManagement {

    public static int addResults(Results results){

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(results);

        transaction.commit();
        session.close();

        return results.getId();
    }

    public static int updateResults(Results results){

        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        session.update(results);

        transaction.commit();
        session.close();

        return results.getId();
    }
}
