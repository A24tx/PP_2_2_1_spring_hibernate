package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByCar(String model, int series) {
        String hqlQuery = "select distinct u from User u, Car c left join fetch u.car where u.car.series =?1 and u.car.model =?2";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlQuery);
        query.setParameter(1, series);
        query.setParameter(2, model);

        return query.getSingleResult();
    }

}
