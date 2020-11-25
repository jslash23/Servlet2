import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class ItemDAO {

   private  static Item item = new Item();
    SessionFactory sessionFactory;

    public Item daoRead() {
        return null;
    }

   /* public static Long CreateId(Integer min, Integer max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        Long  n = (Long) i;
        return n;
    }*/

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
            System.err.println("Something wrong !!!!!!!!!!!!!!!!!!!!!!?????????????????");
            e.printStackTrace();

        }
        catch (HibernateException e ) {
            System.err.println();

            e.printStackTrace();
            System.err.println("!!!!!!!" +
                    "cath worked " + "Save Item failed!!!" + e.getMessage());
        }
    }



    public void daoUpdate() {

            try (Session session = createSessionFactory().openSession()) {

                Transaction transaction = session.getTransaction();
                transaction.begin();

                Long nr = item.getId();
                Item findItem = findById(nr);
                findItem.setName("Pro");
                //action
                session.update(findItem);
                //close session/tr
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Nothing update!" + e.getMessage());
            }
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


    public   SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
