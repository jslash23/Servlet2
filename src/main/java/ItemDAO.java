import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.IOException;
import java.util.List;


public class ItemDAO {
    MyServlet2 myServlet = new MyServlet2();
    Item item = new Item();
    SessionFactory sessionFactory;

    public Item daoRead() {
        return null;
    }

    public void daoSave(Item item) throws HibernateException, IOException {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            session.save(item);
            transaction.commit();
            System.out.println("Save Item done ");
            throw new IOException();
        }

        catch (IOException e) {
            e.printStackTrace();

        }
        catch (HibernateException e ) {
            System.err.println();

            e.printStackTrace();
            System.err.println("!!!!!!!" +
                    "cath worked " + "Save Item failed!!!" + e.getMessage());
        }
    }



    public Item daoUpdate() {
        return null;
    }

    public Item daoDelete(long idn) {
        return null;
    }


    public Item findById(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("from Item where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", id);
            List list = query.getResultList();
            //close session/tr
            transaction.commit();

            for (Object l : list) {
                item = (Item) l;
            }
            return item;
        } catch (HibernateException e) {
            System.err.println("Select from Hotel failed" + e.getMessage());
        }
        return item;
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().buildSessionFactory();
        }
        return sessionFactory;
    }
}
