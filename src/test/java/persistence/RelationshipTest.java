package persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GenericDao;
import entity.User;
import entity.Attendance;
import testUtils.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The type Relationship test.
 */
class RelationshipTest {

    /**
     * The Attendance dao.
     */
    GenericDao<Attendance> attendanceDao;
    /**
     * The User dao.
     */
    GenericDao<User> userDao;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        testUtils.Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        userDao = new GenericDao<>(User.class);
        attendanceDao = new GenericDao<>(Attendance.class);
    }

    /**
     * Test one to one relationship.
     */
    @Test
    void testOneToOneRelationship() {
        User newUser = new User();
        newUser.setUsername("Steve");
        newUser.setPasswordHash("656ge65g");

        Attendance attendance1 = new Attendance();
        attendance1.setClockInTime(LocalDateTime.parse("2024-02-22 11:00:00", DATE_TIME_FORMATTER));
        attendance1.setClockOutTime(LocalDateTime.parse("2024-02-22 13:30:00", DATE_TIME_FORMATTER));
        attendance1.setDate(LocalDate.parse("2024-02-20"));
        attendance1.setUser(newUser);
        Attendance attendance2 = new Attendance();
        attendance2.setClockInTime(LocalDateTime.parse("2024-02-23 11:45:00", DATE_TIME_FORMATTER));
        attendance2.setClockOutTime(LocalDateTime.parse("2024-02-23 15:15:00", DATE_TIME_FORMATTER));
        attendance2.setDate(LocalDate.parse("2024-02-20"));
        attendance2.setUser(newUser);

        int userId = userDao.insert(newUser);
        int attendanceId1 = attendanceDao.insert(attendance1);
        int attendanceId2 = attendanceDao.insert(attendance2);

        User retrievedUser = userDao.getById(userId);

        assertEquals(2, retrievedUser.getAttendance().size());
    }

    /**
     * Test many to one relationship.
     */
    @Test
    void testManyToOneRelationship() {
        User newUser = new User();
        newUser.setUsername("JohnDoe");
        newUser.setPasswordHash("316513533155351351");

        int userId = userDao.insert(newUser);

        Attendance attendance = new Attendance();
        attendance.setClockInTime(LocalDateTime.parse("2024-02-20 11:00:00", DATE_TIME_FORMATTER));
        attendance.setClockOutTime(LocalDateTime.parse("2024-02-20 13:00:00", DATE_TIME_FORMATTER));
        attendance.setDate(LocalDate.parse("2024-02-20"));
        attendance.setUser(newUser);

        int attendanceId = attendanceDao.insert(attendance);

        Attendance retrievedAttendance = attendanceDao.getById(attendanceId);

        assertNotNull(retrievedAttendance.getUser());
        assertEquals(userId, retrievedAttendance.getUser().getId());
    }
}
