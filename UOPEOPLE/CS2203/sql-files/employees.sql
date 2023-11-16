CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    hire_date DATE,
    department_id INT,
    job_id VARCHAR(10),
    manager_id INT,
    salary DECIMAL(10, 2)
);

INSERT INTO employees (employee_id, first_name, last_name, hire_date, department_id, job_id, manager_id, salary)
VALUES
    (101, 'John', 'Doe', '2000-01-15', 1, 'Manager', NULL, 60000.00),
    (102, 'Jane', 'Smith', '2005-03-20', 2, 'Assistant', 100, 145000.00),
    (103, 'Roda', 'Bala', '2002-01-15', 2, 'Manager', NULL, 60000.00),
    (104, 'Kila', 'Kote', '2005-03-21', 3, 'Assistant', 101, 45000.00),
    (105, 'Para', 'Susan', '2000-04-15', 1, 'Manager', NULL, 80000.00),
    (106, 'Jane', 'Smith', '2006-03-24', 4, 'Assistant', 104, 45000.00),
    (107, 'John', 'Doe', '2003-01-17', 3, 'Manager', NULL, 60000.00),
    (108, 'Mosra', 'Cocker', '2007-06-20', 4, 'Assistant', 102, 45000.00),
    (109, 'Misler', 'Michwell', '2007-06-20', 4, 'Assistant', 102, 45000.00),
    (110, 'Mike', 'Johnson', '2008-08-10', 2, 'Analyst', 103, 50000.00);

