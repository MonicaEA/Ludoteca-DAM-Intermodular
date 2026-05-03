# 🕹️ RetroJuegos: Proyecto Intermodular

<p align="center">
  <img src="./docs/imagenes/LogoRetroJuegos.jpg" alt="Logo RetroJuegos" width="300">
</p>

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000f?style=for-the-badge&logo=mysql&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-blue?style=for-the-badge&logo=java&logoColor=white)

## 📝 Resumen del Proyecto
Este proyecto nace de una pasión personal: la recuperación de videojuegos retro. Lo que comenzó como coleccionismo ha evolucionado hacia un modelo de negocio de compra-venta que requiere una gestión técnica profesional. Como estudiante de **1º de DAM**, desarrollo esta solución integral en **Java y JavaFX** para integrar la lógica de negocio real con la eficiencia técnica necesaria.

### 💡 Problema que resuelve
El mercado de videojuegos retro carece de herramientas sencillas que gestionen simultáneamente el stock, los datos de clientes y, sobre todo, el **reparto complejo de beneficios**. **RetroJuegos** soluciona:
*   **Falta de trazabilidad**: Automatiza quién compra, quién vende y quién registra cada artículo.
*   **Cálculo manual de beneficios**: Elimina errores en el reparto de ganancias (70/30 o 50/50) según el origen del stock.
*   **Desorden de inventario**: Clasifica por plataforma, género y estado de conservación de forma estandarizada.

---

## 🛠️ Stack Tecnológico
*   **Lenguaje:** Java 24 (conectado mediante JDBC).
*   **Interfaz Gráfica:** JavaFX con FXML.
*   **Base de Datos:** MySQL / MariaDB.
*   **Gestión de Dependencias:** Maven.
*   **Formatos de Datos:** Exportación e informes en **XML/XSD** para asegurar portabilidad.
*   **Librerías Extra:** Lombok (optimización de código).

---

## 🚀 Instalación y Ejecución

Para poner en marcha **RetroJuegos** en tu entorno local, sigue estos pasos:

1.  **Clonar el repositorio**:
    ```bash
    git clone [https://github.com/tu-usuario/RetroJuegos.git](https://github.com/tu-usuario/RetroJuegos.git)
    ```
2.  **Configurar la Base de Datos**:
    *   Asegúrate de tener un servidor MySQL activo.
    *   Importa el script ubicado en `/sql/retrojuegos.sql` para crear las tablas y datos iniciales.
3.  **Compilar y Ejecutar**:
    *   Abre IntelliJ y ejecuta.
---

## 📂 Estructura del Repositorio y Vinculación Curricular

Para facilitar la navegación y entender la aplicación de los conocimientos de **1º de DAM**, el repositorio se organiza así:

*   [**💻 /src (Código Fuente)**](./src) : Implementación de la lógica, servicios y controladores.
    *   *Asignaturas:* **Programación** y **MPO**.
*   [**🗄️ /sql (Base de Datos)**](./sql) : Scripts de creación, procedimientos y consultas.
    *   *Asignatura:* **Bases de Datos**.
*   [**🎨 /diagramas (Diseño)**](./diagramas) : Esquemas E-R, modelos relacionales y diagramas de flujo.
    *   *Asignatura:* **Bases de Datos / Entornos**.
*   [**📄 /docs (Documentación)**](./docs) : Repositorio central de documentación técnica:
    *   [**📁 Empleabilidad**](./docs/empleabilidad) : Investigación del sector y presentación.
    *   [**📁 XML**](./docs/xml): Estructuras de datos (Asignatura: **Lenguaje de Marcas**).
    *   [**📁 Sistemas**](./docs/sistemas): Informe técnico, instalación y vídeo demostrativo (Asignatura: **Sistemas Informáticos**).

---

## 🏛️ Mejora MPO: Refactorización y Diseño Limpio
Este proyecto no solo "funciona", sino que ha sido **refactorizado** para cumplir con estándares profesionales:
*   **Arquitectura Multicapa**: Separación estricta entre Vista (FXML), Controlador, Servicio (Lógica) y DAO (Persistencia).
*   **Desacoplamiento**: Se extrajo la lógica de negocio de los controladores a clases `Service` independientes.
*   **Integridad**: Uso de `Enums` para estados y perfiles, y `Lombok` para limpiar el código de las entidades.

---

## 🎯 Motivación
Este es mi primer proyecto "de verdad". Me motiva transformar un hobby en un sistema controlado y profesionalizado que me permita aplicar cada día lo aprendido en el ciclo DAM.

---
*Desarrollado por Mónica Espiñeira Aragón - 1º DAM*

