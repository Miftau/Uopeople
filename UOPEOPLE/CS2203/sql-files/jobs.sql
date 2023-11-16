CREATE TABLE jobs (
    job_id VARCHAR(10) PRIMARY KEY,
    job_title VARCHAR(50),
    min_salary DECIMAL(10, 2),
    max_salary DECIMAL(10, 2)
);

INSERT INTO jobs (job_id, job_title, min_salary, max_salary)
VALUES
    ('Manager', 'Department Manager', 50000.00, 80000.00),
    ('Assistant', 'Department Assistant', 40000.00, 55000.00),
    ('Analyst', 'Data Analyst', 45000.00, 65000.00);
