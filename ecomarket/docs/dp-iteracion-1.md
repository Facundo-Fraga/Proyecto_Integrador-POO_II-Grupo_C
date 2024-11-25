# Trabajo en Equipo

**Erreguerena, Agustín Iñaki:** Responsable del diseño de pantallas, implementación de plantillas, integración con la API de Mercado Pago y apoyo en el modelado del diagrama de clases.

**Fraga, Facundo Román:** Encargado del diseño de pantallas y soporte en la implementación de las entidades JPA.

**Piloni, Fabrizio Julián:** Responsable de la creación del diagrama de clases y de la implementación de las entidades JPA.

**Pulikoski, Mauricio Ezequiel:** Responsable de las funcionalidades de alta, baja y modificación de productos individuales, diseño e implementación de plantillas, y desarrollo del sistema de registro e inicio de sesión de usuarios.

# Diseño OO
![ ](/ecomarket/docs/EcoMarketDiagramaDeClases.png)
# Wireframe y caso de uso

# Backlog de Iteración 1
## Acceso Publico
### Registrar cuenta
![ ](/ecomarket/docs/wirefreame/Registrar%20cuenta.png)

### Inciar sesión
![ ](/ecomarket/docs/wirefreame/Iniciar%20sesion.png)
### Detalle del producto
![ ](/ecomarket/docs/wirefreame/Detalle%20del%20producto.png)
### Detalle del paquete
![ ](/ecomarket/docs/wirefreame/Detalle%20del%20paquete.png)

## Acceso restringido al Cliente
### Inicio
![ ](/ecomarket/docs/wirefreame/Inicio%20Cliente.png)
### Carrito de compras
![ ](/ecomarket/docs/wirefreame/Carrito%20de%20compras.png)
### Metodos de pago
![ ](/ecomarket/docs/wirefreame/Metodos%20de%20pago.png)
### Pago exitoso
![ ](/ecomarket/docs/wirefreame/Pago%20exitoso.png)

## Acceso restringido al Administrador
### Inicio
![ ](/ecomarket/docs/wirefreame/Inicio%20administrador.png)
### Gestion de productos
![ ](/ecomarket/docs/wirefreame/Gestion%20de%20productos.png)
### Gestion de paquetes
![ ](/ecomarket/docs/wirefreame/Gestion%20de%20paquetes.png)
### Agregar producto
![ ](/ecomarket/docs/wirefreame/Agregar%20producto.png)
### Editar producto
![ ](/ecomarket/docs/wirefreame/Editar%20producto.png)
### Agregar paquete
![ ](/ecomarket/docs/wirefreame/Agregar%20paquete.png)
### Editar paquete
![ ](/ecomarket/docs/wirefreame/Editar%20paquete.png)
# Casos de uso (esenciales) de las pantallas

## Caso de Uso: Crear un Producto Nuevo
**ID**: CU01  
**Nombre**: Crear Producto  
**Actor**: Administrador  

### Descripción
Permite al administrador crear un nuevo producto ingresando información detallada como nombre, descripción, categoría, unidad de medida, precio, descuento, y las fechas de inicio y finalización del descuento.

### Flujo Principal
1. El actor selecciona el boton  **"Agregar Producto"**.
2. El sistema muestra el formulario de creación de un nuevo producto.
3. El actor ingresa los siguientes datos en el formulario:
   - **Nombre**: Nombre del producto.
   - **Descripción**: Descripción del producto.
   - **Categoría**: Selección de la categoría a la que pertenece el producto.
   - **Unidad**: Selección de la unidad de medida del producto y se ingresa el valor asosciado a este.
   - **Precio**: Precio del producto.
   - **Descuento**: Porcentaje de descuento para el producto, seleccionable de 0% a 100%.
   - **Fecha de inicio de descuento**: Fecha en la que el descuento comienza a aplicarse.
   - **Fecha de finalización de descuento**: Fecha en la que el descuento deja de aplicarse.
4. El actor presiona el botón **"Guardar Producto"**.
5. El sistema valida los datos ingresados:
   - Verifica que todos los campos obligatorios estén completos.
   - Verifica que la fecha de finalización de descuento sea posterior a la de inicio.
6. El sistema guarda el nuevo producto en la base de datos y muestra un mensaje de confirmación.
7. El caso de uso finaliza.

### Flujo Alternativo 1: Datos Inválidos
- **5a.** Si alguno de los datos ingresados no es válido, el sistema muestra un mensaje de error indicando el campo específico que necesita corrección.
- **5b.** El actor corrige los datos y presiona nuevamente el botón **"Guardar Producto"**.
- Regresa al paso 6.

### Precondiciones
- El actor debe estar autenticado y tener permisos de administración.

### Postcondiciones
- El producto se guarda en el sistema y está disponible en la lista de productos para su gestión.
---
## Caso de Uso: Editar Producto
**ID**: CU02 
**Nombre**: Editar Producto  
**Actor**: Administrador  

### Descripción
Permite al administrador editar los datos de un producto existente, incluyendo nombre, descripción, categoría, unidad de medida, precio, descuento y las fechas de inicio y finalización del descuento.

### Flujo Principal
1. El actor accede a la lista de productos.
2. En el renglón del producto a editar, el actor presiona el botón **"Editar"**.
3. El sistema muestra un formulario precargado con los datos actuales del producto.
4. El actor realiza las modificaciones necesarias en los siguientes campos:
   - **Nombre**: Nombre del producto.
   - **Descripción**: Descripción del producto.
   - **Categoría**: Selección de la categoría a la que pertenece el producto.
   - **Unidad**: Selección de la unidad de medida del producto y se ingresa el valor asosciado a este.
   - **Precio**: Precio del producto.
   - **Descuento**: Porcentaje de descuento para el producto, seleccionable de 0% a 100%.
   - **Fecha de inicio de descuento**: Fecha en la que el descuento comienza a aplicarse.
   - **Fecha de finalización de descuento**: Fecha en la que el descuento deja de aplicarse.
5. El actor presiona el botón **"Guardar Cambios"**.
6. El sistema valida los datos ingresados:
   - Verifica que todos los campos obligatorios estén completos.
   - Verifica que la fecha de finalización de descuento sea posterior a la de inicio.
7. El sistema solicita confirmación para guardar los cambios.
8. El actor confirma los cambios.
9. El sistema guarda los cambios en la base de datos y muestra un mensaje de confirmación.
10. El caso de uso finaliza.

### Flujo Alternativo 1: Datos Inválidos
- **6a.** Si alguno de los datos ingresados no es válido, el sistema muestra un mensaje de error indicando el campo específico que necesita corrección.
- **6b.** El actor corrige los datos y presiona nuevamente el botón **"Guardar Cambios"**.
- Regresa al paso 7.

### Precondiciones
- El actor debe estar autenticado y tener permisos de administración de productos.

### Postcondiciones
- Los datos actualizados del producto se guardan en el sistema y están disponibles en la lista de productos para su gestión.

---
## Caso de Uso: Crear Paquete
**ID**: CU03  
**Nombre**: Crear Paquete  
**Actor**: Administrador  

### Descripción
Permite al administrador crear un nuevo paquete de productos, definiendo su nombre, descripción, productos incluidos, porcentaje de descuento, y fechas de inicio y fin del descuento.

### Flujo Principal
1. El actor presiona el botón **"Agregar Paquete"**.
2. El sistema muestra un formulario vacío para la creación del paquete.
3. El actor ingresa los siguientes datos en el formulario:
   - **Nombre**: Nombre del paquete.
   - **Descripción**: Descripción del paquete.
4. El actor selecciona productos para incluir en el paquete:
   - Presiona el botón **"Agregar al Paquete"** en la lista de productos.
   - Los productos seleccionados se añaden a una lista visible dentro del formulario del paquete.
5. Si el actor desea eliminar un producto añadido al paquete:
   - Presiona el botón **"Eliminar del Paquete"** junto al producto en la lista del paquete.
   - El producto se elimina del paquete.
6. El actor selecciona:
   - **Descuento**: Porcentaje de descuento aplicable (de 0% a 100%).
   - **Fecha de inicio de descuento**: Fecha en la que el descuento comienza a aplicarse.
   - **Fecha de finalización de descuento**: Fecha en la que el descuento deja de aplicarse.
7. El actor presiona el botón **"Guardar Paquete"**.
8. El sistema valida los datos ingresados:
   - Verifica que los campos obligatorios estén completos.
   - Verifica que la fecha de finalización sea posterior a la de inicio.
9. El sistema guarda el paquete en la base de datos y muestra un mensaje de confirmación.
10. El caso de uso finaliza.

### Flujo Alternativo 1: Datos Inválidos
- **8a.** Si algún dato no es válido, el sistema muestra un mensaje de error indicando el campo específico que necesita corrección.
- **8b.** El actor corrige los datos y presiona nuevamente el botón **"Guardar Paquete"**.
- Regresa al paso 9.

### Precondiciones
- El actor debe estar autenticado y tener permisos de administración de productos.

### Postcondiciones
- El paquete se guarda en el sistema y está disponible para su gestión y visualización.

---
## Caso de Uso: Editar Paquete
**ID**: CU04  
**Nombre**: Editar Paquete  
**Actor**: Administrador 

### Descripción
Permite al administrador editar los detalles de un paquete existente, incluyendo su nombre, descripción, productos incluidos, porcentaje de descuento, y fechas de inicio y fin del descuento.

### Flujo Principal
1. El actor accede a la lista de paquetes.
2. En el renglón del paquete a editar, el actor presiona el botón **"Editar"**.
3. El sistema muestra un formulario precargado con los datos actuales del paquete.
4. El actor realiza las modificaciones necesarias en los siguientes campos:
   - **Nombre**: Nombre del paquete.
   - **Descripción**: Descripción del paquete.
   - **Productos**:
     - Para añadir productos, el actor selecciona un producto de la lista y presiona el botón **"Agregar al Paquete"**.
     - Para eliminar un producto, el actor presiona el botón **"Eliminar del Paquete"** junto al producto en la lista del paquete.
   - **Descuento**: Porcentaje de descuento aplicable (de 0% a 100%).
   - **Fecha de inicio de descuento**.
   - **Fecha de finalización de descuento**.
5. El actor presiona el botón **"Guardar Cambios"**.
6. El sistema valida los datos ingresados:
   - Verifica que los campos obligatorios estén completos
   - Verifica que el descuento esté en un rango válido (0%-100%).
   - Verifica que la fecha de finalización sea posterior a la de inicio.
7. El sistema solicita confirmación para guardar los cambios.
8. El actor confirma los cambios.
9. El sistema guarda los cambios en la base de datos y muestra un mensaje de confirmación.
10. El caso de uso finaliza.

### Flujo Alternativo 1: Datos Inválidos
- **6a.** Si algún dato no es válido, el sistema muestra un mensaje de error indicando el campo específico que necesita corrección.
- **6b.** El actor corrige los datos y presiona nuevamente el botón **"Guardar Cambios"**.
- Regresa al paso 7.

### Precondiciones
- El actor debe estar autenticado y tener permisos de administración de productos.

### Postcondiciones
- Los datos actualizados del paquete se guardan en el sistema y están disponibles para su gestión y visualización.
---
## Caso de Uso: Gestionar Carrito de Compras
**ID**: CU05  
**Nombre**: Gestionar Carrito de Compras  
**Actor**: Cliente  

### Descripción
Permite al cliente agregar productos al carrito, ver el contenido del carrito, seleccionar el método de envío, y proceder al inicio del proceso de compra.

### Flujo Principal
1. El cliente navega por la tienda en línea y selecciona productos para agregar al carrito:
   - El cliente presiona el botón **"Agregar al Carrito"** en la vista de un producto.
   - El sistema añade el producto al carrito de compras y muestra un mensaje de confirmación (por ejemplo: "Producto añadido al carrito").
2. El cliente puede continuar navegando o presionar el botón **"Ir al Carrito de Compras"**.
3. Al seleccionar **"Ir al Carrito de Compras"**, el sistema redirige al cliente a la página del carrito.
4. En la página del carrito, el cliente puede:
   - Ver la lista de productos seleccionados, incluyendo nombre, cantidad, precio unitario y subtotal.
   - Ajustar la cantidad de productos (si aplica):
     - **Incrementar/Decrementar cantidad**: El cliente presiona los botones correspondientes y el sistema actualiza automáticamente el subtotal.
     - **Papelera**: El cliente presiona el botón **"Eliminar"** junto al producto, y el sistema lo elimina del carrito.
5. El cliente selecciona el método de envío:
   - El sistema presenta las opciones disponibles (**Envío local**, **Envío provincial**, **Envío nacional**).
   - El cliente selecciona una opción y el sistema actualiza el total a pagar.
6. El cliente presiona el botón **"Iniciar Compra"**.
7. El sistema redirige al cliente al flujo de eleccion del metodo de pago.
8. Termina caso de uso.

### Flujo Alternativo 1: Método de Envío No Seleccionado
- **5a.** Si el cliente intenta presionar **"Iniciar Compra"** sin seleccionar un método de envío:
  - El sistema muestra un mensaje de error indicando que es necesario seleccionar un método de envío antes de proceder.
  - El cliente selecciona un método de envío y regresa al paso 6.

### Precondiciones
- El cliente debe estar autenticado o continuar como invitado, dependiendo de las políticas de la tienda.

### Postcondiciones
- Si el cliente presiona **"Iniciar Compra"**, el sistema lo redirige al flujo de confirmación de compra con la información del carrito y el método de envío seleccionado.


## Caso de Uso: Efectuar Compra
**ID**: CU06  
**Nombre**: Efectuar Compra  
**Actor**: Cliente  

### Descripción
Permite al cliente completar la compra seleccionando un método de pago y confirmando el pedido. Los métodos de pago disponibles son **Mercado Pago**, **Tarjeta de Crédito/Débito** y **PayPal**.

### Flujo Principal
1. El cliente accede al flujo de confirmación de compra desde el carrito de compras.
2. El sistema muestra un resumen del pedido, incluyendo:
   - Lista de productos seleccionados.
   - Método de envío elegido y su costo.
   - Total a pagar.
3. El cliente selecciona un método de pago:
   - **Mercado Pago**: Se abre una interfaz para iniciar sesión y completar el pago.
   - **Tarjeta de Crédito/Débito**: El cliente ingresa los datos de la tarjeta (número, fecha de vencimiento, CVV, nombre del titular).
   - **PayPal**: El sistema redirige al cliente a la página de inicio de sesión de PayPal para autorizar el pago.
4. El cliente revisa los detalles de su pedido y presiona el botón **"Confirmar Compra"**.
5. El sistema procesa el pago:
   - Verifica la validez de los datos de pago.
   - Autoriza la transacción con el proveedor de pago seleccionado.
6. Si el pago es exitoso:
   - El sistema registra el pedido en la base de datos.
   - Muestra un mensaje de confirmación con un resumen del pedido y el número de transacción.
   - Envía una notificación por correo electrónico al cliente con el comprobante de la compra.
   - El caso de uso finaliza.
   
### Flujo Alternativo 1: Pago Rechazado
- **5a.** Si el pago es rechazado por el proveedor de pago:
  - El sistema muestra un mensaje de error indicando que la transacción no fue exitosa.
  - El cliente puede:
    - Revisar los datos ingresados y volver a intentar el pago.
    - Seleccionar un método de pago diferente.
  - Regresa al paso 3.

### Flujo Alternativo 2: Cancelación del Proceso
- **4a.** Si el cliente decide cancelar el proceso de compra:
  - El cliente presiona el botón **"Cancelar"**.
  - El sistema redirige al cliente al carrito de compras.
  - El caso de uso finaliza.

### Precondiciones
- El cliente debe haber accedido al flujo desde el carrito de compras con al menos un producto seleccionado.
- El cliente debe haber elegido un método de envío.

### Postcondiciones
- Si el pago es exitoso:
  - El pedido queda registrado en el sistema.
  - Se genera una confirmación de compra.
  - El cliente puede rastrear el estado de su pedido.




## Historias de Usuario a Implementar

### Gestión de Inventario
1. **Añadir nuevos productos al inventario**  
   Como administrador, quiero añadir nuevos productos al inventario para que puedan estar disponibles para la venta en línea.

2. **Eliminar productos descontinuados**  
   Como administrador, quiero eliminar productos descontinuados del inventario para que los clientes solo vean los productos disponibles.

### Aplicación de Descuentos
3. **Crear descuentos**  
   Como administrador, quiero crear descuentos en productos para atraer más clientes con ofertas especiales.

4. **Programar duración de descuentos**  
   Como administrador, quiero programar la duración de los descuentos para que estos se activen y desactiven automáticamente en las fechas especificadas.

### Experiencia de Usuario y Gestión de Cuenta
5. **Crear cuenta en la tienda**  
   Como cliente, quiero crear una cuenta en la tienda para poder gestionar mis pedidos.

---

# Tareas

## Gestión de Inventario
### Para **"Añadir nuevos productos al inventario"**:
- Diseñar y crear la interfaz para el formulario de adición de productos.
- Implementar la funcionalidad de validación de datos (nombre, precio, categoría, stock, etc.).
- Desarrollar la lógica para guardar los productos en la base de datos.
- Probar la funcionalidad en diferentes escenarios (datos válidos/erróneos).

### Para **"Eliminar productos descontinuados"**:
- Crear una lista en la interfaz que muestre los productos disponibles.
- Añadir la opción de seleccionar y eliminar productos.
- Implementar la lógica para eliminar productos de la base de datos.
- Probar que los productos eliminados no aparezcan en la tienda en línea.

## Aplicación de Descuentos
### Para **"Crear descuentos"**:
- Diseñar y crear la interfaz para definir descuentos (porcentaje o monto fijo).
- Implementar la funcionalidad de validación de datos (tipo de descuento, valor, productos aplicables).
- Desarrollar la lógica para guardar los descuentos en la base de datos.
- Verificar que los descuentos creados se reflejen correctamente en los productos.

### Para **"Programar duración de descuentos"**:
- Añadir opciones para definir fecha de inicio y fin en el formulario de descuentos.
- Implementar la lógica para que los descuentos se activen/desactiven automáticamente según las fechas.
- Probar escenarios con fechas dentro y fuera de rango.

## Experiencia de Usuario y Gestión de Cuenta
### Para **"Crear cuenta en la tienda"**:
- Diseñar la interfaz para el registro de cuentas de clientes.
- Implementar la funcionalidad de validación de datos (correo, contraseña, etc.).
- Desarrollar la lógica para guardar cuentas en la base de datos.
- Probar la funcionalidad, incluyendo el manejo de cuentas duplicadas.

---

