package persistence;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import testUtils.Database;
import java.util.List;

import javax.persistence.OptimisticLockException;


/**
 * The type User dao test.
 */
class UserDaoTest {

    /**
     * The Dao.
     */
    GenericDao<User> dao;
    private static final Logger logger = LogManager.getLogger(UserDaoTest.class);
    private static SessionFactory sessionFactory;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        dao = new GenericDao<>(User.class);
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * Tear down.
     */
    @AfterAll
    static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * Test get session success.
     */
    @Test
    void testGetSessionSuccess() {
        try (Session session = sessionFactory.openSession()) {
            assertNotNull(session);
        }
    }

    /**
     * Test get by id success.
     */
    @Test
    void testGetByIdSuccess() {
        User retrievedUser = (User) dao.getById(1);
        logger.info("Retrieved user: " + retrievedUser);
        assertNotNull(retrievedUser);
        assertEquals("Kevg", retrievedUser.getUsername());
    }

    @Test
    void testGetByUsernameSuccess() {
        User retrievedUser = (User) dao.getByUsername("Kevg");
        logger.info("Retrieved user: " + retrievedUser);
        assertNotNull(retrievedUser);
        assertEquals("Kevg", retrievedUser.getUsername());
    }

    /**
     * Test save or update success.
     */
    @Test
    void testSaveOrUpdateSuccess() {
        User userToUpdate = dao.getById(3);
        userToUpdate.setUsername("Herbert");
        dao.saveOrUpdate(userToUpdate);

        User updatedUser = dao.getById(3);
        assertNotNull(updatedUser);
        assertEquals("Herbert", updatedUser.getUsername());
    }

    /**
     * Test insert success.
     */
    @Test
    void testInsertSuccess() {
        User newUser = new User();
        newUser.setUsername("JohnDoe");
        newUser.setPasswordHash("5556656");

        int id = dao.insert(newUser);
        assertNotEquals(0, id);

        User insertedUser = (User) dao.getById(Integer.parseInt(String.valueOf(id)));
        assertNotNull(insertedUser);
        assertEquals("JohnDoe", insertedUser.getUsername());
    }

    /**
     * Test delete success.
     */
    @Test
    void testDeleteSuccess() {
        dao.delete(dao.getById(2));
        assertNull(dao.getById(2));
    }

    /**
     * Test get all success.
     */
    @Test
    void testGetAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
    }

    /**
     * Test update failure.
     */
    @Test
    void testUpdateFailure() {
        User nonExistingRecord = new User();
        nonExistingRecord.setId(9999);
        nonExistingRecord.setUsername("JohnDoe");
        nonExistingRecord.setPasswordHash("123456");

        assertThrows(OptimisticLockException.class, () -> dao.saveOrUpdate(nonExistingRecord));
    }

    /**
     * Test delete failure.
     */
    @Test
    void testDeleteFailure() {
        User nonExistingRecord = new User();
        nonExistingRecord.setId(9999);

        assertThrows(OptimisticLockException.class, () -> dao.delete(nonExistingRecord));
    }

    /**
     * Test get by non existent id.
     */


    @Test
    void testGetByNonExistentId() {
        User nonExistingRecord = dao.getById(9999);
        assertNull(nonExistingRecord);
    }
}
