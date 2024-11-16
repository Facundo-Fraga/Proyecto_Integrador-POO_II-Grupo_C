# Especificación de requisitos de software

## Enunciado del problema

Una tienda de productos ecológicos enfrenta limitaciones en sus ventas debido al uso de métodos de gestión tradicionales y a una presencia digital restringida. Con el creciente auge de las compras en línea, es fundamental que la tienda amplíe su mercado más allá de los clientes locales y llegue a una audiencia más amplia. Sin embargo, gestionar el inventario, aplicar descuentos y ofrecer diversas opciones de pago de manera manual o con sistemas desactualizados es ineficaz y difícil de escalar. Este software permitirá a la tienda automatizar procesos clave, mejorar la experiencia de compra y captar nuevos clientes en línea, impulsando así el crecimiento de sus ventas de productos ecológicos.

## Clientes potenciales

Como clientes potenciales, tenemos a los propietarios y administradores de la tienda, quienes enfrentan grandes desafíos para gestionar las ventas y operaciones en línea de manera eficiente. El software simplificará la administración del inventario, facilitará la aplicación de descuentos y ofrecerá una variedad de métodos de pago. Esto optimizará sus procesos operativos y les permitirá ampliar su alcance y atraer a más clientes.

Los clientes, por su parte, tanto actuales como nuevos, se beneficiarán de una experiencia de compra más conveniente, con múltiples opciones de pago y acceso a atractivas ofertas y descuentos.

## Solución propuesta

La solución de software automatizará la gestión de inventario, permitiendo un control más preciso y eficiente de los productos. Simplificará la aplicación de descuentos, facilitando la creación de promociones para atraer a los clientes. Además, la integración de múltiples métodos de pago ofrecerá una experiencia de compra fluida, mejorando la satisfacción del cliente y aumentando las posibilidades de venta. Estas mejoras permitirán a la tienda expandir su alcance y gestionar sus operaciones en línea de manera más efectiva.

## Requisitos

## 1. Gestión de Inventario

### Historia de Usuario: Añadir nuevos productos al inventario

**Como administrador, quiero añadir nuevos productos al inventario para que puedan estar disponibles para la venta en línea.**

- **Criterios de Aceptación:**
  - El sistema no debe permitir añadir un producto sin completar los campos obligatorios (nombre, precio, categoría y cantidad en inventario).
  - El producto debe guardarse correctamente en la base de datos de inventario.
  - Al añadir un producto, el sistema muestra un mensaje de confirmación.
  - El producto añadido debe ser visible en la lista de inventario.

### Historia de Usuario: Eliminar productos descontinuados

**Como administrador, quiero eliminar productos descontinuados del inventario para que los clientes solo vean los productos disponibles.**

- **Criterios de Aceptación:**
  - El sistema debe requerir confirmación antes de eliminar un producto.
  - El producto eliminado ya no debe ser visible en la lista de inventario ni en la tienda en línea.
  - Un mensaje de confirmación debe aparecer tras la eliminación exitosa.

### Historia de Usuario: Ver disponibilidad de productos

**Como cliente, quiero ver la cantidad de productos disponibles para que pueda decidir si hago la compra o no.**

- **Criterios de Aceptación:**
  - Si el producto está agotado, el sistema debe mostrar un mensaje de “Producto no disponible”.

### Historia de Usuario: Buscar productos por categoría

**Como cliente, quiero buscar productos específicos por categorías para que pueda encontrar fácilmente lo que necesito.**

- **Criterios de Aceptación:**
  - El cliente debe poder seleccionar una categoría de productos y ver solo los productos de esa categoría.
  - La búsqueda debe ser rápida y precisa.
  - Si no hay productos en la categoría seleccionada, el sistema muestra un mensaje de “No hay productos disponibles en esta categoría”.

## 2. Aplicación de Descuentos y Promociones

### Historia de Usuario: Crear promociones y descuentos

**Como administrador, quiero crear promociones y descuentos en productos para que pueda atraer más clientes con ofertas especiales.**

- **Criterios de Aceptación:**
  - El administrador debe poder definir un porcentaje o monto fijo de descuento y asociarlo a productos específicos.
  - La promoción debe aplicarse automáticamente al producto.
  - El descuento debe reflejarse en el precio final visible en la tienda en línea.

### Historia de Usuario: Programar duración de promociones

**Como administrador, quiero programar la duración de las promociones para que estas se activen y desactiven automáticamente en las fechas especificadas.**

- **Criterios de Aceptación:**
  - El administrador debe poder seleccionar las fechas de inicio y fin de la promoción.
  - El sistema debe activar la promoción automáticamente en la fecha de inicio y desactivarla en la fecha de finalización.
  - El producto debe mostrar su precio regular una vez terminada la promoción.

### Historia de Usuario: Ver productos en oferta

**Como cliente, quiero ver los productos en oferta y los descuentos aplicables para que pueda aprovechar las promociones actuales.**

- **Criterios de Aceptación:**
  - El cliente debe ver el precio original y el precio con descuento.
  - Si no hay promociones disponibles, el sistema debe mostrar solo los precios regulares.

## 3. Métodos de Pago

### Historia de Usuario: Seleccionar métodos de pago

**Como cliente, quiero poder seleccionar entre diferentes métodos de pago para que pueda pagar de manera conveniente y segura.**

- **Criterios de Aceptación:**
  - El cliente debe poder seleccionar entre múltiples métodos de pago en el proceso de compra.
  - El sistema debe confirmar el método de pago seleccionado antes de procesar la compra.

### Historia de Usuario: Ver detalles del pedido antes de confirmar

**Como cliente, quiero ver los detalles del pedido y el total a pagar antes de confirmar la compra para que pueda verificar que los cargos sean correctos.**

- **Criterios de Aceptación:**
  - El cliente debe ver un resumen del pedido y los precios antes de confirmar la compra.
  - El total a pagar debe incluir todos los descuentos aplicables y cualquier cargo adicional.
  - El sistema debe requerir confirmación antes de procesar el pago.

### Historia de Usuario: Confirmación de pago exitoso

**Como cliente, quiero recibir una confirmación de pago exitoso al finalizar la compra para que pueda tener la seguridad de que mi pedido se procesó correctamente.**

- **Criterios de Aceptación:**
  - El sistema debe enviar una notificación de confirmación de pago por correo electrónico.
  - La confirmación debe incluir el número de pedido y un resumen de la compra.
  - Si el pago falla, el sistema debe notificar al cliente y permitir reintentar el pago.

## 4. Experiencia de Usuario y Gestión de Cuenta

### Historia de Usuario: Crear cuenta en la tienda

**Como cliente, quiero crear una cuenta en la tienda para que pueda gestionar mis pedidos.**

- **Criterios de Aceptación:**
  - El cliente debe completar un formulario con campos obligatorios para registrar su cuenta.
  - El sistema debe verificar la unicidad del correo electrónico.
  - Se debe enviar un correo electrónico de confirmación de registro.

### Historia de Usuario: Iniciar sesión para ver historial de compras

**Como cliente, quiero iniciar sesión en mi cuenta para que pueda ver mi historial de compras y realizar nuevas compras sin volver a ingresar mis datos.**

- **Criterios de Aceptación:**
  - El cliente debe ingresar correo electrónico y contraseña para iniciar sesión.
  - El sistema debe notificar errores de autenticación si el login falla.

## Arquitectura de software

1. **Tipo de Aplicación:**
   - La aplicación será una **aplicación web**.

2. **Arquitectura de Software:**
   - Se ajustará a una **arquitectura Cliente-Servidor**, donde el cliente accederá a los servicios a través de un navegador web mientras el servidor manejará la lógica de negocio y el acceso a la base de datos.

3. **Tecnologías a Utilizar:**
   - **Lenguaje de Programación:** Java.
   - **Framework de Desarrollo:** Spring.
   - **Base de Datos:** PostgreSQL.
