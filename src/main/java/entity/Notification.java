package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

/**
 * The type Notification.
 */
@Entity(name="Notification")
@Table(name="notifications")
public class Notification {

    @Id
    private int id;
    @Column(name = "notification_id")
    private String notificationType;
    @Column(name = "notification_message")
    private String notificationMessage;
    @Column(name = "timestamp")
    private Date timestamp;

    /**
     * Instantiates a new Notification.
     */
    public Notification() {
    }

    /**
     * Instantiates a new Notification.
     *
     * @param id                  the id
     * @param notificationType    the notification type
     * @param notificationMessage the notification message
     * @param timestamp           the timestamp
     */
    public Notification(int id, String notificationType, String notificationMessage, Date timestamp) {
        this.id = id;
        this.notificationType = notificationType;
        this.notificationMessage = notificationMessage;
        this.timestamp = timestamp;
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
     * Gets notification type.
     *
     * @return the notification type
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * Sets notification type.
     *
     * @param notificationType the notification type
     */
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Gets notification message.
     *
     * @return the notification message
     */
    public String getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * Sets notification message.
     *
     * @param notificationMessage the notification message
     */
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
