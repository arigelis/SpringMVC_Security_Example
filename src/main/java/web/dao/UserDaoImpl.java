package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {
    private final Map<String, User> userMap = Collections.singletonMap("test",
            new User(1L, "test", "test", Collections.singleton(new Role(1L, "admin")))); // name - уникальное значение, выступает в качестве ключа Map

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    @Transactional
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByName(String name) {

        Query result = entityManager.createQuery("from User where name = :name").setParameter("name", name);
        List<User> usersList = (List<User>) result.getResultList();

        if (!usersList.get(0).getName().equalsIgnoreCase(name)) {
            return null;
        }


        usersList.get(0).setRoles(new HashSet<Role>(Arrays.asList(new Role(1L, "user"))));
        return usersList.get(0);
    }
}

