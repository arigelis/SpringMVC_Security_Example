package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {
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
        Query userByNameQuery = entityManager.createQuery("from User where name = :name").setParameter("name", name);

        User userByName = (User) userByNameQuery.getSingleResult();
        if (userByName == null) {
            System.out.println("User not found!");
            return null;
        }
        Query rolesListByUserId = entityManager.createQuery(
                "select rs.id,rs.role_name from roles_sec rs, users_roles ur where rs.id = ur.role_id and ur.user_id = :user_id"
        ).setParameter("user_id", userByName.getId());

        Set<Role> rolesSet = (Set<Role>) rolesListByUserId.getResultList();


        userByName.setRoles(rolesSet);
        return userByName;
    }
}

