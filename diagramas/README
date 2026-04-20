# README para la asignatura de BBDD.

El diseño de la BBDD esta planteado intentando llevar al papel mi "negocio" real, he intentado priorizar la trazabilidad ya que es la base de todo , de donde viene el producto, quien lo adquirio, etc.
Recordamos que se trata de un negocio de compra-venta de videojuegos retro, donde cada videojuego en si es único al tratarse de artículo de colección.
Un apunte importante es que el cliente puede ser tanto vendedor , como comprador o ambos.


**Diagrama E-R (aclaraciones importantes):**
* Las relaciones 1-1 entre videojuego y compra/venta son 1-1 por lo comentado anteriormente de que no hay 2 juegos iguales. Por eso el id_juego es UNIQUE. Solo va a entrar y salir una vez.
* El campo tipo_stock me va a servir para identificar si el producto ya estaba dentro de mi stock o es una incorporación conjuntamente con mi "socia". Asi el programa identificará el porcentaje de beneficio correcto para cada una ya que se distribuye en 70/30 si me pertenecía con anterioridad o 50/50 si las compras han sido de las dos.
* Plataformas y géneros los he separado en tablas independientes y no como atributos de Videojuegos para evitar que si se escribe mal por ejemplo escribir SNES en vez de SUPER NINTENDO..
* Considero que con las relaciones estoy respetando la trazabilidad de todo , para no perder el rastro. 

**En las tablas del modelo relacional, he tomado un par de decisiones que considero me van a evitar problemas de funcionamiento:**
* En la tabla Usuarios he marcado como UNIQUE el DNI ya que es "más profesional" sin embargo basandome en una experiencia real y dado que a todos los clientes los identifico por el teléfono móvil ya que los contacto comienzan siempre desde aplicaciones de compra venta el teléfono pasa a ser UNIQUE y el dni que no es relevante deja de ser incluso NOT NULL.
* La gran mayoría de los atributos son NOT NULL para evitar vacios que me van a dar problemas con el resto de las operaciones , por ejemplo dejar vacio el precio de compra.
* El precio de compra, venta etc, son decimal para cuentas más precisas.
* id_usuario pasa como FK a videojuegos como id_usuario_registro para que pueda reconocerse mejor.

**20.04.26**
Se han detectado errores al crear la BBDD en SQL que me han hecho modificar algunos campos que había planteado mal, y con fecha de hoy se ha hecho una modificación tanto en el modelo relacional como en la BBDD de datos.

* En estado ( tabla videojuegos) había definido el campo como VARCHAR pero dado que siempre voy a usar los mismos he decidido que es mucho mejor usar ENUM con cuatro estados fijos (PRECINTADO, COMPLETO, CAJA SIN MANUAL, SOLO JUEGO).Evito problemas de escritura. Lo mismo ha ocurrido con tipo_stock, tipo_cliente y perfil.
* He tenido que renombrar password a pass ya que me figuraba como palabra reservada.

