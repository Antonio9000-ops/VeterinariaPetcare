package springWeb.demo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    /**
     * Maneja las solicitudes a la raíz del sitio web ("/") y muestra la página de inicio.
     * @return El nombre de la plantilla Thymeleaf a renderizar ("inicio").
     */
    @GetMapping("/")
    public String mostrarPaginaDeInicio() {
        return "inicio"; // Devuelve "inicio.html" desde la carpeta /resources/templates/
    }
    /**
     * Muestra la página de inicio de sesión.
     * @return El nombre de la plantilla "login".
     */
    @GetMapping("/auth/login")
    public String mostrarPaginaLogin() {
        return "login"; // Devuelve login.html
    }

    /**
     * Muestra la página de registro.
     * @return El nombre de la plantilla "register".
     */
    @GetMapping("/auth/register")
    public String mostrarPaginaRegister() {
        return "register"; // Devuelve register.html
    }
    @GetMapping("/mascotas")
    public String mostrarPaginaMascotas() {
        return "mascotas";
    }

    @GetMapping("/mascota-detalle")
    public String mostrarPaginaMascotaDetalle() {
        return "mascota-detalle";
    }

    @GetMapping("/mascota-formulario")
    public String mostrarPaginaMascotaFormulario() {
        return "mascota-formulario";
    }

    @GetMapping("/cita-formulario")
    public String mostrarPaginaCitaFormulario() {
        return "cita-formulario";
    }
}