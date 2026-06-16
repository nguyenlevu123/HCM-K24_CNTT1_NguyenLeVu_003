CREATE DATABASE IF NOT EXISTS watch_store;

USE watch_store;

CREATE TABLE IF NOT EXISTS watches (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       model_name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    movement_type ENUM('MECHANICAL', 'AUTOMATIC', 'QUARTZ') NOT NULL,
    status ENUM('IN_STOCK', 'SOLD', 'REPAIRING') NOT NULL,
    is_deleted BIT(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (id)
    );

INSERT INTO watches (id, model_name, brand, price, movement_type, status, is_deleted)
VALUES
    (1, 'Seiko 5 Sports SRPD55K1', 'Seiko', 250.00, 'AUTOMATIC', 'IN_STOCK', b'0'),
    (2, 'Orient Bambino Version 4', 'Orient', 185.50, 'AUTOMATIC', 'IN_STOCK', b'0'),
    (3, 'Citizen Tsuyosa NJ0150-81E', 'Citizen', 320.00, 'AUTOMATIC', 'IN_STOCK', b'0'),
    (4, 'Casio MTP-V002D-1B3', 'Casio', 45.99, 'QUARTZ', 'IN_STOCK', b'0'),
    (5, 'Tissot PRX Powermatic 80', 'Tissot', 725.00, 'AUTOMATIC', 'IN_STOCK', b'0'),
    (6, 'Hamilton Khaki Field Mechanical', 'Hamilton', 595.00, 'MECHANICAL', 'IN_STOCK', b'0'),
    (7, 'Omega Speedmaster Professional', 'Omega', 7600.00, 'MECHANICAL', 'SOLD', b'0'),
    (8, 'Rolex Submariner Date', 'Rolex', 14500.00, 'AUTOMATIC', 'SOLD', b'0'),
    (9, 'Longines HydroConquest', 'Longines', 1450.00, 'AUTOMATIC', 'REPAIRING', b'0'),
    (10, 'Casio G-Shock GA-2100-1A1', 'Casio', 99.00, 'QUARTZ', 'IN_STOCK', b'0'),
    (11, 'Grand Seiko Heritage SBGA211', 'Grand Seiko', 6200.00, 'QUARTZ', 'IN_STOCK', b'0'),
    (12, 'Tag Heuer Carrera Calibre 5', 'TAG Heuer', 2850.00, 'AUTOMATIC', 'REPAIRING', b'0'),
    (13, 'Vintage Manual Wind Dress Watch', 'Universal Geneve', 980.00, 'MECHANICAL', 'IN_STOCK', b'0'),
    (14, 'Bulova Lunar Pilot', 'Bulova', 550.00, 'QUARTZ', 'IN_STOCK', b'0'),
    (15, 'Deleted Sample Watch', 'Demo', 10.00, 'QUARTZ', 'IN_STOCK', b'1')
    ON DUPLICATE KEY UPDATE
    model_name = VALUES(model_name),
    brand = VALUES(brand),
    price = VALUES(price),
    movement_type = VALUES(movement_type),
    status = VALUES(status),
    s_deleted = VALUES(is_deleted);