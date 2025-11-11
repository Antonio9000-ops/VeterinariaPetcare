/
├───.gitignore
├───LICENSE
├───project-structure.md
├───README.md
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
        │   │   │           │   ├───FacturacionController.java
        │   │   │           │   ├───HistoriaClinicaController.java
        │   │   │           │   ├───ItemFacturableController.java
        │   │   │           │   ├───MascotaController.java
        │   │   │           │   ├───PagoController.java
        │   │   │           │   ├───RecetaController.java
        │   │   │           │   ├───TratamientoController.java
        │   │   │           │   ├───UsuarioController.java
        │   │   │           │   ├───VacunaController.java
        │   │   │           │   └───VistaController.java
        │   │   │           ├───domain/
        │   │   │           │   ├───Dto/
        │   │   │           │   │   ├───CitaDTO.java
        │   │   │           │   │   ├───HistoriaClinicaDTO.java
        │   │   │           │   │   ├───MascotaDTO.java
        │   │   │           │   │   ├───PagoDTO.java
        │   │   │           │   │   ├───RecetaDTO.java
        │   │   │           │   │   ├───TratamientoDTO.java
        │   │   │           │   │   ├───UsuarioDTO.java
        │   │   │           │   │   ├───VacunaDTO.java
        │   │   │           │   │   └───facturacion/
        │   │   │           │   ├───Mapper/
        │   │   │           │   │   ├───CitaMapper.java
        │   │   │           │   │   ├───HistoriaClinicaMapper.java
        │   │   │           │   │   ├───MascotaMapper.java
        │   │   │           │   │   ├───PagoMapper.java
        │   │   │           │   │   ├───RecetaMapper.java
        │   │   │           │   │   ├───TratamientoMapper.java
        │   │   │           │   │   ├───UsuarioMapper.java
        │   │   │           │   │   └───VacunaMapper.java
        │   │   │           │   ├───Modelos/
        │   │   │           │   │   ├───Cita.java
        │   │   │           │   │   ├───EstadoCita.java
        │   │   │           │   │   ├───EstadoPago.java
        │   │   │           │   │   ├───Factura.java
        │   │   │           │   │   ├───HistoriaClinica.java
        │   │   │           │   │   ├───ItemFacturable.java
        │   │   │           │   │   ├───Mascota.java
        │   │   │           │   │   ├───Pago.java
        │   │   │           │   │   ├───Receta.java
        │   │   │           │   │   ├───Rol.java
        │   │   │           │   │   ├───TipoItem.java
        │   │   │           │   │   ├───Tratamiento.java
        │   │   │           │   │   ├───Usuario.java
        │   │   │           │   │   └───Vacuna.java
        │   │   │           │   ├───Repositorios/
        │   │   │           │   │   ├───CitaRepository.java
        │   │   │           │   │   ├───FacturaRepository.java
        │   │   │           │   │   ├───HistoriaClinicaRepository.java
        │   │   │           │   │   ├───ItemFacturableRepository.java
        │   │   │           │   │   ├───MascotaRepository.java
        │   │   │           │   │   ├───PagoRepository.java
        │   │   │           │   │   ├───RecetaRepository.java
        │   │   │           │   │   ├───TratamientoRepository.java
        │   │   │           │   │   ├───UsuarioRepository.java
        │   │   │           │   │   └───VacunaRepository.java
        │   │   │           │   └───Servicios/
        │   │   │           │       ├───CitaServiceImpl.java
        │   │   │           │       ├───FacturacionServiceImpl.java
        │   │   │           │       ├───HistoriaClinicaServiceImpl.java
        │   │   │           │       ├───ItemFacturableServiceImpl.java
        │   │   │           │       ├───MascotaServiceImpl.java
        │   │   │           │       ├───RecetaServiceImpl.java
        │   │   │           │       ├───TratamientoServiceImpl.java
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
        │   │   │                   └───JwtAuthFilter.java
        │   │   │                   └───...
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
        │   │       │   │   ├───footer2.png
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
        │   │       └───templates/
        │   │           ├───agenda.html
        │   │           ├───cita-formulario.html
        │   │           ├───gestion-citas.html
        │   │           ├───gestion-servicios.html
        │   │           ├───historia-formulario.html
        │   │           ├───inicio.html
        │   │           ├───login.html
        │   │           ├───mascota-detalle.html
        │   │           ├───mascota-formulario.html
        │   │           ├───mascotas.html
        │   │           ├───pagos.html
        │   │           ├───receta-formulario.html
        │   │           ├───register.html
        │   │           ├───tratamiento-formulario.html
        │   │           └───vacuna-formulario.html
        │   └───test/
        │       └───java/
        │           └───springWeb/
        │               └───demo/
        │                   └───DemoApplicationTests.java