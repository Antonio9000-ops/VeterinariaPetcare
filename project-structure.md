# Project Structure

This document outlines the structure of the Veterinaria Petcare project.

## Root Directory

-   `.gitignore`: Specifies intentionally untracked files to ignore.
-   `LICENSE`: The license for the project.
-   `project-structure.md`: This file, outlining the project structure.
-   `README.md`: General information about the project.
-   `proyecto/`: Contains the main Spring Boot application.

## `proyecto/springWeb` Directory

-   `.gitattributes`: Defines attributes per path.
-   `.gitignore`: Specifies intentionally untracked files to ignore within the Spring Boot project.
-   `pom.xml`: Maven project configuration file.
-   `src/`: Contains the source code.

### `src/main/java/springWeb/demo`

-   `DemoApplication.java`: The main entry point for the Spring Boot application.
-   `Controlador/`: Controllers for handling web requests.
    -   `CitaController.java`
    -   `HistoriaClinicaController.java`
    -   `MascotaController.java`
    -   `UsuarioController.java`
    -   `VacunaController.java`
    -   `VistaController.java`
-   `domain/`: Contains the business logic and data models.
    -   `Dto/`: Data Transfer Objects.
        -   `CitaDTO.java`
        -   `HistoriaClinicaDTO.java`
        -   `MascotaDTO.java`
        -   `UsuarioDTO.java`
        -   `VacunaDTO.java`
    -   `Mapper/`: Mappers for converting between DTOs and entities.
        -   `CitaMapper.java`
        -   `HistoriaClinicaMapper.java`
        -   `MascotaMapper.java`
        -   `UsuarioMapper.java`
        -   `VacunaMapper.java`
    -   `Modelos/`: Entity classes.
        -   `Cita.java`
        -   `EstadoCita.java`
        -   `HistoriaClinica.java`
        -   `Mascota.java`
        -   `Rol.java`
        -   `Usuario.java`
        -   `Vacuna.java`
    -   `Repositorios/`: Spring Data JPA repositories.
        -   `CitaRepository.java`
        -   `HistoriaClinicaRepository.java`
        -   `MascotaRepository.java`
        -   `UsuarioRepository.java`
        -   `VacunaRepository.java`
    -   `Servicios/`: Service layer.
        -   `CitaServiceImpl.java`
        -   `HistoriaClinicaServiceImpl.java`
        -   `MascotaServiceImpl.java`
        -   `UsuarioServiceImpl.java`
        -   `VacunaServiceImpl.java`
        -   `interfaces/`
            -   `CitaService.java`
            -   `HistoriaClinicaService.java`
            -   `MascotaService.java`
            -   `UsuarioService.java`
            -   `VacunaService.java`
-   `Security/`: Security configuration.
    -   `auth/`: Authentication related classes.
        -   `AuthController.java`
        -   `AuthRequest.java`
        -   `AuthResponse.java`
        -   `AuthService.java`
        -   `RegisterRequest.java`
    -   `config/`: Security configuration.
        -   `SecurityConfig.java`
    -   `jwt/`: JWT related classes.
        -   `JwtAuthFilter.java`
        -   `JwtService.java`

### `src/main/resources`

-   `application.properties`: Configuration file for the application.
-   `templates/`: HTML templates for the web interface.
    -   `agenda.html`
    -   `cita-formulario.html`
    -   `historia-formulario.html`
    -   `inicio.html`
    -   `login.html`
    -   `mascota-detalle.html`
    -   `mascota-formulario.html`
    -   `mascotas.html`
    -   `register.html`
    -   `vacuna-formulario.html`

### `src/test/java/springWeb/demo`

-   `DemoApplicationTests.java`: Tests for the application.
