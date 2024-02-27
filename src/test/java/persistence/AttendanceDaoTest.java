package persistence;

import entity.Attendance;
import entity.User;
import javassist.NotFoundException;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.OptimisticLockException;


/**
 * The type Attendance dao test.
 */
class AttendanceDaoTest {

    /**
     * The Dao.
     */
    GenericDao<Attendance> dao;
    private static final Logger logger = LogManager.getLogger(AttendanceDaoTest.class);
    private static SessionFactory sessionFactory;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        dao = new GenericDao<>(Attendance.class);
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
        Attendance retrievedHours = (Attendance) dao.getById(1);
        assertNotNull(retrievedHours);
        assertEquals(LocalDateTime.parse("2024-02-17 09:00:00", DATE_TIME_FORMATTER), retrievedHours.getClockInTime());
    }

    /**
     * Test save or update success.
     */
    @Test
    void testSaveOrUpdateSuccess() {
        Attendance hoursToUpdate = dao.getById(3);
        hoursToUpdate.setClockOutTime(LocalDateTime.parse("2024-02-17 11:00:00", DATE_TIME_FORMATTER));
        dao.saveOrUpdate(hoursToUpdate);

        Attendance updatedHours = dao.getById(3);
        assertNotNull(updatedHours);
        assertEquals(LocalDateTime.parse("2024-02-17 11:00:00", DATE_TIME_FORMATTER), updatedHours.getClockOutTime());
    }

    /**
     * Test insert success.
     */
    @Test
    void testInsertSuccess() {
        Attendance newHours = new Attendance();
        newHours.setClockInTime(LocalDateTime.parse("2024-02-18 08:00:00", DATE_TIME_FORMATTER));
        newHours.setClockOutTime(LocalDateTime.parse("2024-02-18 16:00:00", DATE_TIME_FORMATTER));

        int id = dao.insert(newHours);
        assertNotEquals(0, id);

        Attendance insertedHours = (Attendance) dao.getById(Integer.parseInt(String.valueOf(id)));
        assertNotNull(insertedHours);
        assertEquals(LocalDateTime.parse("2024-02-18 08:00:00", DATE_TIME_FORMATTER), insertedHours.getClockInTime());
        assertEquals(LocalDateTime.parse("2024-02-18 16:00:00", DATE_TIME_FORMATTER), insertedHours.getClockOutTime());
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
        List<Attendance> attendance = dao.getAll();
        assertEquals(3, attendance.size());
    }

    /**
     * Test update failure.
     */
    @Test
    void testUpdateFailure() {
        Attendance nonExistingRecord = new Attendance();
        nonExistingRecord.setId(9999);
        nonExistingRecord.setClockInTime(LocalDateTime.parse("2024-02-18 08:00:00", DATE_TIME_FORMATTER));
        nonExistingRecord.setClockOutTime(LocalDateTime.parse("2024-02-18 16:00:00", DATE_TIME_FORMATTER));

        assertThrows(OptimisticLockException.class, () -> dao.saveOrUpdate(nonExistingRecord));
    }

    /**
     * Test delete failure.
     */
    @Test
    void testDeleteFailure() {
        Attendance nonExistingRecord = new Attendance();
        nonExistingRecord.setId(9999);

        assertThrows(OptimisticLockException.class, () -> dao.delete(nonExistingRecord));
    }

    /**
     * Test get by non existent id.
     */
    @Test
    void testGetByNonExistentId() {
        Attendance nonExistingRecord = dao.getById(9999);
        assertNull(nonExistingRecord);
    }
}