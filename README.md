# Proyecto Integrador: Sistema de Venta de Productos Ecológicos en Línea

Este proyecto es parte de la materia **Programación Orientada a Objetos II** y ha sido desarrollado como una solución completa para la venta y gestión de productos ecológicos en línea. El sistema permite la venta de productos tanto individualmente como en paquetes, e incluye características avanzadas como la gestión de inventario, cálculo de precios con descuentos, e integración con múltiples métodos de pago.

## Identificación de Tarea: Iteración

El proyecto está siendo desarrollado en iteraciones, lo que permite agregar y mejorar funcionalidad de manera incremental y optimizar el diseño general del sistema.

---

## Grupo de Trabajo: C

### Miembros:

- **Erreguerena Iñaki**
- **Fraga Facundo**
- **Piloni Fabrizio**
- **Pulikoski Mauricio**

---

## Descripción del Proyecto

El sistema de software para la venta de productos ecológicos en línea tiene las siguientes funcionalidades principales:

- **Gestión de Productos**: Los productos pueden ser gestionados en el sistema, ya sea de forma individual o como parte de un paquete.
- **Cálculo de Precios con Descuentos**: Se pueden aplicar descuentos a los productos y paquetes, y el sistema calculará automáticamente el precio final.
- **Múltiples Métodos de Pago**: El sistema permite a los clientes elegir entre varias opciones de pago seguras e integradas.
- **Interfaz de Usuario**: Una UI fácil de usar para que los clientes puedan navegar por el catálogo de productos y realizar sus compras.

---

## Tecnologías Utilizadas

- **Lenguaje de Programación**: Java
- **Framework**: Spring
- **ORM**: JPA (EclipseLink)
- **Base de Datos**: PostgreSQL
- **Herramientas de Desarrollo**:
  - IDE: VSCode
  - Diagramas: draw.io
  - Control de Versiones: Git

---

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas bien definida para facilitar el desarrollo, mantenimiento y pruebas. Las capas incluyen:

1. **Modelo**: Clases que representan la estructura de los datos y las relaciones entre ellos.
2. **Controladores**: Lógica para manejar las interacciones del usuario y coordinar la comunicación entre la UI y los servicios.
3. **Servicios**: Se encargan de procesar datos, interactuar con repositorios, y realizar tareas relacionadas con las operaciones centrales del sistema.
4. **Repositorio**: Acceso y gestión de datos en la base de datos, utilizando JPA.

---

## Funcionalidades Principales

1. **Gestión de Productos**
   - Agregar, actualizar y eliminar productos.
2. **Cálculo de Descuentos**
   - Aplicar descuentos a productos individuales o paquetes.
   - Calcular automáticamente el precio final.
3. **Métodos de Pago**
   - Integración con diferentes métodos de pago como tarjetas de crédito, transferencias bancarias y billeteras electrónicas.
4. **Interfaz de Usuario**
   - Fácil navegación por el catálogo de productos.
   - Funcionalidad de carrito de compras y proceso de pago.

---

## Contribuciones

### Tareas por Miembro:

- **Erreguerena Iñaki**
  - Testing y creación de controladores para la UI.
  - Asesorar y controlar el diseño del modelo de datos.
- **Fraga Facundo**
  - Testing y creación de servicios de lógica de negocio.
  - Pruebas de integración y registro de problemas.
- **Piloni Fabrizio**
  - Diseño del modelo de datos y gestión de la base de datos.
  - Configuración de PostgreSQL y creación de entidades JPA.
- **Pulikoski Mauricio**
  - Testing y diseño de templates de UI.
  - Asesorar la creación de controladores y servicios.

  ## Mecado Pago
    **Vendedor** 
    - Usuario: TESTUSER357148731
    - Contraseña: RVMNfW3isI
    
    **Comprador** 
    - Usuario: TESTUSER763275500
    - Contraseña: W7LGeIsuiS

    **Tarjetas de pruebas**
    - Tarjeta:	 Mastercard
    - Número:	5031 7557 3453 0604
    - Código de seguridad:	123
    - Fecha de caducidad: 11/25
    - Nombre: APRO
    - DNI: 12345678

    **Access token:** APP_USR-2065505450968304-112416-8243d6043a8a5c0456925ea2dcf13478-2117047882


    # **Tutorial de Ejecución del Proyecto EcoMarketDev**

  Este tutorial explica cómo ejecutar el proyecto **EcoMarketDev** de manera correcta, incluyendo los pasos para configurar el entorno, la base de datos y el servidor.

  ---

  ## **Requisitos Previos**

  1. **Java Development Kit (JDK):**  
   Asegúrese de tener instalado JDK 21.  
   Verifique la instalación ejecutando:  
   ```bash
   java -version
   ```
  2. **Apache Maven:**
  Instale Maven y verifique su instalación ejecutando:
  ```bash
   mvn -version
   ```
  3. **PostgreSQL:**
  Asegúrese de tener PostgreSQL instalado y configurado.
  4. **Instalar ngrok:**
  Visita la página oficial de ngrok (Incluye tutorial de instalación):  
   [https://ngrok.com/download](https://ngrok.com/download)

   ## Pasos de ejecución
   1. **Iniciar un endpoint**
   ```bash
   ngrok http 4567
   ```
   2. **Crear la base de datos PostgreSQL:** 
    - Con el nombre EcoMarket
    - Puerto 5432 (Si no se tiene este puerto para PostgreSQL modificar el puerto de **"application.properties"**)
  3. **Cambiar Usuario y Contraseña:**
    Cambiar las credenciales de la base de datos en **"application.properties"** segun corresponda:
    - spring.datasource.username=**"Su usuario"**
    - spring.datasource.password=**"Su contraseña"**  
  4. **Cambie la url de app.base.url:**
    Cambie la url de app.base.url por la url que le dio ngrok al ingresar esto en el bash:
    ```bash
   ngrok http 4567
   ```
   5. **Ejecute el proyecto**
   6. **Abra su navegador y ingrese la url de ngrok**: Si no se sigue este paso y pone localhost no se crearan los pedidos correctamente.