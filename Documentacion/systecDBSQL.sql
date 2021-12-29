CREATE SCHEMA systec;

USE systec;

CREATE TABLE IF NOT EXISTS usuario(
    codigo VARCHAR(45),
    password VARCHAR(45),
    PRIMARY KEY(codigo)
);

CREATE TABLE IF NOT EXISTS producto(
    codigo VARCHAR(45) NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    tipo VARCHAR(45),
    precio_unitario DOUBLE NOT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE IF NOT EXISTS inventario(
    codigo_producto VARCHAR(45) NOT NULL,
    cantidad_existente INT NOT NULL,
    PRIMARY KEY (codigo_producto),
    FOREIGN KEY(codigo_producto) REFERENCES producto(codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS venta(
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS factura(
    id INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    total_pago DOUBLE NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    cantidad_productos INT NOT NULL,
    codigo_producto VARCHAR(45) NOT NULL,
    usuario VARCHAR(45) NOT NULL,
    id_venta INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(codigo_producto) REFERENCES producto(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(usuario) REFERENCES usuario(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_venta) REFERENCES venta(id) ON DELETE CASCADE ON UPDATE CASCADE
);

USE systec;
INSERT INTO usuario(codigo, password) VALUES('user', 'adminSystec');
