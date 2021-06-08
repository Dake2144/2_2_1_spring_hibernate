package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserOnCar(String model, int series) {
        List<User> users;
        Session session = sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("select u from User u where u.car.model = :model and u.car.series = :series", User.class)
                    .setParameter("model", model)
                    .setParameter("series", series)
                    .getResultList();
            session.getTransaction().commit();

        return users;
    }
}
