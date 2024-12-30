INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
('John Doe', '1990-01-01', 'Junior', 900),
('Jane Smith', '1985-05-12', 'Senior', 6000),
('Alice Brown', '1992-03-15', 'Middle', 3000),
('Bob White', '1995-07-08', 'Trainee', 500),
('Max Black', '1980-11-22', 'Senior', 7000),
('Eve Green', '1997-02-27', 'Junior', 1500),
('Olivia Blue', '1988-09-14', 'Middle', 3500),
('Noah Red', '1993-10-10', 'Junior', 2000),
('Liam Gold', '1982-12-30', 'Senior', 8000),
('Sophia Silver', '2000-04-17', 'Trainee', 400);

INSERT INTO client (NAME) VALUES
('Acme Corp'),
('Tech Solutions'),
('Innovate Ltd'),
('Future Enterprises'),
('DreamWorks Inc');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
(1, '2023-01-01', '2023-06-01'),
(2, '2023-02-01', '2023-08-01'),
(3, '2023-03-01', '2024-03-01'),
(4, '2023-04-01', '2023-10-01'),
(5, '2023-05-01', '2024-05-01'),
(1, '2023-06-01', '2023-12-01'),
(2, '2023-07-01', '2024-01-01'),
(3, '2023-08-01', '2023-09-01'),
(4, '2023-09-01', '2024-06-01'),
(5, '2023-10-01', '2024-10-01');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8),
(5, 9), (5, 10),
(6, 1), (6, 3),
(7, 5), (7, 7),
(8, 2), (8, 4),
(9, 6), (9, 8),
(10, 9), (10, 10);
