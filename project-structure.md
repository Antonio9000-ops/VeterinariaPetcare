```
/home/anthony/Proyectos/ProyectoUni/veterinaria/VeterinariaPetcare/
├───.gitignore
├───LICENSE
├───project-structure.md
├───README.md
├───.git/...
├───.vscode/
│   └───settings.json
└───proyecto/
    └───springWeb/
        ├───.gitattributes
        ├───.gitignore
        ├───nbactions.tmp
        ├───pom.xml
        ├───src/
        │   ├───main/
        │   │   ├───java/
        │   │   │   └───springWeb/
        │   │   │       └───demo/
        │   │   │           ├───DemoApplication.java
        │   │   │           ├───Controlador/
        │   │   │           │   ├───CitaController.java
        │   │   │           │   ├───HistoriaClinicaController.java
        │   │   │           │   ├───MascotaController.java
        │   │   │           │   ├───UsuarioController.java
        │   │   │           │   ├───VacunaController.java
        │   │   │           │   └───VistaController.java
        │   │   │           ├───domain/
        │   │   │           │   ├───Dto/
        │   │   │           │   │   ├───CitaDTO.java
        │   │   │           │   │   ├───HistoriaClinicaDTO.java
        │   │   │           │   │   ├───MascotaDTO.java
        │   │   │           │   │   ├───UsuarioDTO.java
        │   │   │           │   │   └───VacunaDTO.java
        │   │   │           │   ├───Mapper/
        │   │   │           │   │   ├───CitaMapper.java
        │   │   │           │   │   ├───HistoriaClinicaMapper.java
        │   │   │           │   │   ├───MascotaMapper.java
        │   │   │           │   │   ├───UsuarioMapper.java
        │   │   │           │   │   └───VacunaMapper.java
        │   │   │           │   ├───Modelos/
        │   │   │           │   │   ├───Cita.java
        │   │   │           │   │   ├───EstadoCita.java
        │   │   │           │   │   ├───HistoriaClinica.java
        │   │   │           │   │   ├───Mascota.java
        │   │   │           │   │   ├───Rol.java
        │   │   │           │   │   ├───Usuario.java
        │   │   │           │   │   └───Vacuna.java
        │   │   │           │   ├───Repositorios/
        │   │   │           │   │   ├───CitaRepository.java
        │   │   │           │   │   ├───HistoriaClinicaRepository.java
        │   │   │           │   │   ├───MascotaRepository.java
        │   │   │           │   │   ├───UsuarioRepository.java
        │   │   │           │   │   └───VacunaRepository.java
        │   │   │           │   └───Servicios/
        │   │   │           │       ├───CitaServiceImpl.java
        │   │   │           │       ├───HistoriaClinicaServiceImpl.java
        │   │   │           │       ├───MascotaServiceImpl.java
        │   │   │           │       ├───UsuarioServiceImpl.java
        │   │   │           │       ├───VacunaServiceImpl.java
        │   │   │           │       └───interfaces/
        │   │   │           └───Security/
        │   │   │               ├───auth/
        │   │   │               │   ├───AuthController.java
        │   │   │               │   ├───AuthRequest.java
        │   │   │               │   ├───AuthResponse.java
        │   │   │               │   ├───AuthService.java
        │   │   │               │   └───RegisterRequest.java
        │   │   │               ├───config/
        │   │   │               │   └───SecurityConfig.java
        │   │   │               └───jwt/
        │   │   │                   ├───JwtAuthFilter.java
        │   │   │                   └───JwtService.java
        │   │   └───resources/
        │   │       ├───application.properties
        │   │       ├───modelosHTML/
        │   │       │   ├───404.html
        │   │       │   ├───about.html
        │   │       │   ├───contact.html
        │   │       │   ├───feature.html
        │   │       │   ├───index.html
        │   │       │   ├───project.html
        │   │       │   ├───service.html
        │   │       │   ├───team.html
        │   │       │   └───testimonial.html
        │   │       ├───static/
        │   │       │   ├───css/
        │   │       │   │   ├───bootstrap.min.css
        │   │       │   │   └───style.css
        │   │       │   ├───img/
        │   │       │   │   ├───about-1.jpg
        │   │       │   │   ├───about-2.jpg
        │   │       │   │   ├───C1.jpg
        │   │       │   │   ├───C2.jpg
        │   │       │   │   ├───C3.jpg
        │   │       │   │   ├───C4.jpg
        │   │       │   │   ├───footer.jpg
        │   │       │   │   ├───hero-bg.jpg
        │   │       │   │   ├───hero-slider-1.jpg
        │   │       │   │   ├───hero-slider-2.jpg
        │   │       │   │   ├───hero-slider-3.jpg
        │   │       │   │   ├───nav.jpg
        │   │       │   │   ├───nav3.png
        │   │       │   │   ├───newsletter.jpg
        │   │       │   │   ├───project-1.jpg
        │   │       │   │   ├───project-2.jpg
        │   │       │   │   ├───project-3.jpg
        │   │       │   │   ├───project-4.jpg
        │   │       │   │   ├───project-5.jpg
        │   │       │   │   ├───project-6.jpg
        │   │       │   │   ├───service-1.jpg
        │   │       │   │   ├───service-2.jpg
        │   │       │   │   ├───service-3.jpg
        │   │       │   │   ├───service-4.jpg
        │   │       │   │   ├───team-1.jpg
        │   │       │   │   ├───team-2.jpg
        │   │       │   │   ├───team-3.jpg
        │   │       │   │   ├───team-4.jpg
        │   │       │   │   ├───testimonial-1.jpg
        │   │       │   │   ├───testimonial-2.jpg
        │   │       │   │   └───testimonial-3.jpg
        │   │       │   ├───js/
        │   │       │   │   └───main.js
        │   │       │   ├───lib/
        │   │       │   │   ├───animate/
        │   │       │   │   │   ├───animate.css
        │   │       │   │   │   └───animate.min.css
        │   │       │   │   ├───counterup/
        │   │       │   │   │   └───counterup.min.js
        │   │       │   │   ├───easing/
        │   │       │   │   │   ├───easing.js
        │   │       │   │   │   └───easing.min.js
        │   │       │   │   ├───owlcarousel/
        │   │       │   │   │   ├───LICENSE
        │   │       │   │   │   ├───owl.carousel.js
        │   │       │   │   │   ├───owl.carousel.min.js
        │   │       │   │   │   └───assets/
        │   │       │   │   │       ├───ajax-loader.gif
        │   │       │   │   │       ├───owl.carousel.css
        │   │       │   │   │       ├───owl.carousel.min.css
        │   │       │   │   │       ├───owl.theme.default.css
        │   │       │   │   │       ├───owl.theme.default.min.css
        │   │       │   │   │       ├───owl.theme.green.css
        │   │       │   │   │       ├───owl.theme.green.min.css
        │   │       │   │   │       └───owl.video.play.png
        │   │       │   │   ├───waypoints/
        │   │       │   │   │   ├───links.php
        │   │       │   │   │   └───waypoints.min.js
        │   │       │   │   └───wow/
        │   │       │   │       ├───wow.js
        │   │       │   │       └───wow.min.js
        │   │       │   └───scss/
        │   │       │       ├───bootstrap.scss
        │   │       │       └───bootstrap/
        │   │       │           └───scss/
        │   │       │               ├───_accordion.scss
        │   │       │               ├───_alert.scss
        │   │       │               ├───_badge.scss
        │   │       │               ├───_breadcrumb.scss
        │   │       │               ├───_button-group.scss
        │   │       │               ├───_buttons.scss
        │   │       │               ├───_card.scss
        │   │       │               ├───_carousel.scss
        │   │       │               ├───_close.scss
        │   │       │               ├───_containers.scss
        │   │       │               ├───_dropdown.scss
        │   │       │               ├───_forms.scss
        │   │       │               ├───_functions.scss
        │   │       │               ├───_grid.scss
        │   │       │               ├───_helpers.scss
        │   │       │               ├───_images.scss
        │   │       │               ├───_list-group.scss
        │   │       │               ├───_mixins.scss
        │   │       │               ├───_modal.scss
        │   │       │               ├───_nav.scss
        │   │       │               ├───_navbar.scss
        │   │       │               ├───_offcanvas.scss
        │   │       │               ├───_pagination.scss
        │   │       │               ├───_popover.scss
        │   │       │               ├───...
        │   │       │               └───...
        │   │       └───templates/
        │   │           ├───agenda.html
        │   │           ├───cita-formulario.html
        │   │           ├───gestion-citas.html
        │   │           ├───historia-formulario.html
        │   │           ├───inicio.html
        │   │           ├───login.html
        │   │           ├───mascota-detalle.html
        │   │           ├───mascota-formulario.html
        │   │           ├───mascotas.html
        │   │           ├───register.html
        │   │           └───vacuna-formulario.html
        │   └───test/
        │       └───java/
        │           └───springWeb/
        │               └───demo/
        │                   └───DemoApplicationTests.java
```