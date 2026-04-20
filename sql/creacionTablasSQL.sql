/* CREO LA BBDD*/

CREATE DATABASE retrojuegos;

/*creo la tabla plataformas relación 1-N con Videojuegos*/
CREATE TABLE plataformas(
  id_plataforma INT AUTO_INCREMENT PRIMARY KEY,
  nombre_plataforma VARCHAR(50) NOT NULL UNIQUE 
  );

/* ahora creo generos que al igual que plataformas tiene relación 1-N con videojuegos*/ 
CREATE TABLE generos(
  id_genero INT AUTO_INCREMENT PRIMARY KEY,
  nombre_genero VARCHAR(50) NOT NULL UNIQUE 
  );

/* CREO TABLA USUARIOS , , también aqui nombro password como pass ya que parece que es palabra reservada y no se si me dará problemas
esta tabla tiene .
Relación 1-N con Videojuegos y 1-N con Ventas/Compras*/

CREATE TABLE usuarios(
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
dni VARCHAR(10) not null UNIQUE,
nombre VARCHAR(50) not null,
apellidos VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
pass VARCHAR(100) not null,
perfil ENUM('administrador','socio') NOT NULL    
);

/*creo tabla clientes
Relación 1-N con Compras/Ventas */

CREATE TABLE clientes(
id_cliente INT AUTO_INCREMENT PRIMARY KEY,
dni VARCHAR(10),
nombre VARCHAR(50) NOT NULL,
apellidos VARCHAR(100),
email VARCHAR(100),  
telefono VARCHAR(15) UNIQUE NOT NULL,
tipo_cliente ENUM('comprador','vendedor','ambos') NOT NULL    

);

/* CREO TABLA VIDEJUEGOS CON CHECK EN LOS PRECIOS PARA NO PERMITIR NEGATIVOS
Relación N-1 con Plataformas ,N-1 con Géneros, N-1 con Usuarios, 1-1 con Compras/Ventas*/

CREATE TABLE videojuegos(
id_juego INT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(150) NOT NULL,
precio_compra DECIMAL(8,2) NOT NULL CHECK (precio_compra >=0),
precio_venta_estimado DECIMAL(8,2) NOT NULL CHECK (precio_venta_estimado >=0),
tipo_stock ENUM('propio','comun') NOT NULL,
estado ENUM('PRECINTADO','COMPLETO','CAJA SIN MANUAL','SOLO JUEGO') NOT NULL,
id_plataforma INT NOT NULL,
id_genero INT NOT NULL,
id_usuario_registro INT NOT NULL,

    
CONSTRAINT fk_juego_plataforma
FOREIGN KEY (id_plataforma) REFERENCES plataformas(id_plataforma)
ON UPDATE CASCADE ON DELETE RESTRICT,

CONSTRAINT fk_juego_genero
FOREIGN KEY (id_genero) REFERENCES generos(id_genero)
ON UPDATE CASCADE ON DELETE RESTRICT,
    
CONSTRAINT fk_juego_usuario
FOREIGN KEY (id_usuario_registro) REFERENCES usuarios(id_usuario)
ON UPDATE CASCADE ON DELETE RESTRICT

);

/* tabla compras, explicación de porque el id_juego es unique en el README
Relación N-1 con clientes, N-1 con usuarios, 1-1 con Videojuegos*/
CREATE TABLE compras(
id_compra INT AUTO_INCREMENT PRIMARY KEY,
fecha_compra DATE NOT NULL,
id_juego INT UNIQUE,
id_usuario INT,
id_cliente INT,

CONSTRAINT fk_compras_juego
FOREIGN KEY (id_juego) REFERENCES videojuegos(id_juego)
ON UPDATE CASCADE ON DELETE RESTRICT,

CONSTRAINT fk_compras_usuario
FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
ON UPDATE CASCADE ON DELETE RESTRICT,    

CONSTRAINT fk_compras_cliente
FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
ON UPDATE CASCADE ON DELETE RESTRICT
);

/* tabla de ventas
Relación 1-1 con Videojuegos, N-1 con clientes, N-1 con usuarios*/

CREATE TABLE ventas(
id_ventas INT AUTO_INCREMENT PRIMARY KEY,
fecha_venta DATE NOT NULL,
precio_final DECIMAL(8,2) NOT NULL CHECK (precio_final>=0),
id_juego INT NOT NULL UNIQUE,
id_usuario INT NOT NULL,
id_cliente INT NOT NULL,

CONSTRAINT fk_ventas_juego 
FOREIGN KEY (id_juego) REFERENCES videojuegos(id_juego)
ON UPDATE CASCADE ON DELETE RESTRICT,

CONSTRAINT fk_ventas_usuario 
FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
ON UPDATE CASCADE ON DELETE RESTRICT,

CONSTRAINT fk_ventas_cliente 
FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
ON UPDATE CASCADE ON DELETE RESTRICT
);


