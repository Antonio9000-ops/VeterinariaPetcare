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
}