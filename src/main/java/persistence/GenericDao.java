package persistence;

import entity.Attendance;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.*;
import static persistence.SessionFactoryProvider.sessionFactory;

public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public GenericDao() {

    }

    /**
     * Returns an open session from the SessionFactory.
     *
     * @return the session
     */
    public Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Gets an entity by id.
     * @param id entity id to search by
     * @return the entity
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Gets by username.
     *
     * @param <T>      the type parameter
     * @param username the username
     * @return the by username
     */
    public <T> T getByUsername(String username) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery(type);
        Root<T> root = (Root<T>) query.from(type);

        query.select(root).where(builder.equal(root.get("username"), username));

        T entity = session.createQuery(query).uniqueResult();
        session.close();
        return entity;
    }


    /**
     * Save or update.
     *
     * @param entity the entity
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Insert int.
     *
     * @param entity the new User
     * @return the int
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete the entity.
     * @param entity the entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Gets all entities.
     * @return all entities
     */
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Gets by property equal.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Find most recent attendance by user t.
     *
     * @param <T>  the type parameter
     * @param user the user
     * @return the t
     */
    public <T> T findMostRecentAttendanceByUser(User user) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery(type);
        Root<T> root = (Root<T>) query.from(type);

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<T> subRoot = (Root<T>) subquery.from(type);
        subquery.select(builder.max(subRoot.get("id")))
                .where(builder.equal(subRoot.get("user"), user));

        query.select(root)
                .where(builder.equal(root.get("id"), subquery))
                .orderBy(builder.desc(root.get("id")));

        T result = session.createQuery(query)
                .setMaxResults(1)
                .uniqueResult();

        session.close();

        return result;
    }

}