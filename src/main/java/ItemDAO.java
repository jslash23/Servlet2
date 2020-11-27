import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;

import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class ItemDAO {

    SessionFactory sessionFactory;

    public Item daoRead(String ds) {
        Item item = new Item();
        try (Session session = createSessionFactory().openSession()) {

            org.hibernate.query.Query query = session.createQuery("from Item where description = :ds");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("ds", ds);
            List list = query.list();

            //close session/tr
            transaction.commit();

            for (Object l : list) {
                item = (Item) l;
            }
            return item;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
    }

  /*  public void daoSave(Item item) throws HibernateException, IOException {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            session.save(item);
            transaction.commit();
            System.out.println("Save Item done ");
            throw new IOException();
        } catch (IOException e) {
            System.err.println("Something wrong !!!!!!!!!!!!!!!!!!!!!!?????????????????");
            e.printStackTrace();

        } catch (HibernateException e) {
            System.err.println();

            e.printStackTrace();
            System.err.println("!!!!!!!" +
                    "cath worked " + "Save Item failed!!!" + e.getMessage());
        }
    }*/




    public void daoUpdate(long id) {
        Item item = new Item();
        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            Item findItem = findById(id);
            findItem.setName("TEST");
            //action
            session.update(findItem);
            //close session/tr
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }



 /*  public void daoDelete(long idn) throws IOException{

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("delete from  Item where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", idn);
            query.executeUpdate();
            //close session/tr
            transaction.commit();
           throw  new IOException();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        catch (HibernateException e) {
            System.err.println("Select from Item failed" + e.getMessage());
        }

    }*/

    public Item findById(Long id) {
        Item item = new Item();

        try (Session session = createSessionFactory().openSession()) {
            org.hibernate.query.Query query = session.createQuery("from Item where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", id);
            List list = query.list();

            //close session/tr
            transaction.commit();

            for (Object l : list) {
                item = (Item) l;
            }
            return item;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
    }


    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
//Hear we create new sessionFactory
           return new Configuration().configure().buildSessionFactory();
        }
        return  sessionFactory;
    }
}
