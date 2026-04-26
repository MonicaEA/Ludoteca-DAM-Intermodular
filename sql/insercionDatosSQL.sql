/*Creo los usuarios*/
INSERT INTO usuarios (dni, nombre, apellidos, email, pass, perfil) VALUES 
('39458713M', 'Mónica', 'Espiñeira', 'monica.espineira1988@gmail.com', '123456', 'administrador'),
('12345678M', 'Beatriz', 'Ferreira', 'bettyfer@gmail.com', '987654', 'administrador');

/*Creo los clientes*/
INSERT INTO clientes (dni, nombre, apellidos, email, telefono, tipo_cliente) VALUES
('00000000T', 'Venta', 'Mostrador', 'mostrador@retro.com', '000000000', 'ambos'),
('11122233A', 'Jose', 'Ruiz', 'jose@gmail.com', '600000000', 'vendedor'),
('44455566B', 'María', 'Gónzalez', 'maria@gmail.com', '611444555', 'comprador'),
('12345678A', 'Marcos', 'Gude', 'marcos@gmail.com', '611111111', 'ambos');

/*Creo las plataformas*/
INSERT INTO plataformas (nombre_plataforma) VALUES 
('Nintendo NES'), 
('Game Boy'), 
('Sega Mega Drive');

/*Creo los géneros*/
INSERT INTO generos (nombre_genero) VALUES 
('Plataformas'), 
('RPG'), 
('Aventuras');

/*Creo los videojuegos*/
INSERT INTO videojuegos (titulo, precio_compra, precio_venta_estimado, tipo_stock, estado, id_plataforma, id_genero, id_usuario_registro) VALUES 
('The Legend of Zelda', 35.00, 120.00, 'comun', 'COMPLETO', 1, 2, 1),
('Super Mario', 5.00, 15.00, 'propio', 'SOLO JUEGO', 2, 1, 1),
('Sonic', 80.00, 200.00, 'comun', 'PRECINTADO', 3, 3, 2);

/*Creo las compras*/
INSERT INTO compras (fecha_compra, id_juego, id_usuario, id_cliente) VALUES 
('2026-04-15', 1, 1, 1), 
('2026-04-18', 2, 1, 3), 
('2026-04-20', 3, 2, 3);

/*Creo las ventas*/
INSERT INTO ventas (fecha_venta, precio_final, id_juego, id_usuario, id_cliente) VALUES 
('2026-04-19', 115.00, 1, 1, 2);

INSERT INTO ventas (fecha_venta, precio_final, id_juego, id_usuario, id_cliente) VALUES 
('2026-04-21', 45.00, 2, 2, 1);
