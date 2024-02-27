package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

/**
 * The type Attendance.
 */
@Entity
@Table(name="attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "attendance_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "clock_in_time")
    private LocalDateTime clockInTime;
    @Column(name = "clock_out_time")
    private LocalDateTime clockOutTime;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status")
    private boolean clokedStatus;

    /**
     * Instantiates a new Attendance.
     */
    public Attendance() {

    }

    /**
     * Instantiates a new Attendance.
     *
     * @param id           the id
     * @param clockInTime  the clock in time
     * @param clockOutTime the clock out time
     * @param date         the date
     * @param clokedStatus the cloked status
     */
    public Attendance(int id, LocalDateTime clockInTime, LocalDateTime clockOutTime, LocalDate date, boolean clokedStatus) {
        this.id = id;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.date = date;
        this.clokedStatus = clokedStatus;
    }

    /**
     * Gets clock out time.
     *
     * @return the clock out time
     */
    public LocalDateTime getClockOutTime() {
        return clockOutTime;
    }

    /**
     * Sets clock out time.
     *
     * @param clockOutTime the clock out time
     */
    public void setClockOutTime(LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Is cloked status boolean.
     *
     * @return the boolean
     */
    public boolean isClokedStatus() {
        return clokedStatus;
    }

    /**
     * Sets cloked status.
     *
     * @param clokedStatus the cloked status
     */
    public void setClokedStatus(boolean clokedStatus) {
        this.clokedStatus = clokedStatus;
    }

    /**
     * Gets clock in time.
     *
     * @return the clock in time
     */
    public LocalDateTime getClockInTime() {
        return clockInTime;
    }

    /**
     * Sets clock in time.
     *
     * @param clockInTime the clock in time
     */
    public void setClockInTime(LocalDateTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", clockInTime=" + clockInTime +
                ", clockOutTime=" + clockOutTime +
                ", date=" + date +
                ", clokedStatus=" + clokedStatus +
                '}';
    }
}
