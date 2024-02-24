DELETE FROM notifications;
DELETE FROM attendance;
DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles VALUES (100, 'admin', 1);
INSERT INTO users VALUES (1, 'Kevg', '5151321nbmbbhj', 100, 1);
INSERT INTO attendance VALUES (1, 1, '2024-02-17 09:00:00', '2024-02-17 17:00:00', '2024-02-17', 'Present', 1);
INSERT INTO notifications VALUES (1, 1, 'Type', 'Sample message', '2024-02-17 12:00:00', 1);
