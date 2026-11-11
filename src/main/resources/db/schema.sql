-- =====================================================================
-- Reviste Marketplace - Esquema MySQL/MariaDB para XAMPP
-- Ejecutar en phpMyAdmin (pestaña SQL) o con: mysql -u root < schema.sql
-- =====================================================================

CREATE DATABASE IF NOT EXISTS reviste_marketplace_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE reviste_marketplace_db;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario     INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    correo         VARCHAR(254) NOT NULL UNIQUE,
    contrasena     VARCHAR(100) NOT NULL,
    telefono       VARCHAR(10)  NOT NULL,
    direccion      VARCHAR(200) NOT NULL,
    fecha_registro DATETIME     NOT NULL,
    activo         BOOLEAN      NOT NULL DEFAULT TRUE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(60)  NOT NULL UNIQUE,
    descripcion  VARCHAR(255),
    activo       BOOLEAN      NOT NULL DEFAULT TRUE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS prenda (
    id_prenda         INT AUTO_INCREMENT PRIMARY KEY,
    id_vendedor       INT            NOT NULL,
    id_categoria      INT            NOT NULL,
    nombre            VARCHAR(100)   NOT NULL,
    descripcion       VARCHAR(2000)  NOT NULL,
    marca             VARCHAR(60)    NOT NULL,
    talla             VARCHAR(12)    NOT NULL,
    condicion         VARCHAR(10)    NOT NULL,
    precio            DECIMAL(12, 2) NOT NULL,
    stock             INT            NOT NULL,
    estado            VARCHAR(12)    NOT NULL,
    fecha_publicacion DATETIME       NOT NULL,
    CONSTRAINT fk_prenda_vendedor  FOREIGN KEY (id_vendedor)  REFERENCES usuario (id_usuario),
    CONSTRAINT fk_prenda_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria),
    CONSTRAINT ck_prenda_condicion CHECK (condicion IN ('EXCELENTE', 'BUENO', 'REGULAR')),
    CONSTRAINT ck_prenda_estado    CHECK (estado IN ('DISPONIBLE', 'VENDIDA')),
    CONSTRAINT ck_prenda_precio    CHECK (precio > 0),
    CONSTRAINT ck_prenda_stock     CHECK (stock >= 0)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS foto_prenda (
    id_foto   INT AUTO_INCREMENT PRIMARY KEY,
    id_prenda INT          NOT NULL,
    ruta      VARCHAR(500) NOT NULL,
    orden     INT          NOT NULL,
    CONSTRAINT fk_foto_prenda FOREIGN KEY (id_prenda) REFERENCES prenda (id_prenda) ON DELETE CASCADE,
    CONSTRAINT uq_foto_prenda_orden UNIQUE (id_prenda, orden),
    CONSTRAINT ck_foto_orden CHECK (orden BETWEEN 0 AND 4)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS carrito (
    id_carrito     INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario     INT      NOT NULL UNIQUE,
    fecha_creacion DATETIME NOT NULL,
    CONSTRAINT fk_carrito_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS item_carrito (
    id_item_carrito INT AUTO_INCREMENT PRIMARY KEY,
    id_carrito      INT NOT NULL,
    id_prenda       INT NOT NULL,
    cantidad        INT NOT NULL,
    CONSTRAINT fk_item_carrito_carrito FOREIGN KEY (id_carrito) REFERENCES carrito (id_carrito) ON DELETE CASCADE,
    CONSTRAINT fk_item_carrito_prenda  FOREIGN KEY (id_prenda)  REFERENCES prenda (id_prenda)   ON DELETE CASCADE,
    CONSTRAINT ck_item_carrito_cantidad CHECK (cantidad >= 1)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS orden (
    id_orden       INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario     INT            NOT NULL,
    monto_total    DECIMAL(12, 2) NOT NULL,
    estado         VARCHAR(12)    NOT NULL,
    fecha_creacion DATETIME       NOT NULL,
    CONSTRAINT fk_orden_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT ck_orden_estado  CHECK (estado IN ('PENDIENTE', 'PAGADA', 'CANCELADA'))
) ENGINE = InnoDB;

-- nombre_prenda y precio_unitario son una copia (snapshot) al momento de la compra
CREATE TABLE IF NOT EXISTS item_orden (
    id_item_orden   INT AUTO_INCREMENT PRIMARY KEY,
    id_orden        INT            NOT NULL,
    id_prenda       INT            NOT NULL,
    nombre_prenda   VARCHAR(100)   NOT NULL,
    cantidad        INT            NOT NULL,
    precio_unitario DECIMAL(12, 2) NOT NULL,
    CONSTRAINT fk_item_orden_orden  FOREIGN KEY (id_orden)  REFERENCES orden (id_orden) ON DELETE CASCADE,
    CONSTRAINT fk_item_orden_prenda FOREIGN KEY (id_prenda) REFERENCES prenda (id_prenda),
    CONSTRAINT ck_item_orden_cantidad CHECK (cantidad >= 1)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS metodo_pago (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(60)  NOT NULL,
    descripcion    VARCHAR(255),
    activo         BOOLEAN      NOT NULL DEFAULT TRUE
) ENGINE = InnoDB;

-- Pago interno de la plataforma (sin pasarela externa)
CREATE TABLE IF NOT EXISTS pago (
    id_pago        INT AUTO_INCREMENT PRIMARY KEY,
    id_orden       INT            NOT NULL,
    id_metodo_pago INT            NOT NULL,
    monto          DECIMAL(12, 2) NOT NULL,
    estado         VARCHAR(12)    NOT NULL,
    fecha_pago     DATETIME       NOT NULL,
    CONSTRAINT fk_pago_orden       FOREIGN KEY (id_orden)       REFERENCES orden (id_orden),
    CONSTRAINT fk_pago_metodo_pago FOREIGN KEY (id_metodo_pago) REFERENCES metodo_pago (id_metodo_pago),
    CONSTRAINT ck_pago_estado      CHECK (estado IN ('COMPLETADO', 'FALLIDO'))
) ENGINE = InnoDB;
