CREATE TABLE locations (
    location_id INT PRIMARY KEY,
    street_address VARCHAR(50),
    postal_code VARCHAR(20),
    city VARCHAR(50),
    state_province VARCHAR(50),
    country_id CHAR(2)
);

INSERT INTO locations (location_id, street_address, postal_code, city, state_province, country_id)
VALUES
    (1, '123 Main St', '12345', 'New York', 'NY', 'US'),
    (2, '456 Elm St', '67890', 'Los Angeles', 'CA', 'US');
