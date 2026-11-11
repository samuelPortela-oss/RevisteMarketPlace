-- =====================================================================
-- Datos iniciales (opcional). Ejecutar después de schema.sql
-- =====================================================================

USE reviste_marketplace_db;

-- Categorías iniciales (mismas del modelo Reviste)
INSERT IGNORE INTO categoria (nombre, descripcion, activo) VALUES
    ('Camisetas y tops',    'Camisetas, tops y esqueletos',        TRUE),
    ('Camisas y blusas',    'Camisas y blusas',                    TRUE),
    ('Pantalones',          'Pantalones de tela',                  TRUE),
    ('Jeans',               'Jeans y prendas de denim',            TRUE),
    ('Faldas',              'Faldas de todos los largos',          TRUE),
    ('Vestidos',            'Vestidos casuales y de fiesta',       TRUE),
    ('Chaquetas y abrigos', 'Chaquetas, abrigos y gabardinas',     TRUE),
    ('Buzos y sacos',       'Buzos, sacos y sudaderas',            TRUE),
    ('Ropa deportiva',      'Prendas para hacer deporte',          TRUE),
    ('Calzado',             'Zapatos, tenis y sandalias',          TRUE),
    ('Accesorios',          'Bolsos, gorras, cinturones y otros',  TRUE);

-- Métodos de pago internos (no hay pasarela externa)
INSERT INTO metodo_pago (nombre, descripcion, activo)
SELECT 'Efectivo contra entrega', 'Pago en efectivo al recibir la prenda', TRUE FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM metodo_pago WHERE nombre = 'Efectivo contra entrega');

INSERT INTO metodo_pago (nombre, descripcion, activo)
SELECT 'Transferencia bancaria', 'Transferencia directa a la cuenta del vendedor', TRUE FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM metodo_pago WHERE nombre = 'Transferencia bancaria');
