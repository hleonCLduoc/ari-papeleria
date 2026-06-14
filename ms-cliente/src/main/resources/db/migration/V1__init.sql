CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          rut VARCHAR(15) NOT NULL UNIQUE,
                          nombre VARCHAR(255) NOT NULL,
                          apellido VARCHAR(255) NOT NULL,
                          correo VARCHAR(255) NOT NULL UNIQUE,
                          telefono VARCHAR(255) NOT NULL
);


INSERT INTO clientes (rut, nombre, apellido, correo, telefono) VALUES
                                                                   ('93113348-9', 'Elias', 'Torres', 'eli.torres@example.com', '+56911111111'),
                                                                   ('98765432-1', 'Señor', 'Lápiz', 'senor.lapiz@example.com', '+56922222222'),
                                                                   ('87654321-2', 'Emilio', 'Soto', 'emi.so@example.com', '+56933333333'),
                                                                   ('45664789-8', 'Calcetín', 'Con Rombos Man', 'calcetin.conrombos@example.com', '+56944444444'),
                                                                   ('88778833-8', 'Tamara', 'Rubio', 'tam.rubio@example.com', '+56955555555'),
                                                                   ('23456789-0', 'Nathalie', 'Fuentes', 'na.fuen@example.com', '+56966666666'),
                                                                   ('87334421-2', 'Guillermo', 'Villacura', 'guille.villa@example.com', '+56977777777'),
                                                                   ('67654321-2', 'Diente', 'De Oro', 'diente.deoro@example.com', '+56988888888'),
                                                                   ('89543213-8', 'Victor', 'Gallardo', 'vi.gallard@example.com', '+56999999999'),
                                                                   ('34567890-3', 'Chico', 'Terry', 'chico.terry@example.com', '+56900000000');