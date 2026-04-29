Documentación para la asignatura de Lenguajes de Marcas.

EXPORTACION DE INVENTARIO XML
He implementado un sistema para sacar los datos de los juegos disponibles de la BBDD y guardarlos en un archivo estructurado.

1. ¿Que datos representa el XML?
El archivo generado (videojuegos_exportados.xml) refleja el estado actual del stock, organizando la información en dos bloques principales:
- Usuarios : Listado de los administradores , ya que por el momento no contemplo otro perfil por el uso del programa.
- Catálogo: Es la lista de todos los juegos que hay en stock. Para cada uno guardo:
  - Título
  - Plataforma
  - Género
  - Precio compra
  - Precio venta
  - Estado
  - Tipo stock
  - Fecha de registro (compra)

2. ¿Cómo la válido?
Para comprobar el xml se ha usado un archivo xsd llamado esquema.xsd
[Esquema XSD](src/main/resources/com/retrojuegos/retrojuegos/xml/esquema.xsd)
Se han incorporado las siguientes reglas:
  - Estructura obligatoria con orden lógico.
  - IDS y relaciones , cada videojuego debe tener un id único y tiene que estar relacionado con un usuario que a su vez tiene un id.
  - Restricciones :
    - Precio , deben ser decimales y llevan el atributo moneda="EUR"
    - Fechas , la fecha_registro debe tener el formato estandar
    - ENUMS , solo permite los valores especificos de los mismos.

3. ¿Cómo encaja dentro del proyecto?
Esta funcionalidad se integra en la vista "VER STOCK" del programa:
  Capa de Vista: Se ha añadido un botón de exportación que dispara el evento.
  Capa de Utilidad: Una clase ExportadorXML realiza una consulta compleja (JOIN) a la base de datos para unificar la información dispersa en varias tablas.
  Ruta de salida: El archivo se genera automáticamente en la ruta de recursos del proyecto: [Archivos XML](src/main/resources/com/retrojuegos/retrojuegos/xml/), quedando disponible para su uso inmediato.

Ha sido comprobado y testeado confirmando que funciona correctamente.
He añadido las capturas de pantalla para verificar que se ha válidado y forzado errores:
[VALIDACIÓN OK](docs/xml/Captura de Pantalla Validacion OK.jpg)
[VALIDACIÓN KO](docs/xml/Captura de Pantalla Validación KO.jpg)






