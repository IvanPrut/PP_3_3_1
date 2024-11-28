package org.firstwebproject.pp_3_3_1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.firstwebproject.pp_3_3_1.models.User;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory entityManagerFactory;

    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public void saveUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateUser(Long id, User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User userOrigin = em.find(User.class, id);
        userOrigin.setName(user.getName());
        userOrigin.setSurname(user.getSurname());
        userOrigin.setEmail(user.getEmail());
        em.merge(userOrigin);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteUser(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User userOrigin = em.find(User.class, id);
        em.remove(userOrigin);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User findUserById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> users = em.createQuery("from User").getResultList();
        em.close();
        return users;
    }
}
