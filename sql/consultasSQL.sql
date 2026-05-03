/*Listar todos los clientes*/
SELECT id_cliente, nombre, apellidos, tipo_cliente FROM clientes;

/*Listar todas las ventas con el precio final*/
SELECT id_ventas, fecha_venta, precio_final FROM ventas;

/*Listar todos los juegos precintados*/
SELECT titulo, precio_venta_estimado FROM videojuegos WHERE estado = 'PRECINTADO';

/*Listar con rango de fechas*/
SELECT * FROM compras WHERE fecha_compra BETWEEN '2026-04-01' AND '2026-04-30';

/*Videojuegos vendidos a cada cliente y el precio de venta*/
SELECT 
    c.nombre AS Nombre, 
    c.apellidos AS Apellidos, 
    v.titulo AS Juego, 
    ve.precio_final AS PrecioVenta,
    ve.fecha_venta AS Fecha
FROM ventas ve
INNER JOIN clientes c ON ve.id_cliente = c.id_cliente
INNER JOIN videojuegos v ON ve.id_juego = v.id_juego
ORDER BY ve.fecha_venta DESC;

/*Busqueda de que empleado compro cada juego su precio de compra y el estimado de venta*/
SELECT 
    u.nombre AS empleado, 
    v.titulo AS juego, 
    p.nombre_plataforma AS plataforma,
    v.precio_compra AS precioCoste, 
    v.precio_venta_estimado AS precioEstimado
FROM videojuegos v
INNER JOIN usuarios u ON v.id_usuario_registro = u.id_usuario
INNER JOIN plataformas p ON v.id_plataforma = p.id_plataforma;

/*Listar clientes y el total gastado*/
SELECT 
    c.nombre, 
    c.apellidos, 
    SUM(v.precio_final) AS totalGastado
FROM clientes c
LEFT JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.id_cliente, c.nombre, c.apellidos;

/*Listar ventas y la empleada que lo hizo*/
SELECT 
    u.nombre AS empleada, 
    v.id_ventas
FROM usuarios u
RIGHT JOIN ventas v ON u.id_usuario = v.id_usuario;


/*Listar videojuegos y saber si se han vendido o no , null salen los que no se han vendido porque no tienen id_venta*/
SELECT v.titulo, ve.id_ventas
FROM videojuegos v
LEFT JOIN ventas ve ON v.id_juego = ve.id_juego;


/*Listar clientes que han gastado >100*/
SELECT c.nombre, v.precio_final
FROM clientes c
LEFT JOIN ventas v ON c.id_cliente = v.id_cliente
WHERE v.precio_final > 100;

/*Clientes que nos han vendido al menos una vez*/
SELECT nombre, apellidos 
FROM clientes c
WHERE EXISTS (SELECT 1 FROM compras co WHERE co.id_cliente = c.id_cliente);

/* Para detectar si algun juego esta en compras pero no en videojuegos*/
SELECT id_juego FROM compras 
WHERE id_juego NOT IN (SELECT id_juego FROM videojuegos);


/* --- CONSULTAS IMPLEMENTADAS PARA LA LÓGICA DE LA APP --- */

/* [VideojuegoDAO] Listar videojuegos disponibles para la venta */
/* Selecciona solo los juegos que no han sido registrados en la tabla de ventas */
SELECT vj.* 
FROM videojuegos vj 
LEFT JOIN ventas v ON vj.id_juego = v.id_juego 
WHERE v.id_juego IS NULL;

/* [ComprasDAO] Calcular la inversión total en stock */
/* Suma el precio de compra de todos los registros de la tabla compras */
SELECT SUM(v.precio_compra) AS total 
FROM compras c 
JOIN videojuegos v ON c.id_juego = v.id_juego;

/* [VentasDAO] Calcular beneficio neto por tipo de procedencia */
/* Fundamental para el reparto de beneficios (70/30 o 50/50). 
   Se filtra por 'comun' o 'propio' según el caso */
SELECT SUM(ve.precio_final - v.precio_compra) AS beneficio 
FROM ventas ve 
JOIN videojuegos v ON ve.id_juego = v.id_juego 
WHERE v.tipo_stock = ?;

/* [ExportadorXML] Obtención de datos cruzados para informe XML */
/* Recupera la información completa del catálogo incluyendo nombres de plataforma y género */
SELECT v.*, c.fecha_compra AS fecha_registro, p.nombre_plataforma, g.nombre_genero 
FROM videojuegos v 
JOIN plataformas p ON v.id_plataforma = p.id_plataforma 
JOIN generos g ON v.id_genero = g.id_genero 
JOIN compras c ON v.id_juego = c.id_juego;

/* [VentasDAO] Suma total de ingresos por ventas */
SELECT SUM(precio_final) AS total FROM ventas;
