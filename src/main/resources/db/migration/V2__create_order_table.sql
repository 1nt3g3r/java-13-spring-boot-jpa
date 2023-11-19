CREATE TABLE chocolate_order (
    id VARCHAR(128) PRIMARY KEY,
    chocolate_type VARCHAR(100),
    quantity INT NOT NULL,
    price_per_item INT NOT NULL,
    customer_email VARCHAR(255),
    delivery_address VARCHAR(500),
    FOREIGN KEY (chocolate_type) REFERENCES chocolate(chocolate_type)
)