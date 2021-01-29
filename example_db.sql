CREATE TABLE department
(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    working_hours varchar(255) NOT NULL
);

CREATE TABLE item
(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    cost numeric NOT NULL,
    department_id int NOT NULL REFERENCES department
);

INSERT INTO department (id, name, working_hours) VALUES
	(1, 'Отдел продажи столов', '8:00 - 17:00'),
	(2, 'Отдел продажи авторучек', '10:00 - 14:00'),
	(3, 'Отдел продажи пончиков', '9:00 - 22:00');
SELECT setval('department_id_seq', 3);

INSERT INTO item (name, cost, department_id) VALUES
    ('Стол письменный', 3500, 1),
    ('Стол обеденный', 2900, 1),
    ('Ручка шариковая', 10, 2),
    ('Ручка гелевая', 20, 2),
    ('Пончик с вареньем', 35, 3),
    ('Пончик со сгущёнкой', 35, 3);
