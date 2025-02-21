CREATE TABLE prices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT NOT NULL,
    product_id INT NOT NULL,
    price_list INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    curr VARCHAR(3) NOT NULL DEFAULT 'USD'
);

INSERT INTO prices (brand_id, product_id, price_list, start_date, end_date, priority, price, curr)
VALUES
    (1, 35455, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
    (1, 35455, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR'),
    (1, 35455, 3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 'EUR'),
    (1, 35455, 4, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 'EUR');


INSERT INTO prices (brand_id, product_id, price_list, start_date, end_date, priority, price, curr)
VALUES
    (1, 35456, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
    (1, 35456, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR'),
    (1, 35456, 3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 'EUR'),
    (1, 35456, 4, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 'EUR');

