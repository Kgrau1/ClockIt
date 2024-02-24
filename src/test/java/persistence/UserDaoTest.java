package persistence;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import testUtils.Database;


class UserDaoTest {

    GenericDao dao;
    private static final Logger logger = LogManager.getLogger(UserDaoTest.class);

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        dao = new GenericDao(User.class);
    }

    @Test
    void getSession() {

    }

    @Test
    void testGetById() {
        User retrievedUser = (User) dao.getById(1);
        logger.info("Retrieved user: " + retrievedUser);
        assertNotNull(retrievedUser);
        assertEquals("Kevg", retrievedUser.getUsername());
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void insert() {
        User newUser = new User("JohnDoe", "5556656");
        int id = dao.insert(newUser);
        assertNotEquals(0, id);

        User insertedUser = (User) dao.getById(Integer.parseInt(String.valueOf(id)));
        assertNotNull(insertedUser);
        assertEquals("JohnDoe", insertedUser.getUsername());
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}