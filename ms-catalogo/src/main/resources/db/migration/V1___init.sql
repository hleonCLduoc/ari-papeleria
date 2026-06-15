CREATE TABLE IF NOT EXISTS productos (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         sku VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT DEFAULT 0,
    estado VARCHAR(20) DEFAULT 'Disponible'
    );

INSERT IGNORE INTO productos (sku, nombre, precio, stock, estado) VALUES
    ('CUAD-UNI-01', 'Cuaderno Universitario', 1500, 50, 'Disponible'),
    ('LAP-PAST-02', 'Lápiz Pasta Azul', 300, 100, 'Disponible'),
    ('GOM-MIG-03', 'Goma de borrar miga', 250, 80, 'Disponible'),
    ('DES-AMA-04', 'Destacador amarillo', 600, 60, 'Disponible'),
    ('PEG-BAR-05', 'Pegamento en barra 36g', 1200, 40, 'Disponible'),
    ('TIJ-ESC-06', 'Tijera escolar punta roma', 850, 35, 'Disponible'),
    ('CAR-COL-07', 'Block de Cartulina de colores', 1800, 25, 'Disponible'),
    ('COR-LIQ-08', 'Corrector lápiz', 900, 45, 'Disponible'),
    ('PLU-COL-09', 'Plumones de colores x12', 2500, 20, 'Disponible'),
    ('REG-MET-10', 'Regla metálica 30cm', 1000, 30, 'Disponible');