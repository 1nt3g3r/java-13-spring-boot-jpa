CREATE TABLE order_status (
    order_id VARCHAR(128),
    status VARCHAR(100),
    created_at DATETIME,
    PRIMARY KEY (order_id, status, created_at)
)