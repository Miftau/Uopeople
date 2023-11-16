CREATE TABLE countries (
    country_id CHAR(2) PRIMARY KEY,
    country_name VARCHAR(50)
);
INSERT INTO countries (country_id, country_name)
VALUES
    ('US', 'United States'),
    ('CA', 'Canada');
