CREATE TABLE productos (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           sku VARCHAR(50) UNIQUE NOT NULL,
                           nombre VARCHAR(100) NOT NULL,
                           precio DOUBLE NOT NULL,
                           stock INT DEFAULT 0,
                           estado VARCHAR(20) DEFAULT 'Disponible'
);

INSERT INTO productos (sku, nombre, precio, stock, estado) VALUES
                                                               ('CUAD-UNI-01', 'Cuaderno Universitario', 1500, 50, 'Disponible'),
                                                               ('LAP-PAST-02', 'Lápiz Pasta Azul', 300, 100, 'Disponible');