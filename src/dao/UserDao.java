package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtils;

public class UserDao {

    public User getById(int id) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();
            String sql = "Select u from " + User.class.getName() + " u "
                    + "where u.id =:id ";
            Query<User> query = session.createQuery(sql);
            query.setParameter("id", id);
            User user = query.getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public User getUserByLoginPassword(final String login, final String password) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();
            String sql = "Select u from " + User.class.getName() + " u "
                    + "where u.email =:login and u.password =:password ";
            Query<User> query = session.createQuery(sql);
            query.setParameter("login", login);
            query.setParameter("password", password);
            User user = query.getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public boolean userIsExist(final String login, final String password) {
        try {
            User u = getUserByLoginPassword(login, password);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
