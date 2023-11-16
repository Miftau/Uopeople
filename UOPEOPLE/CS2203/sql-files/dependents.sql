CREATE TABLE dependents (
    dependent_id INT PRIMARY KEY,
    employee_id INT,
    dependent_name VARCHAR(50),
    relationship VARCHAR(50),
    birth_date DATE
);

INSERT INTO dependents (dependent_id, employee_id, dependent_name, relationship, birth_date)
VALUES
    (1, 101, 'Sarah Doe', 'Child', '2003-07-12'),
    (2, 102, 'Emily Smith', 'Spouse', '1980-03-05'),
     (3, 103, 'Ramah Kelly', 'Sibling', '2003-07-12'),
      (4, 104, 'Kuala Xabi', 'Brother', '2003-07-12');

