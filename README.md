# ClinicaVeterinaria

Crear estructura del Proyecto

usuario: user
contraseña: "cambia cada vez que se inicia"
Crear base de datos- Mysql

# Estructura del proyecto
## Capa de Dominio (domain)
Contiene la logica del negocio y las reglas (dominio).
### dto 
Clase que sirve para transferir datos entre capas como por ejemplo : backend y el cliente

### repository
Interfaces que definen las operaciones de acceso a los datos

### service 
Clases que contiene logica del negocio como crear cita o registrar cliente

## Capa de Persistencia
Se encarga de la comunicacion con la base de datos
### entity 
Clase que representa las tablas de la base de datos

### crud 
Interfaces específicas para las operaciones (crear, leer , actualizar , borrar)

## Capa de presentacion 

Api rest

# Estructuras de clases
## Propietario.java 
Dentro del paquete entity, esta clase mapea la tabla propietarios de la base de datos. 
## Mascota.java
Clase que mapea la tabla mascota y tiene realcion con el propietario 

## Requerimientos Funcionales
- Registro y Login
- Reservas
- 
- 
