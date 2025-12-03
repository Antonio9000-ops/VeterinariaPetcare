![Logo UTP](https://portalestudiante.utp.edu.pe/Images/newlogoUTP.jpg)

---
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Render](https://img.shields.io/badge/Render-%46E3B7.svg?style=for-the-badge&logo=render&logoColor=white)

---

## Descripción general del proyecto

# Introducción:
El presente repositorio es un proyecto universitario del curso de Desarrollo Integrador Web, dirigido por el docente Henrique Lee, y tiene como finalidad desarrollar un aplicativo web con **temática** y opciones para una veterinaria.

Con el transcurso del tiempo se irán agregando diversos elementos correspondientes a los avances vistos en clase, los cuales serán especificados mediante Sprints.

Las versiones del repositorio actual, se dividirán de la **siguiente** forma:

| Spring | Identificación |
|---|---|
| S1 | V.1.0.0 |
| S2 | V.2.0.0 |
| S3 | V.3.0.0 |

---

## Funcionalidad de la página:
Como se indicó anteriormente, este trabajo se **regirá** por la metodología de trabajo Sprint:

### Plan de Desarrollo del Proyecto (Sprints)

Este documento detalla las funcionalidades planificadas, organizadas por Sprints, incluyendo el módulo y el rol responsable.

| Sprint | Módulo | Funcionalidad | Rol Asociado | Estado |
| :--- | :--- | :--- | :--- | :--- |
| **Sprint 1** | **Páginas Estáticas** | Inicio | General | Finalizado |
| | **Páginas Estáticas** | Sobre nosotros | General | Finalizado |
| | **Páginas Estáticas** | Contacto de Emergencia | General | Finalizado |
| | **Autenticación** | Login | General | Finalizado |
| | **Autenticación** | Registro (Register) | General | Finalizado |
| | **Gestión de Mascotas** | Agregar Mascota | Dueño de Mascota | Finalizado |
| | **Gestión de Mascotas** | Solicitar Citas | Dueño de Mascota | Finalizado |
| | **Gestión de Mascotas** | Editar datos de mascota | Dueño de Mascota | Finalizado |
| | **Gestión de Mascotas** | Eliminar mascota | Dueño de Mascota | Finalizado |
| **Sprint 2** | **Páginas Estáticas** | Política de Privacidad | General | Finalizado |
| | **Páginas Estáticas** | Términos y Condiciones | General | Finalizado |
| | **Páginas Estáticas** | Catálogo (*Revisar*) | General | Finalizado |
| | **Finanzas** | Gestión de Pagos | Dueño de Mascota | Finalizado |
| | **Gestión Clínica** | Agregar recetas médicas | Veterinario | Finalizado |
| | **Gestión Clínica** | Aceptar y rechazar citas clínicas | Veterinario | Finalizado |
| | **Gestión Clínica** | Historial Clínico | Veterinario | Finalizado |
| | **Gestión Clínica** | Recetar medicinas | Veterinario | Finalizado |
| **Sprint 3** | **Gestión de Usuarios** | Editar Usuario | General | Finalizado |
| | **Rol Asistente** | Agregar datos de mascotas no existentes | Asistente | Finalizado |
| | **Rol Asistente** | Agendar citas de emergencia | Asistente | Finalizado |
| | **Seguridad** | Implementar spring security| General | Finalizado |
| | **Hosting** | Subir proyecto en la nuber | General | Finalizado |

---

## Objetivo Final:
Nuestro objetivo final es poder adquirir experiencia en la **elaboración** de páginas web que operan mediante servicios y no páginas estáticas. Además, el proyecto se presentará en Render para la entrega final del curso, sin embargo, esta versión no estará disponible para el público general.

---

### Tecnologías Usadas
- *Backend*:
    - Spring Boot
    - Maven
        - *Dependencias*:
            - Spring web
- *Frontend*:
    - Bootstrap

---


### Equipo de Desarrolladores

| Colaboradores | Área |
|---|---|
| Salvador | BackEnd |
| Antonio | FrontEnd |
| Walter | FrontEnd |
| Ravichagua | BackEnd |
| Alexander | FrontEnd |

### Estructura del Proyecto

```
├── java
│   └── springWeb
│       └── demo
│           ├── Controlador
│           │   ├── CitaController.java
│           │   ├── FacturacionController.java
│           │   ├── HistoriaClinicaController.java
│           │   ├── HomeController.java
│           │   ├── ItemFacturableController.java
│           │   ├── MascotaController.java
│           │   ├── PagoController.java
│           │   ├── RecetaController.java
│           │   ├── TratamientoController.java
│           │   ├── UsuarioController.java
│           │   ├── VacunaController.java
│           │   └── VistaController.java
│           ├── DemoApplication.java
│           ├── domain
│           │   ├── Dto
│           │   │   ├── CitaDTO.java
│           │   │   ├── facturacion
│           │   │   │   ├── DeudaDetalleDTO.java
│           │   │   │   ├── DeudaTotalDTO.java
│           │   │   │   └── FacturaRequestDTO.java
│           │   │   ├── HistoriaClinicaDTO.java
│           │   │   ├── MascotaDTO.java
│           │   │   ├── PagoDTO.java
│           │   │   ├── RecetaDTO.java
│           │   │   ├── TratamientoDTO.java
│           │   │   ├── UsuarioDTO.java
│           │   │   └── VacunaDTO.java
│           │   ├── Mapper
│           │   │   ├── CitaMapper.java
│           │   │   ├── HistoriaClinicaMapper.java
│           │   │   ├── MascotaMapper.java
│           │   │   ├── PagoMapper.java
│           │   │   ├── RecetaMapper.java
│           │   │   ├── TratamientoMapper.java
│           │   │   ├── UsuarioMapper.java
│           │   │   └── VacunaMapper.java
│           │   ├── Modelos
│           │   │   ├── Cita.java
│           │   │   ├── EstadoCita.java
│           │   │   ├── EstadoPago.java
│           │   │   ├── Factura.java
│           │   │   ├── HistoriaClinica.java
│           │   │   ├── ItemFacturable.java
│           │   │   ├── Mascota.java
│           │   │   ├── Pago.java
│           │   │   ├── Receta.java
│           │   │   ├── Rol.java
│           │   │   ├── TipoItem.java
│           │   │   ├── Tratamiento.java
│           │   │   ├── Usuario.java
│           │   │   └── Vacuna.java
│           │   ├── Repositorios
│           │   │   ├── CitaRepository.java
│           │   │   ├── FacturaRepository.java
│           │   │   ├── HistoriaClinicaRepository.java
│           │   │   ├── ItemFacturableRepository.java
│           │   │   ├── MascotaRepository.java
│           │   │   ├── PagoRepository.java
│           │   │   ├── RecetaRepository.java
│           │   │   ├── TratamientoRepository.java
│           │   │   ├── UsuarioRepository.java
│           │   │   └── VacunaRepository.java
│           │   └── Servicios
│           │       ├── CitaServiceImpl.java
│           │       ├── FacturacionServiceImpl.java
│           │       ├── HistoriaClinicaServiceImpl.java
│           │       ├── interfaces
│           │       │   ├── CitaService.java
│           │       │   ├── FacturacionService.java
│           │       │   ├── HistoriaClinicaService.java
│           │       │   ├── ItemFacturableService.java
│           │       │   ├── MascotaService.java
│           │       │   ├── RecetaService.java
│           │       │   ├── TratamientoService.java
│           │       │   ├── UsuarioService.java
│           │       │   └── VacunaService.java
│           │       ├── ItemFacturableServiceImpl.java
│           │       ├── MascotaServiceImpl.java
│           │       ├── RecetaServiceImpl.java
│           │       ├── TratamientoServiceImpl.java
│           │       ├── UsuarioServiceImpl.java
│           │       └── VacunaServiceImpl.java
│           └── Security
│               ├── auth
│               │   ├── AuthController.java
│               │   ├── AuthRequest.java
│               │   ├── AuthResponse.java
│               │   ├── AuthService.java
│               │   └── RegisterRequest.java
│               ├── config
│               │   └── SecurityConfig.java
│               └── jwt
│                   ├── JwtAuthFilter.java
│                   └── JwtService.java
└── resources
    ├── application.properties
    └── templates
        ├── about.html
        ├── agenda.html
        ├── asistente-mascota-formulario.html
        ├── cita-formulario.html
        ├── contact.html
        ├── gestion-citas.html
        ├── gestion-servicios.html
        ├── historia-formulario.html
        ├── inicio.html
        ├── login.html
        ├── mascota-detalle.html
        ├── mascota-formulario.html
        ├── mascotas.html
        ├── pagos.html
        ├── privacy.html
        ├── receta-formulario.html
        ├── register.html
        ├── terms.html
        ├── tratamiento-formulario.html
        └── vacuna-formulario.html

```

---

