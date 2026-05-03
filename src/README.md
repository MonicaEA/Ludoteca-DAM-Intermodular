# ReTrOJuEgOs - Sistema de Gestión de Videojuegos Retro

**ReTrOJuEgOs** es una herramienta integral desarrollada en Java y JavaFX diseñada para optimizar la gestión comercial de tiendas de videojuegos clásicos[cite: 1]. La aplicación permite controlar el flujo completo desde la compra a proveedores o particulares hasta la venta final, incorporando un análisis financiero detallado de beneficios[cite: 1].

## 🚀 Cómo se ejecuta el proyecto

Para poner en marcha la aplicación, sigue estos pasos:

1.  **Requisitos previos**: Asegúrate de tener instalados **JDK 24**, **Maven** y **MySQL**[cite: 1].
2.  **Base de Datos**: Importa el script SQL adjunto para crear la base de datos `retrojuegos` incluida en la carpeta sql llamada creacionTablasSQL.
3.  **Lanzamiento**: Ejecuta desde IntelliJ
   

## 🛠️ Funcionalidades (Módulo de Programación)

La aplicación implementa las siguientes características técnicas:

*   **Gestión de Inventario**: Registro completo de títulos con metadatos sobre plataformas (NES, Game Boy, PSX, etc.) y estados de conservación[cite: 1].
*   **Módulo de Compras**: Formulario dinámico que automatiza la entrada de stock vinculando datos de clientes y videojuegos en una sola transacción[cite: 1].
*   **Terminal de Ventas**: Interfaz interactiva con carrito de compra, búsqueda predictiva y actualización de totales en tiempo real[cite: 1].
*   **Análisis Económico**: Panel de control con balance de gastos, ingresos totales y reparto de beneficios neto entre socios[cite: 1].
*   **Tratamiento de Ficheros**: Generación de reportes de inventario en formato **XML** mediante la API DOM de Java[cite: 1].

## 🗄️ Persistencia y Base de Datos

El sistema utiliza **MySQL** para garantizar que la información sea persistente y segura[cite: 1]:
*   **Capa DAO**: Centraliza todas las consultas SQL (`INSERT`, `SELECT`, `SUM`) aislándolas de la interfaz[cite: 1].
*   **Integridad**: Uso de `PreparedStatement` en todos los procesos para prevenir inyecciones SQL[cite: 1].
*   **Relacional**: Vinculación de tablas mediante claves foráneas para mantener la trazabilidad de qué usuario registra cada operación[cite: 1].

---

## 🏛️ Arquitectura y Metodología (Módulo de MPO)

### Estructura del Proyecto
Se ha seguido una **Arquitectura Multicapa** para maximizar la mantenibilidad y el desacoplamiento[cite: 1]:
*   **Model**: Clases de datos (`POJOs`) que representan las entidades del negocio[cite: 1].
*   **DAO (Data Access Object)**: Capa de persistencia exclusiva para comunicación con la base de datos[cite: 1].
*   **Service**: Capa de lógica que orquesta las reglas de negocio[cite: 1].
*   **ControllerView**: Controladores de JavaFX encargados de la gestión de eventos y la UI[cite: 1].

### ✨ Mejoras mediante Refactorización (Trabajo Extra MPO)
El proyecto ha evolucionado mediante un proceso de refactorización para aplicar principios de diseño limpio[cite: 1]:

1.  **Desacoplamiento (Service Layer)**: Se ha extraído la lógica pesada de los controladores hacia clases `Service` dedicadas[cite: 1].
    *   *Resultado*: Los controladores ahora solo gestionan la interfaz, cumpliendo el **Principio de Responsabilidad Única**[cite: 1].
2.  **Gestión de Sesión (UsuarioActualService)**: Se ha centralizado la información del usuario logueado en un servicio estático[cite: 1].
    *   *Resultado*: Eliminación del acoplamiento innecesario al no tener que pasar objetos de usuario manualmente entre ventanas[cite: 1].
3.  **Optimización del Modelo con Lombok**: Refactorización de las entidades para eliminar código repetitivo[cite: 1].
    *   *Resultado*: Uso de `@Data` y `@AllArgsConstructor` para mejorar la legibilidad y reducir el tamaño de las clases de modelo[cite: 1].
4.  **Tipado fuerte con Enums**: Sustitución de cadenas de texto por tipos enumerados para estados de juegos y perfiles[cite: 1].
    *   *Resultado*: Garantía de integridad de datos y eliminación de errores por valores nulos o incorrectos[cite: 1].

---

