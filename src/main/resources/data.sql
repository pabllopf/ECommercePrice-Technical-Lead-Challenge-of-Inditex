CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS prices (
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


-- Insert Users (Passwords should be hashed in a real system)
-- Insert Users with hashed passwords
INSERT INTO users (username, password, role) VALUES
 ('user1', '$2a$10$R.5gHT4DcYZPkMqHW6XeR.wuK5bkRe8eF2JcPMxbrC8hn2U34snDG', 'USER'),  -- Password: user1
 ('user2', '$2a$10$Yzd2aE9WZv2r1nQXCzkHDekPcWwQ0hCYMp74n/IlvmMoujHF67McC', 'USER'),  -- Password: user2
 ('admin', '$2a$10$wLZrrgHDUWZcwq6zb1dCL.CN8XErNdkcosnvit5izZVlgjiCBYEWe', 'ADMIN'), -- Password: admin
 ('superadmin', '$2a$10$YnB2tsoA/Qj677ct/NDVd.XysULfImMxgjg7POfHSfsnY448dMnUG', 'SUPERADMIN'); -- Password: superadmin


-- Insert Prices
INSERT INTO prices (brand_id, product_id, price_list, start_date, end_date, priority, price, curr) VALUES
    (1, 35455, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
    (1, 35455, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR'),
    (1, 35455, 3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 'EUR'),
    (1, 35455, 4, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 'EUR');

INSERT INTO prices (brand_id, product_id, price_list, start_date, end_date, priority, price, curr) VALUES
    (1, 35456, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 'EUR'),
    (1, 35456, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 'EUR'),
    (1, 35456, 3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 'EUR'),
    (1, 35456, 4, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 'EUR');
