DELETE FROM attendance;
DELETE FROM users;

INSERT INTO users VALUES (1, 'Kevg', '5151321nbmbbhj', 100, 1), (2, 'jSmith', '5151321', 101, 0), (3, 'grapes', '5151321nbmbbhj', 110, 0);
INSERT INTO attendance VALUES (1, 1, '2024-02-17 09:00:00', '2024-02-17 17:00:00', '2024-02-17', 0), (2, 3, '2024-02-17 09:30:00', null, '2024-02-17', 1), (3, 2, '2024-02-17 10:00:00', '2024-02-17 15:00:00', '2024-02-17', 0);
